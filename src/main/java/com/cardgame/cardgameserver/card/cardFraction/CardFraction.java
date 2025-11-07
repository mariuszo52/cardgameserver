package com.cardgame.cardgameserver.card.cardFraction;

import com.cardgame.cardgameserver.card.Card;
import com.cardgame.cardgameserver.card.fraction.Fraction;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardFraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Card card;
    @ManyToOne
    private Fraction fraction;
    @NotNull
    @Min(0)
    @Max(10)
    private Integer playCost;
}
