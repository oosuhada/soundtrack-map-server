package com.depromeet.domains.soundtrack.dto;

import java.time.Instant;

public record SoundtrackMemoryResponse(
        String id,
        String placeName,
        String songTitle,
        String artistName,
        String note,
        Double latitude,
        Double longitude,
        Instant createdAt
) {
}
