package com.cardgame.cardgameserver.card.fraction;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FractionService {
    private final FractionRepository fractionRepository;

    public FractionService(FractionRepository fractionRepository) {
        this.fractionRepository = fractionRepository;
    }

    public void save(@NotNull String name) {
        fractionRepository.save(new Fraction(name));
    }

    public List<FractionDto> getAllFractions() {
        return StreamSupport.stream(fractionRepository.findAll().spliterator(), false)
                .map(f -> new FractionDto(f.getId(), f.getName()))
                .collect(Collectors.toList());
    }
}
