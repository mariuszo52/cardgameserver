package com.cardgame.cardgameserver.card;

import com.cardgame.cardgameserver.card.ability.Ability;
import com.cardgame.cardgameserver.card.ability.AbilityRepository;
import com.cardgame.cardgameserver.card.cardFraction.CardFraction;
import com.cardgame.cardgameserver.card.cardFraction.CardFractionDto;
import com.cardgame.cardgameserver.card.cardFraction.CardFractionRepository;
import com.cardgame.cardgameserver.card.dto.CardUIDto;
import com.cardgame.cardgameserver.card.dto.CardsDataDto;
import com.cardgame.cardgameserver.card.fraction.Fraction;
import com.cardgame.cardgameserver.card.fraction.FractionRepository;
import com.cardgame.cardgameserver.configuration.databaseVersions.DatabaseVersionRepository;
import com.cardgame.cardgameserver.configuration.databaseVersions.Version;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
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
    private final DatabaseVersionRepository databaseVersionRepository;
    @Value(value = "${UPLOADS_LOCATION}")
    private String UPLOADS_LOCATION;
    @Value(value = "${APP_SERVER}")
    private String APP_SERVER;

    public CardService(FractionRepository fractionRepository, AbilityRepository abilityRepository, CardRepository cardRepository, CardFractionRepository cardFractionRepository, DatabaseVersionRepository databaseVersionRepository) {
        this.fractionRepository = fractionRepository;
        this.abilityRepository = abilityRepository;
        this.cardRepository = cardRepository;
        this.cardFractionRepository = cardFractionRepository;
        this.databaseVersionRepository = databaseVersionRepository;
    }

    @Transactional
    public void save(Integer id, String name, MultipartFile imageFile, String rarity, List<CardFractionDto> fractionCosts,
                     String cardType, List<Long> abilitiesIds, String serialNumber, String description, Integer attack) throws IOException {
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
                .serialNumber(serialNumber)
                .abilities(abilities).build();
        cardRepository.save(card);
        fractionCosts.forEach(fractionCost -> {
            Fraction fraction = fractionRepository.findFractionByName(fractionCost.getFractionName())
                    .orElseThrow(() -> new IllegalArgumentException("No such fraction"));
            CardFraction cardFraction = new CardFraction(null, card, fraction, fractionCost.getCost());
            cardFractionRepository.save(cardFraction);
            Version version = databaseVersionRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("No such database version"));
            version.setCardVersion(version.getCardVersion() + 1);
        });
    }

    private String saveFile(@NotNull MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new IllegalArgumentException("File is empty");
        File cardsFolder = new File(UPLOADS_LOCATION + "cards/");
        if (!cardsFolder.exists()) {
            cardsFolder.mkdirs();
        }
        File dest = new File(cardsFolder, file.getOriginalFilename());
        try (BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(dest))) {
            outputStream.write(file.getBytes());
        }
        return APP_SERVER + "/cards/" + file.getOriginalFilename();
    }


    @Transactional(readOnly = true)
    public CardsDataDto getAllCardsData() {
        Long cardsVersion = databaseVersionRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("No such database version"))
                .getCardVersion();
        List<CardUIDto> cardUIDtoList = StreamSupport.stream(cardRepository.findAll().spliterator(), false)
                .map(CardMapper::cartToCardUIDto)
                .toList();
        return new CardsDataDto(cardsVersion, cardUIDtoList);
    }
}


