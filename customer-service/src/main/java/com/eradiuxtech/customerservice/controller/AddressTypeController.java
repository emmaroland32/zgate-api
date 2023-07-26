package com.eradiuxtech.customerservice.controller;


import com.eradiuxtech.customerservice.entity.AddressType;
import com.eradiuxtech.customerservice.exception.NotFoundException;
import com.eradiuxtech.customerservice.repository.AddressTypeRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AddressTypeControllerV1")
@RequestMapping("/api/customer-service/v1/address-types")
public class AddressTypeController {

    private final AddressTypeRepository repository;

    public AddressTypeController(AddressTypeRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public Iterable<AddressType> listAll() {
        return repository.findAll();
    }


    @GetMapping("/paginate")
    public ResponseEntity<Iterable<AddressType>> listAllWithPagination( @RequestParam(defaultValue = "0") final Integer page,
                                                    @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(repository.findAllWithPagination(PageRequest.of(page, size)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AddressType> getById(@PathVariable Long id) {
        return repository.findById(id)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> new NotFoundException("Address Type", id));
    }



    @PostMapping
    public ResponseEntity<AddressType> save(@Valid @RequestBody AddressType addressType) {
        addressType.setId(null);
        AddressType savedAddressType = repository.save(addressType);
        return new ResponseEntity<AddressType>(savedAddressType, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AddressType> update(@PathVariable Long id, @Valid @RequestBody AddressType addressType) {
        AddressType existingAddressType = repository.findById(id).orElseThrow(() -> new NotFoundException("Address Type" ,id));
        if(addressType.getName() != null) {
            existingAddressType.setName(addressType.getName());
        }
        if(addressType.getType() != null) {
            existingAddressType.setType(addressType.getType());
        }
        if(addressType.getDescription() != null) {
            existingAddressType.setDescription(addressType.getDescription());
        }
        AddressType updatedAddressType = repository.save(existingAddressType);
        return ResponseEntity.ok(updatedAddressType);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        AddressType addressType = repository.findById(id).orElseThrow(() -> new NotFoundException("Address Type" ,id));
        repository.delete(addressType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
