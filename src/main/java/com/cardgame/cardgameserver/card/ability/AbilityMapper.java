package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.Line;
import com.cardgame.cardgameserver.card.cardType.CardType;
import com.cardgame.cardgameserver.card.cardType.CardTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AbilityMapper {
    private final CardTypeRepository cardTypeRepository;

    public AbilityMapper(CardTypeRepository cardTypeRepository) {
        this.cardTypeRepository = cardTypeRepository;
    }


    public Ability maptoAbility(AbilityDto abilityDto) {
        if (abilityDto == null) {
            return null;
        }
        List<CardType> cardTypes = StreamSupport.stream(cardTypeRepository.findAllById(abilityDto.getTargetCardTypesIds()).spliterator(), false)
                .toList();
        return Ability.builder()
                .name(abilityDto.getName())
                .targetLine(Line.valueOf(abilityDto.getTargetLine()))
                .targetCardTypes(cardTypes)
                .targetCardIds(abilityDto.getTargetCardIds())
                .numberOfTargets(abilityDto.getNumberOfTargets())
                .affectsOwnCards(abilityDto.getAffectsOwnCards())
                .changeType(AbilityChangeType.valueOf(abilityDto.getChangeType()))
                .changeValue(abilityDto.getChangeValue())
                .changeToValue(abilityDto.getChangeToValue())
                .activeForAttacks(abilityDto.getActiveForAttacks())
                .activeForTurns(abilityDto.getActiveForTurns())
                .affectingArtifactIds(abilityDto.getAffectingArtifactIds())
                .affectingLocationIds(abilityDto.getAffectingLocationIds())
                .requiredLine(Line.valueOf(abilityDto.getRequiredLine()))
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
