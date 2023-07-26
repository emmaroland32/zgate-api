package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.AddressType;
import com.eradiuxtech.customerservice.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressTypeRepository extends JpaRepository<AddressType, Long> {
    AddressType findByName(String name);

    AddressType findByType(String type);

    @Query(value = "SELECT * FROM address_types", nativeQuery = true)
    Page<AddressType> findAllWithPagination(final Pageable pageable);
}
