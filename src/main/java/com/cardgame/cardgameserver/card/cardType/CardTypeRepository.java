package com.cardgame.cardgameserver.card.cardType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends CrudRepository<CardType, Long> {
}
