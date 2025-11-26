package com.cardgame.cardgameserver.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/files")
public class FilesDownloadController {
    private final FilesDownloadService filesDownloadService;

    public FilesDownloadController(FilesDownloadService filesDownloadService) {
        this.filesDownloadService = filesDownloadService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileInfo>> getAllFilesToDownload() {
        return ResponseEntity.ok().body(filesDownloadService.getFilesToDownload());
    }
}
