package com.eradiuxtech.customerservice.repository.shared;

import com.eradiuxtech.customerservice.entity.shared.Country;
import com.eradiuxtech.customerservice.entity.shared.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public  interface StateRepository extends JpaRepository<State, Long> {


    @Transactional
    @Query(value = "SELECT * from states c where c.code = ?1", nativeQuery = true)
    State findByCode(String code);

}
