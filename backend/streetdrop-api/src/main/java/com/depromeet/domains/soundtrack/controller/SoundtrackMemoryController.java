package com.depromeet.domains.soundtrack.controller;

import com.depromeet.domains.soundtrack.dto.SoundtrackMemoryRequest;
import com.depromeet.domains.soundtrack.dto.SoundtrackMemoryResponse;
import com.depromeet.domains.soundtrack.service.SoundtrackMemoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/soundtrack-memories")
@RequiredArgsConstructor
@Tag(name = "🎧 Soundtrack Memory", description = "장소와 음악을 개인 기억으로 연결하는 API")
public class SoundtrackMemoryController {

    private final SoundtrackMemoryService soundtrackMemoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SoundtrackMemoryResponse create(@Valid @RequestBody SoundtrackMemoryRequest request) {
        return soundtrackMemoryService.create(request);
    }

    @GetMapping
    public List<SoundtrackMemoryResponse> findAll() {
        return soundtrackMemoryService.findAll();
    }

    @GetMapping("/nearby")
    public List<SoundtrackMemoryResponse> findNearby(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "250") double radiusMeters
    ) {
        return soundtrackMemoryService.findNearby(latitude, longitude, radiusMeters);
    }
}
