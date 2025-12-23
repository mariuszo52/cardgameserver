package com.cardgame.cardgameserver.card.ability;

import jakarta.validation.ValidationException;

public class AbilityValidator {
    public static void validateAbility(Ability ability) {
        boolean changeValueSet = ability.getChangeValue() != null;
        boolean changeToValueSet = ability.getChangeToValue() != null;
        if (changeValueSet == changeToValueSet) {
            throw new ValidationException(
                    "Ability must have exactly one of changeValue or changeToValue set."
            );
        }
    }
}
