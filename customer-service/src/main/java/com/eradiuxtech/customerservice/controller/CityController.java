package com.eradiuxtech.customerservice.controller;


import com.eradiuxtech.customerservice.entity.City;
import com.eradiuxtech.customerservice.entity.Country;
import com.eradiuxtech.customerservice.entity.State;
import com.eradiuxtech.customerservice.exception.CustomNotFoundException;
import com.eradiuxtech.customerservice.repository.CityRepository;
import com.eradiuxtech.customerservice.repository.CountryRepository;
import com.eradiuxtech.customerservice.repository.StateRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/customer-service/v1/cities", name = "City API",produces = "application/json",path = "/api/customer-service/v1/cities", headers = "X-API-Version=v1")
public class CityController {

    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityController(StateRepository stateRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    @GetMapping
    public Iterable<City> listAll() {
        return cityRepository.findAll();
    }



    @GetMapping("/paginate")
    public ResponseEntity<Iterable<City>> listAllWithPagination( @RequestParam(defaultValue = "0") final Integer page,
                                                    @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(cityRepository.findWithPagination(PageRequest.of(page, size)));
    }


    @GetMapping("/state/{id}")
    public ResponseEntity<Iterable<City>> listAllByState(@PathVariable Long id) {
        return ResponseEntity.ok(cityRepository.listByStateId(id));
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<Iterable<City>> listAllByCountry(@PathVariable Long id) {
        return ResponseEntity.ok(cityRepository.listByCountryId(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable Long id) {
        return cityRepository.findById(id)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> new CustomNotFoundException("City", id));
    }

    @GetMapping("/search")
    public ResponseEntity<City> search(@RequestParam(required = false) String code, @RequestParam(required = false) String name) {
       if(code.isEmpty() && name.isEmpty()) {
           throw new BadRequestException("Provide Either code or name to search");
       }
       if(!code.isEmpty() && !name.isEmpty()) {
           throw new BadRequestException("Provide Either code or name to search");
       }

       City city = null;
         if(!code.isEmpty()) {
              city = cityRepository.findByCode(code);
         }

         if(!name.isEmpty()){
             city = cityRepository.findByName(name);
            }

        return ResponseEntity.ok(city);
    }

    @PostMapping
    public ResponseEntity<City> save(@Valid @RequestBody City city) {
        city.setId(null);
        City savedCity = cityRepository.save(city);
        return new ResponseEntity<City>(savedCity, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @Valid @RequestBody City city) {
        City existingCity = cityRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("City" ,id));
        if(city.getName() != null) {
            existingCity.setName(city.getName());
        }
        if(city.getCode() != null) {
            existingCity.setCode(city.getCode());
        }
        if(city.getCountry() != null) {
            Country existingCountry = countryRepository.findById(city.getCountry().getId()).orElseThrow(() -> new CustomNotFoundException("Country" ,id));
            existingCity.setCountry(existingCountry);
        }
        if(city.getState() != null) {
            State existingState = stateRepository.findById(city.getState().getId()).orElseThrow(() -> new CustomNotFoundException("State" ,id));
            existingCity.setState(existingState);
        }

        if(city.getDescription() != null) {
            existingCity.setDescription(city.getDescription());
        }
        City updatedCity = cityRepository.save(existingCity);
        return ResponseEntity.ok(updatedCity);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("City" ,id));
        cityRepository.delete(city);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        cityRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
