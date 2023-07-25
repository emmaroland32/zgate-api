package com.eradiuxtech.customerservice.controller;


import com.eradiuxtech.customerservice.entity.Country;
import com.eradiuxtech.customerservice.entity.State;
import com.eradiuxtech.customerservice.exception.NotFoundException;
import com.eradiuxtech.customerservice.repository.CountryRepository;
import com.eradiuxtech.customerservice.repository.StateRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer-service/v1/states")
public class StateController {

    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateController(StateRepository stateRepository, CountryRepository countryRepository) {
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
                         .orElseThrow(() -> new NotFoundException("State", id));
    }



    @PostMapping
    public ResponseEntity<State> save(@Valid @RequestBody State country) {
        country.setId(null);
        State savedState = stateRepository.save(country);
        return new ResponseEntity<State>(savedState, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @Valid @RequestBody State state) {
        State existingState = stateRepository.findById(id).orElseThrow(() -> new NotFoundException("State" ,id));
        if(state.getName() != null) {
            existingState.setName(state.getName());
        }
        if(state.getCode() != null) {
            existingState.setCode(state.getCode());
        }
        if(state.getCountry() != null) {
            Country existingCountry = countryRepository.findById(state.getCountry().getId()).orElseThrow(() -> new NotFoundException("Country" ,id));
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
        State state = stateRepository.findById(id).orElseThrow(() -> new NotFoundException("Country" ,id));
        stateRepository.delete(state);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        stateRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
