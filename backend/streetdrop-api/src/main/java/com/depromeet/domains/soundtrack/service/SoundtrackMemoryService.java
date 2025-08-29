package com.depromeet.domains.soundtrack.service;

import com.depromeet.domains.soundtrack.dto.SoundtrackMemoryRequest;
import com.depromeet.domains.soundtrack.dto.SoundtrackMemoryResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SoundtrackMemoryService {

    private final CopyOnWriteArrayList<SoundtrackMemoryResponse> memories = new CopyOnWriteArrayList<>();

    public SoundtrackMemoryResponse create(SoundtrackMemoryRequest request) {
        SoundtrackMemoryResponse memory = new SoundtrackMemoryResponse(
                UUID.randomUUID().toString(),
                request.placeName().trim(),
                request.songTitle().trim(),
                normalize(request.artistName()),
                normalize(request.note()),
                request.latitude(),
                request.longitude(),
                Instant.now()
        );

        memories.add(memory);
        return memory;
    }

    public List<SoundtrackMemoryResponse> findAll() {
        List<SoundtrackMemoryResponse> result = new ArrayList<>(memories);
        result.sort(Comparator.comparing(SoundtrackMemoryResponse::createdAt).reversed());
        return result;
    }

    public List<SoundtrackMemoryResponse> findNearby(
            double latitude,
            double longitude,
            double radiusMeters
    ) {
        return findAll().stream()
                .filter(memory -> distanceMeters(
                        latitude,
                        longitude,
                        memory.latitude(),
                        memory.longitude()
                ) <= radiusMeters)
                .toList();
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    private double distanceMeters(
            double latitudeA,
            double longitudeA,
            double latitudeB,
            double longitudeB
    ) {
        final double earthRadiusMeters = 6_371_000.0;
        double latitudeDelta = Math.toRadians(latitudeB - latitudeA);
        double longitudeDelta = Math.toRadians(longitudeB - longitudeA);
        double startLatitude = Math.toRadians(latitudeA);
        double endLatitude = Math.toRadians(latitudeB);

        double haversine = Math.sin(latitudeDelta / 2) * Math.sin(latitudeDelta / 2)
                + Math.cos(startLatitude)
                * Math.cos(endLatitude)
                * Math.sin(longitudeDelta / 2)
                * Math.sin(longitudeDelta / 2);

        double centralAngle = 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));
        return earthRadiusMeters * centralAngle;
    }
}
