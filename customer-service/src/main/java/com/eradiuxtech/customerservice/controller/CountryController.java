package com.eradiuxtech.customerservice.controller;


import com.eradiuxtech.customerservice.entity.Country;
import com.eradiuxtech.customerservice.exception.CustomNotFoundException;
import com.eradiuxtech.customerservice.repository.CountryRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CountryControllerV1")
@RequestMapping("/api/customer-service/v1/countries")
public class CountryController {

    private final CountryRepository repository;

    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public Iterable<Country> listAll() {
        return repository.findAll();
    }


    @GetMapping("/paginate")
    public ResponseEntity<Iterable<Country>> listAllWithPagination( @RequestParam(defaultValue = "0") final Integer page,
                                                    @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(repository.getCountries(PageRequest.of(page, size)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable Long id) {
        return repository.findById(id)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> new CustomNotFoundException("Country", id));
    }


    @GetMapping("/search")
    public ResponseEntity<Country> search(@RequestParam(required = false) String code, @RequestParam(required = false) String name) {
        if(code.isEmpty() && name.isEmpty()) {
            throw new BadRequestException("Provide Either code or name to search");
        }
        if(!code.isEmpty() && !name.isEmpty()) {
            throw new BadRequestException("Provide Either code or name to search");
        }

        Country country = null;
        if(!code.isEmpty()) {
            country = repository.findByCode(code);
        }

        if(!name.isEmpty()){
            country = repository.findByName(name);
        }

        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<Country> save(@Valid @RequestBody Country country) {
        country.setId(null);
        Country savedCountry = repository.save(country);
        return new ResponseEntity<Country>(savedCountry, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id, @Valid @RequestBody Country country) {
        Country existingCountry = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Country" ,id));
        if(country.getName() != null) {
            existingCountry.setName(country.getName());
        }
        if(country.getCode() != null) {
            existingCountry.setCode(country.getCode());
        }
        if(country.getPhoneCode() != null) {
            existingCountry.setPhoneCode(country.getPhoneCode());
        }
        if(country.getDescription() != null) {
            existingCountry.setDescription(country.getDescription());
        }
        Country updatedCountry = repository.save(existingCountry);
        return ResponseEntity.ok(updatedCountry);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Country country = repository.findById(id).orElseThrow(() -> new CustomNotFoundException("Country" , id));
        repository.delete(country);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
