package com.cardgame.cardgameserver.card.cardFraction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardFractionRepository extends CrudRepository<CardFraction, Long> {
}
