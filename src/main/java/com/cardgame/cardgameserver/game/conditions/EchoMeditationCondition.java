package com.cardgame.cardgameserver.game.conditions;

import com.cardgame.cardgameserver.game.AbilityContext;
import com.cardgame.cardgameserver.game.GameState;

public class EchoMeditationCondition implements AbilityCondition {
    @Override
    public boolean isSatisfied(GameState gameState, AbilityContext context) {
        return gameState.getPlayers()
                .get(context.getOwnerId())
                .isMeditatedLastTurn();
    }
}
