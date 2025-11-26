package com.cardgame.cardgameserver.File;

import com.cardgame.cardgameserver.card.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesDownloadService {
    private final CardRepository cardRepository;

    public FilesDownloadService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    List<FileInfo> getFilesToDownload() {
        return cardRepository.getAllCardsUrls().stream()
                .map(url -> new FileInfo(url, getFileNameFromUrl(url)))
                .toList();
    }

    private String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }
}
