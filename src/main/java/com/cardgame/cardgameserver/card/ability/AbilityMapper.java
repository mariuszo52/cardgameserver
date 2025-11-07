package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.ability.dto.AbilityDto;
import org.springframework.stereotype.Service;

@Service
public class AbilityMapper {

    public Ability maptoAbility(AbilityDto abilityDto) {
        if (abilityDto == null) {
            return null;
        }
        AbilityChangeType changeType = abilityDto.getChangeType() != null ?
                AbilityChangeType.valueOf(abilityDto.getChangeType()) : null;
        return Ability.builder()
                .name(abilityDto.getName())
                .changeType(changeType)
                .changeValue(abilityDto.getChangeValue())
                .changeToValue(abilityDto.getChangeToValue())
                .activeForTurns(abilityDto.getActiveForTurns())
                .build();
    }
}
