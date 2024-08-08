package com.exercise.reactive.centralservice.application;

import com.exercise.reactive.centralservice.domain.alerts.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class AlertsServiceTest{

    @InjectMocks
    private AlertsService alertHandler;

    @Mock
    private Logger log;

    @Mock
    private Alert alert;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled
    void testHandleAlert() {
        String message = "Test Alert Message";
        when(alert.generateMessage()).thenReturn(message);

        alertHandler.handleAlert(alert);

        verify(log, times(1)).info(message);
    }
}
