package com.cardgame.cardgameserver.card.cardType;

import com.cardgame.cardgameserver.card.ability.Ability;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "ability_id")
    private Ability ability;
}
