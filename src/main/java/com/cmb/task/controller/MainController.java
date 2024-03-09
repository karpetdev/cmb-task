package com.cmb.task.controller;

import com.cmb.task.service.ValueRetrievalService;
import com.cmb.task.service.ZonedDateService;
import com.cmb.task.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final ValueRetrievalService valueRetrievalService;

    private final ZonedDateService zonedDateService;

    @Autowired
    public MainController(final ValueRetrievalService valueRetrievalService, final ZonedDateService zonedDateService) {
        this.valueRetrievalService = valueRetrievalService;
        this.zonedDateService = zonedDateService;
    }

    @GetMapping(path="/home", produces = "application/json")
    public ResponseDto getResponse(@RequestParam(required = false) String queryParam) {
        logger.info("Request accepted. Parameter: {}", queryParam);
        String value = valueRetrievalService.getValueFromParameterOrDefault(queryParam);
        String date = zonedDateService.getCurrentZonedDate();
        return new ResponseDto(value, date);
    }
}
