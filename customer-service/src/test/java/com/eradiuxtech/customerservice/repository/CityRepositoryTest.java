package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.City;
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
public class CityRepositoryTest {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CityRepository cityRepository;
    @BeforeEach
    void setUp() {
        stateRepository.deleteAll();
        countryRepository.deleteAll();
        cityRepository.deleteAll();
      Country country =  countryRepository.save(new Country("Nigeria", "NG", "+234", "NGN"));
      State state = stateRepository.save(new State("Lagos", "LAG", country, "New State"));
        cityRepository.save(new City("Ikeja", "IKJ", state, country, "Ikeja City"));
    }

    @Test
    public void shouldGetCityByCode() {
       assertThat(cityRepository.findByCode("IKJ"), hasProperty("code", equalTo("IKJ")));
    }

    @Test
    public void shouldGetCityByName() {
        State state = stateRepository.findByName("Ikeja");
        assertThat(state, hasProperty("name", equalTo("Ikeja")));
    }

    @Test
    public void shouldGetCityByCountry() {
        Optional<Country> country = countryRepository.findById(1L);
        assertThat(country, hasProperty("code", equalTo("NG")));
        List<City> cities = cityRepository.listByCountryId(country.get().getId());
        assertThat(cities, hasSize(1));
};

    @Test
    public void shouldGetCityByState() {
        Optional<State> state = stateRepository.findById(1L);
        assertThat(state, hasProperty("code", equalTo("LAG")));
        List<City> cities = cityRepository.listByStateId(state.get().getId());
        assertThat(cities, hasSize(1));
    };
}