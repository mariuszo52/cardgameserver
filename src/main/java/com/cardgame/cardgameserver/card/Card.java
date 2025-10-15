//package com.cardgame.cardgameserver.card;
//
//import com.cardgame.cardgameserver.card.ability.Ability;
//import com.cardgame.cardgameserver.card.fraction.Fraction;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Card {
//    @Id
//    @NotNull
//    private Integer id;
//    @NotNull
//    @Size(min = 1, max = 50)
//    private String name;
//    @Enumerated(EnumType.STRING)
//    private CardRarity rarity;
//    @NotNull
//    @ManyToOne
//    private Fraction fraction;
//    @NotNull
//    @NotBlank
//    @Size(min = 1, max = 1000)
//    private String imageLink;
//    @NotNull
//    @NotBlank
//    @Size(min = 1, max = 1000)
//    private String quote;
//    @ManyToMany
//    @JoinTable(name = "card_ability",
//    joinColumns = @JoinColumn(name = "card"),
//    inverseJoinColumns = @JoinColumn(name = "ability"))
//    private List<Ability> abilities = new ArrayList<>();
//    @Enumerated(EnumType.ORDINAL)
//    private PreferredLane preferredLane;
//    @Min(1)
//    @Max(10)
//    private Integer playCost;
//    @Min(1)
//    @Max(10)
//    private Integer addCost;
//
//
//
//}
