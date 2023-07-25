package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.Country;
import com.eradiuxtech.customerservice.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StateRepository extends JpaRepository<State, Long> {
    State findByCode(String code);

    State findByName(String name);


    @Query(value = "SELECT * FROM states", nativeQuery = true)
    Page<State> getStates(final Pageable pageable);
}
