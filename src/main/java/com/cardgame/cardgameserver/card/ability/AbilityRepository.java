package com.cardgame.cardgameserver.card.ability;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends CrudRepository<Ability, Long> {
}
