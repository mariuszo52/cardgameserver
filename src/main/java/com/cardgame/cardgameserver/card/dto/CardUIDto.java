package com.cardgame.cardgameserver.card.dto;

import com.cardgame.cardgameserver.card.ability.dto.AbilityDto;
import com.cardgame.cardgameserver.card.fraction.FractionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardUIDto {
    private Integer id;
    private String serialNumber;
    private String name;
    private String rarity;
    private String cardType;
    private List<FractionDto> fractions;
    private String imageLink;
    private List<AbilityDto> abilities;
    private String description;
    private Integer attack;
}
