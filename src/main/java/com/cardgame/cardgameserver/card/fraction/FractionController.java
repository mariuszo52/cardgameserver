package com.cardgame.cardgameserver.card.fraction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FractionController {
    private final FractionService fractionService;

    public FractionController(FractionService fractionService) {
        this.fractionService = fractionService;
    }

    @PostMapping("/fraction")
    public ResponseEntity<Fraction> createFraction(@RequestParam String name) {
        try {
            fractionService.save(name);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/fractions")
        public ResponseEntity<List<FractionDto>> getAllFractions(){
            List<FractionDto> fractions = fractionService.getAllFractions();
            return ResponseEntity.ok(fractions);
        }
    }

