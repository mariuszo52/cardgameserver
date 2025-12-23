package com.cardgame.cardgameserver.game.excutors;

import com.cardgame.cardgameserver.game.AbilityContext;
import com.cardgame.cardgameserver.game.GameState;
import com.cardgame.cardgameserver.game.PlayerState;

public class DamageExecutor implements AbilityExecutor {

    @Override
    public void execute(GameState gameState, AbilityContext context) {
        PlayerState targetState = gameState.getPlayers().get(context.getTargetPlayerId());
// todo
}
}
