package com.cmb.task.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValueRetrievalService {

    private final String defaultValue;

    public ValueRetrievalService(@Value("${default.value}") String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValueFromParameterOrDefault(String queryParam) {
        if (queryParam == null) {
            return this.defaultValue;
        } else {
            return queryParam;
        }
    }
}
