package com.cmb.task.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class ZonedDateService {

    private static final String TIMEZONE_ID = "Europe/Paris";

    public String getCurrentZonedDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(TIMEZONE_ID));
        LocalDate localDate = zonedDateTime.toLocalDate();
        return localDate.toString();
    }
}
