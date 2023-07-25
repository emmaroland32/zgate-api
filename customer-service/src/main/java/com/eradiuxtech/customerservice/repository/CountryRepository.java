package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCode(String code);

    Country findByName(String name);


    @Query(value = "SELECT * FROM countries", nativeQuery = true)
    Page<Country> getCountries(final Pageable pageable);
}
