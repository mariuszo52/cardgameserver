package com.cardgame.cardgameserver.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardsDataDto {
    private Long cardsVersion;
    private List<CardUIDto> cards;
}
