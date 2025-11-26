package com.cardgame.cardgameserver.File;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileInfo {
    @NotNull
    @NonNull
    private String url;
    @NotNull
    @NonNull
    private String fileName;
}
