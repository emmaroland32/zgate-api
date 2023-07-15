package com.eradiuxtech.customerservice.repository.shared;

import com.eradiuxtech.customerservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public  interface CountryRepository extends JpaRepository<Country, Long> {


    @Transactional
    @Query(value = "SELECT * from countries c where c.code = ?1", nativeQuery = true)
    Country findByCode(String code);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE countries c SET c.name = ?1, c.code = ?2, c.phone_code = ?3, c.description = ?4 WHERE id = ?5", nativeQuery = true)
//    void updateById(Long id, UpdateCountryRequestDto updateCustomerDto);
}
