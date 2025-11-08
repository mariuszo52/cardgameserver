package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.cardFraction.CardFractionDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CardController {
    private final CardService cardService;
    private final ObjectMapper objectMapper;

    public CardController(CardService cardService, ObjectMapper objectMapper) {
        this.cardService = cardService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardDto> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @PostMapping("/card")
    public ResponseEntity<?> createCard(
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("rarity") String rarity,
            @RequestParam("fractionsCosts") String fractionsCosts,
            @RequestParam("cardType") String cardType,
            @RequestParam("abilitiesIds") List<Long> abilitiesIds,
            @RequestParam("hasEchoOfMeditation") Boolean hasEchoOfMeditation,
            @RequestParam("description") String description,
            @RequestParam("attack") Integer attack
    ) {
        try {
            List<CardFractionDto> fc = objectMapper.readValue(fractionsCosts,
                    new TypeReference<>() {
                    });
            cardService.save(id, name, imageFile, rarity, fc, cardType, abilitiesIds, hasEchoOfMeditation,
                    description, attack);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
