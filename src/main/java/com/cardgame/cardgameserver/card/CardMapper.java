package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.card.fraction.Fraction;
import jakarta.validation.Valid;

import java.util.List;

public class CardMapper {

    public static CardDto cartToCardDto(@Valid Card card) {
        if (card == null) return null;
        List<String> abilitiesNames = card.getAbilities().stream()
                .map(Ability::getName)
                .toList();
        return CardDto.builder()
                .id(card.getId())
                .name(card.getName())
                .rarity(card.getRarity().name())
                .cardType(card.getCardType().name())
                .fractions(card.getFractions().stream().map(Fraction::getName).toList())
                .imageLink(card.getImageLink())
                .abilitiesNames(abilitiesNames)
                .description(card.getDescription())
                .serialNumber(card.getSerialNumber())
                .attack(card.getAttack()).build();
    }
}
