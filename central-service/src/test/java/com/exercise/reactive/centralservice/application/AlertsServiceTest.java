package com.exercise.reactive.centralservice.application;

import com.exercise.reactive.centralservice.domain.alerts.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class AlertsServiceTest {

    @InjectMocks
    private AlertsService alertHandler;

    @Mock
    private Alert alert;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleAlert(CapturedOutput output) {
        String message = "Test Alert Message";
        when(alert.generateMessage()).thenReturn(message);

        alertHandler.handleAlert(alert);

        assertThat(output).contains(message);
    }
}
