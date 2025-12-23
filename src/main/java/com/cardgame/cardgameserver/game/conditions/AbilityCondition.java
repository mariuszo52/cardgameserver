package com.cardgame.cardgameserver.game.conditions;

import com.cardgame.cardgameserver.game.AbilityContext;
import com.cardgame.cardgameserver.game.GameState;

public interface AbilityCondition {
    boolean isSatisfied(GameState gameState, AbilityContext context);
}
