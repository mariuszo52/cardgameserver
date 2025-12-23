package com.cardgame.cardgameserver.game.excutors;

import com.cardgame.cardgameserver.game.AbilityContext;
import com.cardgame.cardgameserver.game.GameState;

public interface AbilityExecutor {
    void execute(GameState gameState, AbilityContext context);
}
