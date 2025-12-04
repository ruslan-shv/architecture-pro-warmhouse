package com.warmhouse.deviceservice.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DeviceController {

    private final JdbcTemplate jdbcTemplate;

    public DeviceController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/devices")
    public List<Map<String, Object>> getDevices(@RequestParam("user_id") Long userId) {
        return jdbcTemplate.queryForList(
            "SELECT id, name, type, status, created_at FROM devices WHERE user_id = ?",
            userId
        );
    }
}

