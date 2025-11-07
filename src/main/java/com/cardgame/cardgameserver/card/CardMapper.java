package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.ability.Ability;
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
                .fractions(card.getFractions().stream().map(fraction -> fraction.getName()).toList())
                .imageLink(card.getImageLink())
                .abilitiesNames(abilitiesNames)
                .description(card.getDescription())
                .hasEchoOfMeditation(card.getHasEchoOfMeditation())
                .attack(card.getAttack()).build();
    }
}
