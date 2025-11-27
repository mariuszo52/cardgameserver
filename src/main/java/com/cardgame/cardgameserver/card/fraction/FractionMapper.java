package com.cardgame.cardgameserver.card.fraction;

import jakarta.validation.Valid;

public class FractionMapper {

    public static FractionDto fractionToFractionDto(@Valid Fraction fraction) {
        if (fraction == null) { return null; }
        return new FractionDto(fraction.getId(), fraction.getName());
    }
}
