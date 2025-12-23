package com.cardgame.cardgameserver.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class GameState {

    private Map<Integer, PlayerState> players;
    private int activePlayerId;
    private GamePhase phase;
    Queue<AbilityContext> resolvingQueue = new LinkedList<>();
}

