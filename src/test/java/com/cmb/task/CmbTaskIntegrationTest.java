package com.cmb.task;

import com.cmb.task.controller.MainController;
import com.cmb.task.dto.ResponseDto;
import com.cmb.task.service.ValueRetrievalService;
import com.cmb.task.service.ZonedDateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CmbTaskIntegrationTest {

    private static final String TIMEZONE_ID = "Europe/Paris";

    @Value("${default.value}")
    private String defaultValue;

    private MainController underTest;

    @BeforeEach
    public void setUp() {
        ValueRetrievalService valueRetrievalService = new ValueRetrievalService(this.defaultValue);
        ZonedDateService zonedDateService = new ZonedDateService();
        underTest = new MainController(valueRetrievalService, zonedDateService);
    }

    @Test
    public void testIntegratedBehaviorWithParameter() {
        // given
        String queryParam = "value1";
        ZonedDateTime testZonedDate = ZonedDateTime.of(2022, 3, 5, 0, 0 ,0, 0, ZoneId.of(TIMEZONE_ID));

        try (MockedStatic<ZonedDateTime> mockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            mockedStatic.when(() -> ZonedDateTime.now(ArgumentMatchers.eq(ZoneId.of(TIMEZONE_ID))))
                    .thenReturn(testZonedDate);

            // when
            ResponseDto response = underTest.getResponse(queryParam);

            // then
            assertThat(response.aValue()).isEqualTo(queryParam);
            assertThat(response.bValue()).isEqualTo("2022-03-05");
        }
    }

    @Test
    public void testIntegratedBehaviorWithoutParameter() {
        // given
        ZonedDateTime testZonedDate = ZonedDateTime.of(2024, 2, 27, 0, 0 ,0, 0, ZoneId.of(TIMEZONE_ID));

        try (MockedStatic<ZonedDateTime> mockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
            mockedStatic.when(() -> ZonedDateTime.now(ArgumentMatchers.eq(ZoneId.of(TIMEZONE_ID))))
                    .thenReturn(testZonedDate);

            // when
            ResponseDto response = underTest.getResponse(null);

            // then
            assertThat(response.aValue()).isEqualTo("test");
            assertThat(response.bValue()).isEqualTo("2024-02-27");
        }
    }
}
