package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.card.ability.AbilityRepository;
import com.cardgame.cardgameserver.card.fraction.Fraction;
import com.cardgame.cardgameserver.card.fraction.FractionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CardService {
    private final FractionRepository fractionRepository;
    private final AbilityRepository abilityRepository;
    private final CardRepository cardRepository;

    public CardService(FractionRepository fractionRepository, AbilityRepository abilityRepository, CardRepository cardRepository) {
        this.fractionRepository = fractionRepository;
        this.abilityRepository = abilityRepository;
        this.cardRepository = cardRepository;
    }

    public void save(Integer id, MultipartFile imageFile, String rarity, Integer fractionId,
                     String quote, List<Long> abilitiesIds, String preferredLane, Integer playCost,
                     Integer addCost, String description, Integer health, Integer attack) throws IOException {
        String imageLink = saveFile(imageFile);
        Fraction fraction = fractionRepository.findById(fractionId)
                .orElseThrow(() -> new EntityNotFoundException("Fraction not found"));
        List<Ability> abilities = StreamSupport.stream(abilityRepository.findAllById(abilitiesIds).spliterator(), false)
                .toList();
        Card card = Card.builder()
                .id(id)
                .imageLink(imageLink)
                .rarity(CardRarity.valueOf(rarity))
                .fraction(fraction)
                .quote(quote)
                .addCost(addCost)
                .playCost(playCost)
                .description(description)
                .health(health)
                .attack(attack)
                .preferredLane(PreferredLane.valueOf(preferredLane))
                .abilities(abilities).build();
        cardRepository.save(card);
    }

    private String saveFile(@NotNull MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new IllegalArgumentException("File is empty");
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream("/resources" + file.getOriginalFilename()));
        outputStream.write(file.getBytes());
        return "/resources" + file.getOriginalFilename();
    }
}


