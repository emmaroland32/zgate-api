package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///countries"
})
public class CountryRepositoryTest {

    @Autowired
    CountryRepository repository;
    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.save(new Country("Nigeria", "NG", "+234", "NGN"));
        repository.save(new Country("Ghana", "GH", "+235", "GHS"));
    }

    @Test
    public void shouldGetCountryByCode() {
        assertThat(repository.findByCode("NG")).isNotNull();
    }

    @Test
    public void shouldGetCountryByName() {
        assertThat(repository.findByName("Nigeria")).isNotNull();
    }
}