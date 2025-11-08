package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.card.ability.AbilityRepository;
import com.cardgame.cardgameserver.card.cardFraction.CardFraction;
import com.cardgame.cardgameserver.card.cardFraction.CardFractionDto;
import com.cardgame.cardgameserver.card.cardFraction.CardFractionRepository;
import com.cardgame.cardgameserver.card.fraction.Fraction;
import com.cardgame.cardgameserver.card.fraction.FractionRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CardService {
    private final FractionRepository fractionRepository;
    private final AbilityRepository abilityRepository;
    private final CardRepository cardRepository;
    private final CardFractionRepository cardFractionRepository;

    public CardService(FractionRepository fractionRepository, AbilityRepository abilityRepository, CardRepository cardRepository, CardFractionRepository cardFractionRepository) {
        this.fractionRepository = fractionRepository;
        this.abilityRepository = abilityRepository;
        this.cardRepository = cardRepository;
        this.cardFractionRepository = cardFractionRepository;
    }

    @Transactional
    public void save(Integer id, String name, MultipartFile imageFile, String rarity, List<CardFractionDto> fractionCosts,
                     String cardType, List<Long> abilitiesIds, Boolean hasEchoOfMeditation, String description, Integer attack) throws IOException {
        String imageLink = saveFile(imageFile);
        List<Ability> abilities = StreamSupport.stream(abilityRepository.findAllById(abilitiesIds).spliterator(), false)
                .toList();
        Card card = Card.builder()
                .id(id)
                .name(name)
                .imageLink(imageLink)
                .cardType(CardType.valueOf(cardType))
                .rarity(CardRarity.valueOf(rarity))
                .description(description)
                .attack(attack)
                .hasEchoOfMeditation(hasEchoOfMeditation)
                .abilities(abilities).build();
        cardRepository.save(card);
        fractionCosts.forEach(fractionCost -> {
            Fraction fraction = fractionRepository.findFractionByName(fractionCost.getFractionName())
                    .orElseThrow(() -> new IllegalArgumentException("No such fraction"));
            CardFraction cardFraction = new CardFraction(null, card, fraction, fractionCost.getCost());
            cardFractionRepository.save(cardFraction);
        });
    }

    private String saveFile(@NotNull MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new IllegalArgumentException("File is empty");
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream("src/main/resources/" + file.getOriginalFilename()));
        outputStream.write(file.getBytes());
        return "src/main/resources/" + file.getOriginalFilename();
    }

    @Transactional(readOnly = true)
    public List<CardDto> getAllCards() {
        return StreamSupport.stream(cardRepository.findAll().spliterator(), false)
                .map(CardMapper::cartToCardDto)
                .toList();
    }
}


