package com.warmhouse.telemetryservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TelemetryController {

    private final JdbcTemplate jdbcTemplate;

    public TelemetryController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/telemetry")
    public ResponseEntity<Map<String, Object>> saveTelemetry(@RequestBody Map<String, Object> telemetry) {
        Long deviceId = ((Number) telemetry.get("device_id")).longValue();
        String type = (String) telemetry.get("type");
        Double value = ((Number) telemetry.get("value")).doubleValue();

        jdbcTemplate.update(
            "INSERT INTO telemetry_data (device_id, type, value) VALUES (?, ?, ?)",
            deviceId, type, value
        );

        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}

