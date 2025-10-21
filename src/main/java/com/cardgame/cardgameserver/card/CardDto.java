package com.cardgame.cardgameserver.card;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @Enumerated(EnumType.STRING)
    private String rarity;
    @NotNull
    @ManyToOne
    private String fraction;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String imageLink;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String quote;
    private List<String> abilitiesNames = new ArrayList<>();
    private String preferredLane;
    @Min(1)
    @Max(10)
    private Integer playCost;
    @Min(1)
    @Max(10)
    private Integer addCost;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;
    @NotNull
    private Integer health;
    @NotNull
    private Integer attack;



}
