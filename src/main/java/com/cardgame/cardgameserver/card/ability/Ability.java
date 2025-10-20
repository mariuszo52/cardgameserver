package com.cardgame.cardgameserver.card.ability;

import com.cardgame.cardgameserver.card.cardType.CardType;
import com.cardgame.cardgameserver.card.Line;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Ability
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private Line targetLine;
    @OneToMany(mappedBy = "ability", fetch = FetchType.EAGER)
    private List<CardType> targetCardTypes = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> targetCardIds = new ArrayList<>();
    @Min(1)
    @Max(10)
    private Integer numberOfTargets;
    private Boolean affectsOwnCards;
    @Enumerated(EnumType.STRING)
    private AbilityChangeType changeType;
    @Min(1)
    @Max(10)
    private Integer changeValue;
    @Min(0)
    @Max(100)
    private Integer changeToValue;

    private Integer activeForAttacks;
    private Integer activeForTurns;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> affectingArtifactIds = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> affectingLocationIds  = new ArrayList<>();
    @Enumerated(EnumType.ORDINAL)
    private Line requiredLine;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> requiredCardIdsOnBoard = new ArrayList<>();
    @Min(1)
    @Max(10)
    private Integer requiredCountOnBoard;
    @Min(1)
    @Max(100)
    private Integer requiredHealthToActivate;

    private Boolean waitsBeforeAttacking;
    @Min(1)
    @Max(100)
    private Integer attacksPerTurn;
    private Boolean capitalDrawsTwoCards;

    private Boolean canAttackHeroInLine2;

}
