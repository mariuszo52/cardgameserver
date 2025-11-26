package com.cardgame.cardgameserver.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    @Query("SELECT c.imageLink from Card c")
    List<String> getAllCardsUrls();
}
