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
                .fraction(card.getFraction().getName())
                .imageLink(card.getImageLink())
                .quote(card.getQuote())
                .abilitiesNames(abilitiesNames)
                .preferredLane(card.getPreferredLane().name())
                .playCost(card.getPlayCost())
                .addCost(card.getAddCost())
                .description(card.getDescription())
                .health(card.getHealth())
                .attack(card.getAttack()).build();
    }
}
