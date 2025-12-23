package com.cardgame.cardgameserver.game;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PlayerState {
    private int playerId;
    @Min(0)
    @Max(25)
    private int hp = 25;
    @Min(0)
    private int armor = 0;

    private boolean meditatedLastTurn;

    private int fatigueCounter;

    private List<CardInstance> deck = new ArrayList<>();
    private List<CardInstance> hand = new ArrayList<>();
}
