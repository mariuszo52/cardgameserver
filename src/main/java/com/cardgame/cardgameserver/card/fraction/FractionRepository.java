package com.cardgame.cardgameserver.card.fraction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FractionRepository extends CrudRepository<Fraction, Integer> {
    Optional<Fraction> findFractionByName(String name);
}
