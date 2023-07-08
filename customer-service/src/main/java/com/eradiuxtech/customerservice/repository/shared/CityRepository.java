package com.eradiuxtech.customerservice.repository.shared;

import com.eradiuxtech.customerservice.entity.shared.City;
import com.eradiuxtech.customerservice.entity.shared.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public  interface CityRepository extends JpaRepository<City, Long> {

    @Transactional
    @Query(value = "SELECT * from cities c where c.code = ?1", nativeQuery = true)
    Country findByCode(String code);

}
