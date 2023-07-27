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
@RequestMapping("/api/customer-service/v1/states")
public class StateController {

    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateController(StateRepository stateRepository, CountryRepository countryRepository,
                           CityRepository cityRepository
                          ) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }


    @GetMapping
    public Iterable<State> listAll() {
        return stateRepository.findAll();
    }


    @GetMapping("/paginate")
    public ResponseEntity<Iterable<State>> listAllWithPagination( @RequestParam(defaultValue = "0") final Integer page,
                                                    @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(stateRepository.getStates(PageRequest.of(page, size)));
    }



    @GetMapping("/{id}")
    public ResponseEntity<State> getById(@PathVariable Long id) {
        return stateRepository.findById(id)
                         .map(ResponseEntity::ok)
                         .orElseThrow(() -> new CustomNotFoundException("State", id));
    }


    @GetMapping("/country/{id}")
    public ResponseEntity<Iterable<State>> listAllByCountry(@PathVariable Long id) {
        return ResponseEntity.ok(stateRepository.listByCountryId(id));
    }


    @GetMapping("/search")
    public ResponseEntity<State> search(@RequestParam(required = false) String code, @RequestParam(required = false) String name) {
        if(code.isEmpty() && name.isEmpty()) {
            throw new BadRequestException("Provide Either code or name to search");
        }
        if(!code.isEmpty() && !name.isEmpty()) {
            throw new BadRequestException("Provide Either code or name to search");
        }

        State state = null;
        if(!code.isEmpty()) {
            state = stateRepository.findByCode(code);
        }

        if(!name.isEmpty()){
            state = stateRepository.findByName(name);
        }

        return ResponseEntity.ok(state);
    }
    @PostMapping
    public ResponseEntity<State> save(@Valid @RequestBody State country) {
        country.setId(null);
        State savedState = stateRepository.save(country);
        return new ResponseEntity<State>(savedState, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @Valid @RequestBody State state) {
        State existingState = stateRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("State" ,id));
        if(state.getName() != null) {
            existingState.setName(state.getName());
        }
        if(state.getCode() != null) {
            existingState.setCode(state.getCode());
        }
        if(state.getCountry() != null) {
            Country existingCountry = countryRepository.findById(state.getCountry().getId()).orElseThrow(() -> new CustomNotFoundException("Country" ,id));
            existingState.setCountry(existingCountry);
        }

        if(state.getDescription() != null) {
            existingState.setDescription(state.getDescription());
        }
        State updatedState = stateRepository.save(existingState);
        return ResponseEntity.ok(updatedState);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        State state = stateRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Country" ,id));
        stateRepository.delete(state);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        stateRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
