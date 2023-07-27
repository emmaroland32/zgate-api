package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CityRepository extends JpaRepository<City, Long> {
    City findByCode(String code);

    City findByName(String name);

    @Query(value = "SELECT * FROM cities", nativeQuery = true)
    Page<City> findWithPagination(final Pageable pageable);


    @Query(value = "SELECT * FROM cities WHERE state_id = ?1", nativeQuery = true)
    List<City> listByStateId(Long id);


    @Query(value = "SELECT * FROM cities WHERE country_id = ?1", nativeQuery = true)
    List<City> listByCountryId(Long id);
}
