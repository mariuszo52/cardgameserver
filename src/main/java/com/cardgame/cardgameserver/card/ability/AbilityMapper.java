package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.Line;
import com.cardgame.cardgameserver.card.ability.dto.AbilityDto;
import com.cardgame.cardgameserver.card.fraction.Fraction;
import com.cardgame.cardgameserver.card.fraction.FractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AbilityMapper {
    private final FractionRepository fractionRepository;

    public AbilityMapper(FractionRepository fractionRepository) {
        this.fractionRepository = fractionRepository;
    }

    public Ability maptoAbility(AbilityDto abilityDto) {
        if (abilityDto == null) {
            return null;
        }
        List<Fraction> fractions = StreamSupport.stream(fractionRepository.findAllById(abilityDto.getTargetFractionIds()).spliterator(),
                        false)
                .toList();
        Line targetLine = abilityDto.getTargetLine() != null ?
                Line.valueOf(abilityDto.getTargetLine()) : null;
        AbilityChangeType changeType = abilityDto.getChangeType() != null ?
                AbilityChangeType.valueOf(abilityDto.getChangeType()) : null;
        Line requiredLine = abilityDto.getRequiredLine() != null ?
                Line.valueOf(abilityDto.getRequiredLine()) : null;
        return Ability.builder()
                .name(abilityDto.getName())
                .targetLine(targetLine)
                .targetCardIds(abilityDto.getTargetCardIds())
                .targetCardFractions(fractions)
                .numberOfTargets(abilityDto.getNumberOfTargets())
                .affectsOwnCards(abilityDto.getAffectsOwnCards())
                .changeType(changeType)
                .changeValue(abilityDto.getChangeValue())
                .changeToValue(abilityDto.getChangeToValue())
                .activeForAttacks(abilityDto.getActiveForAttacks())
                .activeForTurns(abilityDto.getActiveForTurns())
                .affectingArtifactIds(abilityDto.getAffectingArtifactIds())
                .affectingLocationIds(abilityDto.getAffectingLocationIds())
                .requiredLine(requiredLine)
                .requiredCardIdsOnBoard(abilityDto.getRequiredCardIdsOnBoard())
                .requiredCountOnBoard(abilityDto.getRequiredCountOnBoard())
                .requiredHealthToActivate(abilityDto.getRequiredHealthToActivate())
                .waitsBeforeAttacking(abilityDto.getWaitsBeforeAttacking())
                .attacksPerTurn(abilityDto.getAttacksPerTurn())
                .capitalDrawsTwoCards(abilityDto.getCapitalDrawsTwoCards())
                .canAttackHeroInLine2(abilityDto.getCanAttackHeroInLine2())
                .build();
    }
}
