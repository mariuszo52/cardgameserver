package com.cardgame.cardgameserver.card.fraction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FractionRepository extends CrudRepository<Fraction, Integer> {
}
