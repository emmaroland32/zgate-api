package com.eradiuxtech.customerservice.controller.shared;

import com.eradiuxtech.customerservice.dto.request.CreateCountryRequestDto;
import com.eradiuxtech.customerservice.dto.request.UpdateCountryRequestDto;
import com.eradiuxtech.customerservice.entity.shared.Country;
import com.eradiuxtech.customerservice.repository.shared.CountryRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.*;

@RestController
@RequestMapping("/api/customer-service/countries")
@RequiredArgsConstructor
public class CountryController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final CountryRepository countryRepository;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listCountriesWithPagination(
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id,desc") String[] sort){
        LOGGER.info("CountryController | ListCountriesWithPagination started");

        try {
            List<Order> orders = new ArrayList<Order>();

            List<Country> countries;

            if(size>10){
                size = 10;
            }

            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Country> pageCountries = countryRepository.findAll(pagingSort);

            countries = pageCountries.getContent();


            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageCountries.getNumber());
            response.put("totalItems", pageCountries.getTotalElements());
            response.put("totalPages", pageCountries.getTotalPages());
            response.put("countries", countries);
            LOGGER.info("CountryController | ListCountriesWithPagination Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("CountryController | ListCountriesWithPagination Error");

            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Country>> listAllCountries(){
        LOGGER.info("CountryController | ListAllCountries Started");
       try {
           List<Country> countries = countryRepository.findAll();
           LOGGER.info("CountryController | ListAllCountries Success");
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
           LOGGER.info("CountryController | ListAllCountries Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Country>> getCountryById(@PathVariable Long id){
        LOGGER.info("CountryController | GetCountryById Started");
        try {
            Optional<Country> country = countryRepository.findById(id);
            if(country.isEmpty()){
                throw new NotFoundException("Country Not Found");
            }
            LOGGER.info("CountryController | GetCountryById Success");
            return new ResponseEntity<>(country, HttpStatus.OK);
        } catch (Exception e){
            LOGGER.info("CountryController | GetCountryById Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<Country>> getCountryByCode(@PathVariable String code){
        LOGGER.info("CountryController | GetCountryByCode Started");
        try {
            Optional<Country> country = Optional.ofNullable(countryRepository.findByCode(code));
            if(country.isEmpty()){
                throw new NotFoundException("Country Not Found");
            }
            LOGGER.info("CountryController | GetCountryByCode Success");
            return new ResponseEntity<>(country, HttpStatus.OK);
        } catch (Exception e){
            LOGGER.info("CountryController | GetCountryByCode Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<String> createCountry(@Valid @RequestBody CreateCountryRequestDto country){
        LOGGER.info("CountryController | CreateCountry Started");
        try {
            Country _country = new Country();
            _country.setName(country.getName());
            _country.setCode(country.getCode());
            _country.setPhoneCode(country.getPhoneCode());
            _country.setDescription(country.getDescription());
            countryRepository.save(_country);
            LOGGER.info("CountryController | CreateCountry Success");
            return new ResponseEntity<String>("Country Created Successfully", HttpStatus.OK);
        } catch (Exception e){
            LOGGER.info("CountryController | CreateCountry Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Long id, @Valid @RequestBody UpdateCountryRequestDto country){
        LOGGER.info("CountryController | UpdateCountry Started");
        try {
            Optional<Country> countryData = countryRepository.findById(id);
            if(countryData.isEmpty()){
                throw new NotFoundException("Country Not Found");
            }
            Country _country = countryData.get();
            _country.setName(country.getName());
            _country.setCode(country.getCode());
            _country.setPhoneCode(country.getPhoneCode());
            _country.setDescription(country.getDescription());

             Country newCountry =   countryRepository.save(_country);
            LOGGER.info("CountryController | UpdateCountry Success");
            return new ResponseEntity<Country>(newCountry, HttpStatus.OK );

        } catch (Exception e){
            LOGGER.info("CountryController | UpdateCountry Error");
            throw new BadRequestException(e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") Long id){
        LOGGER.info("CountryController | DeleteCountry Started");
        try {
            Optional<Country> countryData = countryRepository.findById(id);
            if(countryData.isEmpty()){
                throw new NotFoundException("Country Not Found");
            }


           countryRepository.deleteById(id);
            LOGGER.info("CountryController | DeleteCountry success");
            return new ResponseEntity<String>("Country Deleted Successfully", HttpStatus.OK );

        } catch (Exception e){
            LOGGER.info("CountryController | DeleteCountry Error");
            throw new BadRequestException(e.getMessage());

        }
    }

}
