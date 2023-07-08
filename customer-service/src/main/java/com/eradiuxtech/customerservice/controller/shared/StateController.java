package com.eradiuxtech.customerservice.controller.shared;

import com.eradiuxtech.customerservice.dto.request.CreateStateRequestDto;
import com.eradiuxtech.customerservice.dto.request.UpdateStateRequestDto;
import com.eradiuxtech.customerservice.entity.shared.Country;
import com.eradiuxtech.customerservice.entity.shared.State;
import com.eradiuxtech.customerservice.repository.shared.CountryRepository;
import com.eradiuxtech.customerservice.repository.shared.StateRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customer-service/states")
@RequiredArgsConstructor
public class StateController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listStatesWithPagination(
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id,desc") String[] sort){
        LOGGER.info("StateController | ListStatesWithPagination started");

        try {
            List<Order> orders = new ArrayList<Order>();

            if(size>10){
                size = 10;
            }
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<State> pages = stateRepository.findAll(pagingSort);

            List<State>  states = pages.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pages.getNumber());
            response.put("totalItems", pages.getTotalElements());
            response.put("totalPages", pages.getTotalPages());
            response.put("states", states);
            LOGGER.info("StateController | ListStatesWithPagination Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("StateController | ListStatesWithPagination Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<State>> listAllStates(){
        LOGGER.info("StateController | ListAllStates Started");
       try {
           List<State> states = stateRepository.findAll();
           LOGGER.info("StateController | ListAllStates Success");
            return new ResponseEntity<>(states, HttpStatus.OK);
        } catch (Exception e) {
           LOGGER.info("StateController | ListAllStates Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<State>> getStateById(@PathVariable Long id){
        LOGGER.info("StateController | GetStateById Started");
        try {
            Optional<State> state = stateRepository.findById(id);
            if(state.isEmpty()){
                throw new NotFoundException("State Not Found");
            }
            LOGGER.info("StateController | GetStateById Success");
            return new ResponseEntity<>(state, HttpStatus.OK);
        } catch (Exception e){
            LOGGER.info("StateController | GetStateById Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Optional<State>> getStateByCode(@PathVariable String code){
        LOGGER.info("StateController | GetStateByCode Started");
        try {
            Optional<State> state = Optional.ofNullable(stateRepository.findByCode(code));
            if(state.isEmpty()){
                throw new NotFoundException("State Not Found");
            }
            LOGGER.info("StateController | GetStateByCode Success");
            return new ResponseEntity<>(state, HttpStatus.OK);
        } catch (Exception e){
            LOGGER.info("StateController | GetStateByCode Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<String> createState(@Valid @RequestBody CreateStateRequestDto state){
        LOGGER.info("StateController | CreateState Started");
        try {
            Optional<Country> country = Optional.ofNullable(countryRepository.findByCode(state.getCountryCode()));
            if(country.isEmpty()){
                throw new NotFoundException("Invalid Country Provided");
            }
            State _state = new State();
            _state.setName(state.getName());
            _state.setCode(state.getCode());
            _state.setCountry(country.get());
            _state.setDescription(state.getDescription());
            stateRepository.save(_state);
            LOGGER.info("StateController | CreateState Success");
            return new ResponseEntity<String>("State Created Successfully", HttpStatus.OK);
        } catch (Exception e){
            LOGGER.info("StateController | CreateState Error");
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<State> updateState(@PathVariable("id") Long id, @Valid @RequestBody UpdateStateRequestDto state){
        LOGGER.info("StateController | UpdateState Started");
        try {
            Optional<State> stateData = stateRepository.findById(id);
            if (stateData.isEmpty()) {
                throw new NotFoundException("State Not Found");
            }
           Optional<Country> countryData = Optional.ofNullable(countryRepository.findByCode(state.getCountryCode()));
            if (countryData.isEmpty()) {
                throw new NotFoundException("Invalid Country Provided");
            }
            State _state = stateData.get();
            _state.setName(state.getName());
            _state.setCode(state.getCode());
            _state.setCountry(countryData.get());
            _state.setDescription(state.getDescription());

            State newState = stateRepository.save(_state);
            LOGGER.info("StateController | UpdateState Success");
            return new ResponseEntity<State>(newState, HttpStatus.OK);

        } catch (Exception e){
            LOGGER.info("StateController | UpdateState Error");
            throw new BadRequestException(e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteState(@PathVariable("id") Long id){
        LOGGER.info("StateController | DeleteState Started");
        try {
            Optional<State> stateData = stateRepository.findById(id);
            if(stateData.isEmpty()){
                throw new NotFoundException("State Not Found");
            }


           stateRepository.deleteById(id);
            LOGGER.info("StateController | DeleteState success");
            return new ResponseEntity<String>("State Deleted Successfully", HttpStatus.OK );

        } catch (Exception e){
            LOGGER.info("StateController | DeleteState Error");
            throw new BadRequestException(e.getMessage());

        }
    }

}
