package com.cardgame.cardgameserver.card.fraction;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class FractionService {
    private final FractionRepository fractionRepository;

    public FractionService(FractionRepository fractionRepository) {
        this.fractionRepository = fractionRepository;
    }

    public void save(@NotNull String name) {
        fractionRepository.save(new Fraction(name));
    }
}
