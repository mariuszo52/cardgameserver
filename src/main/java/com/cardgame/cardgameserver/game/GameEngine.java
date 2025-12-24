package com.cardgame.cardgameserver.game;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.game.conditions.AbilityCondition;
import com.cardgame.cardgameserver.game.conditions.EchoMeditationCondition;
import com.cardgame.cardgameserver.game.excutors.AbilityExecutorRegistry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GameEngine {
    private final GameState gameState;
    private final AbilityExecutorRegistry abilityExecutorRegistry;


    void playCard(CardInstance cardInstance, int targetPlayerId){
        for (Ability ability : cardInstance.getDefinition().getAbilities()) {
            AbilityContext abilityContext = new AbilityContext(
                    ability,
                    cardInstance.getId(),
                    gameState.getActivePlayerId(),
                    targetPlayerId,
                    buildConditions(ability));
            gameState.getResolvingQueue().add(abilityContext);
        }
        gameState.setPhase(GamePhase.RESOLVING);
    }

    void resolve(){
        while (!gameState.getResolvingQueue().isEmpty()){
            AbilityContext ctx = gameState.getResolvingQueue().poll();
            boolean isOk = ctx.getConditions()
                    .stream()
                    .allMatch(c -> c.isSatisfied(gameState, ctx));
            if(!isOk) continue;
            abilityExecutorRegistry.get(ctx.getAbility().getChangeType())
                    .execute(gameState, ctx);
        }
        gameState.setPhase(GamePhase.TURN_END);
    }

    List<AbilityCondition> buildConditions(Ability ability){
        List<AbilityCondition> conditions = new ArrayList<>();
        if(ability.getIsMeditation()){
            conditions.add(new EchoMeditationCondition());
        }
        return conditions;
    }
}
