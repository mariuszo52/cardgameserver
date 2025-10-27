package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.ability.dto.AbilityDto;
import com.cardgame.cardgameserver.card.ability.dto.AbilityShortDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

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

    public List<AbilityShortDto> getAll() {
        return StreamSupport.stream(abilityRepository.findAll().spliterator(), false)
                .map(a -> new AbilityShortDto(a.getId(), a.getName()))
                .toList();

    }
}
