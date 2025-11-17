package com.cardgame.cardgameserver.card;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private String serialNumber;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @Enumerated(EnumType.STRING)
    private String rarity;
    private String cardType;
    @NotNull
    private List<String> fractions;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String imageLink;
    @NotNull
    @NotBlank
    private List<String> abilitiesNames = new ArrayList<>();
    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;
    @NotNull
    private Integer health;
    @NotNull
    private Integer attack;
}
