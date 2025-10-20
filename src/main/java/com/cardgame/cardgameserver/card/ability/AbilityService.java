package com.cardgame.cardgameserver.card.ability;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class AbilityService {
    private final AbilityRepository abilityRepository;
    private final AbilityMapper abilityMapper;

    public AbilityService(AbilityRepository abilityRepository, AbilityMapper abilityMapper) {
        this.abilityRepository = abilityRepository;
        this.abilityMapper = abilityMapper;
    }

    public void save(@NotNull AbilityDto ability){
        abilityRepository.save(abilityMapper.maptoAbility(ability));
    }
}
