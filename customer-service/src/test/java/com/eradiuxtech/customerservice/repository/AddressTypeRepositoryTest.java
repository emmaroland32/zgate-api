package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.AddressType;
import com.eradiuxtech.customerservice.entity.Country;
import com.eradiuxtech.customerservice.entity.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///states"
})
public class AddressTypeRepositoryTest {

    @Autowired
    AddressTypeRepository repository;


    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.save(new AddressType("Billing Address", "billing", "Billing Address"));
    }

    @Test
    public void shouldGetStateByCode() {
       assertThat(repository.findByType("billing"), hasProperty("type", equalTo("billing")));
    }

    @Test
    public void shouldGetStateByName() {
        AddressType addressType = repository.findByName("Billing Address");
        assertThat(addressType, hasProperty("name", equalTo("Billing Address")));
    }

    @Test
    public void shouldGetAddressTypeWithPagination() {
        Page<AddressType> addressTypes = repository.findAllWithPagination(PageRequest.of(0, 1));
        assertThat(addressTypes.getContent(), hasSize(1));
};
}