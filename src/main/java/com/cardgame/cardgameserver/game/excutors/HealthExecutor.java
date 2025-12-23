package com.cardgame.cardgameserver.game.excutors;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.card.ability.AbilityValidator;
import com.cardgame.cardgameserver.game.AbilityContext;
import com.cardgame.cardgameserver.game.GameState;
import com.cardgame.cardgameserver.game.PlayerState;

public class HealthExecutor implements AbilityExecutor {
    @Override
    public void execute(GameState gameState, AbilityContext context) {
        PlayerState owner = gameState.getPlayers().get(context.getOwnerId());
        Ability ability = context.getAbility();
        AbilityValidator.validateAbility(ability);
        int newHp = ability.getChangeValue() != null
                ? owner.getHp() + ability.getChangeValue()
                : ability.getChangeToValue();
        owner.setHp(Math.min(newHp, 25));
    }
}
