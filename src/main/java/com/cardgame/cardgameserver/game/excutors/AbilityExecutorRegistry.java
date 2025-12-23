package com.cardgame.cardgameserver.game.excutors;

import com.cardgame.cardgameserver.card.ability.AbilityChangeType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AbilityExecutorRegistry {

    private final Map<AbilityChangeType, AbilityExecutor> executors;

    public AbilityExecutorRegistry() {
        executors = Map.of(
                AbilityChangeType.ATTACK, new DamageExecutor(),
                AbilityChangeType.HEALTH, new HealthExecutor()
//                AbilityChangeType.ARMOR, new ,
//                AbilityChangeType.MANA, new
        );
    }

    public AbilityExecutor get(AbilityChangeType type) {
        return executors.get(type);
    }
}
