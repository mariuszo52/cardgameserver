package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.ability.dto.AbilityDto;
import com.cardgame.cardgameserver.card.ability.dto.AbilityShortDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AbilityController {
    private final AbilityService abilityService;

    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @PostMapping("/ability")
    public ResponseEntity<?> createAbility(@RequestBody @Valid AbilityDto ability) {
        try {
            abilityService.save(ability);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/abilities")
    public ResponseEntity<List<AbilityShortDto>> getAllAbilities() {
        List<AbilityShortDto> abilities = abilityService.getAll();
        return ResponseEntity.ok().body(abilities);
    }
}
