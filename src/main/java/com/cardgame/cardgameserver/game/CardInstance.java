package com.cardgame.cardgameserver.game;

import com.cardgame.cardgameserver.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CardInstance {
        private UUID id;
        private Card definition;
    }

