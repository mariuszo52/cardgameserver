package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.ability.dto.AbilityDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AbilityMapper {

    public static AbilityDto abilityToAbilityDto(@Valid Ability ability){
        if (ability == null) { return null; }
        return AbilityDto.builder()
                .id(ability.getId())
                .name(ability.getName())
                .changeValue(ability.getChangeValue())
                .changeToValue(ability.getChangeToValue())
                .activeForTurns(ability.getActiveForTurns())
                .isMeditation(ability.getIsMeditation()).build();
    }

    public Ability maptoAbility(AbilityDto abilityDto) {
        if (abilityDto == null) {
            return null;
        }
        AbilityChangeType changeType = abilityDto.getChangeType() != null ?
                AbilityChangeType.valueOf(abilityDto.getChangeType()) : null;
        return Ability.builder()
                .name(abilityDto.getName())
                .changeType(changeType)
                .isMeditation(abilityDto.getIsMeditation())
                .changeValue(abilityDto.getChangeValue())
                .changeToValue(abilityDto.getChangeToValue())
                .activeForTurns(abilityDto.getActiveForTurns())
                .build();
    }
}
