package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.card.fraction.Fraction;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Card {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String serialNumber;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @Enumerated(EnumType.STRING)
    private CardRarity rarity;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @NotNull
    @ManyToMany
    @JoinTable(name = "card_fraction",
    joinColumns = @JoinColumn(name = "card_id"),
    inverseJoinColumns = @JoinColumn(name = "fraction_id"))
    private List<Fraction> fractions = new ArrayList<>();
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String imageLink;
    @ManyToMany
    @JoinTable(name = "card_ability",
    joinColumns = @JoinColumn(name = "card_id"),
    inverseJoinColumns = @JoinColumn(name = "ability_id"))
    private List<Ability> abilities = new ArrayList<>();

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;
    @NotNull
    private Integer attack;
}
