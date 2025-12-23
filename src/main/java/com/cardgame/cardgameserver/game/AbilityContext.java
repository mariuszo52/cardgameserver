package com.cardgame.cardgameserver.game;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.game.conditions.AbilityCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class AbilityContext {

    private Ability ability;
    private UUID cardInstanceId;

    private int ownerId;
    private Integer targetPlayerId;

    private List<AbilityCondition> conditions;
}

