package com.eradiuxtech.zgate.portfolioservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio-service/portfolio")
public class PortfolioController {

    @GetMapping()
    public ResponseEntity<String> health(){
        return new  ResponseEntity<String>("Alive",HttpStatus.OK);
    }
}
