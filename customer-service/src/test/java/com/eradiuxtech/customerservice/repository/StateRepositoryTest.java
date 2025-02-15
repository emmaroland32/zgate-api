package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.Country;
import com.eradiuxtech.customerservice.entity.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///states"
})
public class StateRepositoryTest {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CountryRepository countryRepository;
    @BeforeEach
    void setUp() {
        stateRepository.deleteAll();
        countryRepository.deleteAll();
      Country country =  countryRepository.save(new Country("Nigeria", "NG", "+234", "NGN"));
        stateRepository.save(new State("Lagos", "LAG", country, "New State"));
    }

    @Test
    public void shouldGetStateByCode() {
       assertThat(stateRepository.findByCode("LAG"), hasProperty("code", equalTo("LAG")));
    }

    @Test
    public void shouldGetStateByName() {
        State state = stateRepository.findByName("Lagos");
        assertThat(state, hasProperty("name", equalTo("Lagos")));
    }


    @Test
    public void shouldGetStateByCountry() {
        Optional<Country> country = countryRepository.findById(1L);
        assertThat(country, hasProperty("code", equalTo("NG")));
        List<State> states = stateRepository.listByCountryId(country.get().getId());
        assertThat(states, hasSize(1));
};
}