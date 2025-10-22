package com.cardgame.cardgameserver.card.fraction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FractionDto {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
}
