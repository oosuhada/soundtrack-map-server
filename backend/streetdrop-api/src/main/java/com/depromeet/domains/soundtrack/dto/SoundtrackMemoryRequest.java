package com.depromeet.domains.soundtrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SoundtrackMemoryRequest(
        @NotBlank String placeName,
        @NotBlank String songTitle,
        String artistName,
        String note,
        @NotNull Double latitude,
        @NotNull Double longitude
) {
}
