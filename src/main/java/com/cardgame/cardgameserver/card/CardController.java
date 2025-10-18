package com.cardgame.cardgameserver.card;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    ResponseEntity<?> createCard(
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("id") Integer id,
            @RequestParam("rarity") String rarity,
            @RequestParam("fraction") Integer fractionId,
            @RequestParam("quote") String quote,
            @RequestParam("abilitiesIds") List<Long> abilitiesIds,
            @RequestParam("preferredLane") String preferredLane,
            @RequestParam("playCost") Integer playCost,
            @RequestParam("addCost") Integer addCost,
            @RequestParam("description") String description,
            @RequestParam("health") Integer health,
            @RequestParam("attack") Integer attack
    ) {
        try {
            cardService.save(id, imageFile, rarity, fractionId, quote, abilitiesIds, preferredLane, playCost,
                    addCost, description, health, attack);
        }catch (IOException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
