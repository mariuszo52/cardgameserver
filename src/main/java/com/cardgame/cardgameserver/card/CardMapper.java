package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.ability.AbilityMapper;
import com.cardgame.cardgameserver.card.dto.CardUIDto;
import com.cardgame.cardgameserver.card.fraction.FractionMapper;
import jakarta.validation.Valid;

public class CardMapper {

    public static CardUIDto cartToCardUIDto(@Valid Card card) {
        if (card == null) return null;
        return CardUIDto.builder()
                .id(card.getId())
                .name(card.getName())
                .rarity(card.getRarity().name())
                .cardType(card.getCardType().name())
                .fractions(card.getFractions().stream().map(FractionMapper::fractionToFractionDto).toList())
                .imageLink(card.getImageLink())
                .abilities(card.getAbilities().stream().map(AbilityMapper::abilityToAbilityDto).toList())
                .description(card.getDescription())
                .serialNumber(card.getSerialNumber())
                .attack(card.getAttack()).build();
    }
}
