package com.restaurants.bestmatchedrestaurants.interceptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.restaurants.bestmatchedrestaurants.domain.ErrorResponse;
import com.restaurants.bestmatchedrestaurants.domain.ErrorType;
import com.restaurants.bestmatchedrestaurants.exception.ClientErrorException;
import com.restaurants.bestmatchedrestaurants.exception.ServerErrorException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ControllerErrorInterceptorTest {

    @InjectMocks private ControllerErrorInterceptor interceptor;

    @Test
    public void handleClientErrorExceptionWithoutDetails() {
        //given
        final ErrorType errorType = ErrorType.VALIDATION;
        final String message = UUID.randomUUID().toString();
        final ClientErrorException exception = new ClientErrorException(errorType, message);

        //when
        final ResponseEntity<ErrorResponse> response = interceptor.handleException(exception);

        //then
        assertNotNull(response);
        assertEquals(errorType.getHttpStatus(), response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(errorType, response.getBody().getErrorType());
        assertEquals(message, response.getBody().getMessage());
        assertTrue(response.getBody().getDetails() == null || response.getBody().getDetails().isEmpty());
    }

    @Test
    public void handleClientErrorExceptionWithDetails() {
        //given
        final ErrorType errorType = ErrorType.VALIDATION;
        final String message = UUID.randomUUID().toString();
        final Map<String, String> details = new HashMap<>();
        details.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        details.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final ClientErrorException exception = new ClientErrorException(errorType, message);
        exception.getDetails().putAll(details);

        //when
        final ResponseEntity<ErrorResponse> response = interceptor.handleException(exception);

        //then
        assertNotNull(response);
        assertEquals(errorType.getHttpStatus(), response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(errorType, response.getBody().getErrorType());
        assertEquals(message, response.getBody().getMessage());
        assertEquals(details, response.getBody().getDetails());
    }

    @Test
    public void handleServerErrorExceptionWithoutDetails() {
        //given
        final String message = UUID.randomUUID().toString();
        final ServerErrorException exception = new ServerErrorException(message);

        //when
        final ResponseEntity<ErrorResponse> response = interceptor.handleException(exception);

        //then
        assertNotNull(response);
        assertEquals(ErrorType.INTERNAL_ERROR.getHttpStatus(), response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(ErrorType.INTERNAL_ERROR, response.getBody().getErrorType());
        assertEquals(message, response.getBody().getMessage());
        assertTrue(response.getBody().getDetails() == null || response.getBody().getDetails().isEmpty());
    }

    @Test
    public void handleServerErrorExceptionWithDetails() {
        //given
        final String message = UUID.randomUUID().toString();
        final Map<String, String> details = new HashMap<>();
        details.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        details.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        final ServerErrorException exception = new ServerErrorException(message);
        exception.getDetails().putAll(details);

        //when
        final ResponseEntity<ErrorResponse> response = interceptor.handleException(exception);

        //then
        assertNotNull(response);
        assertEquals(ErrorType.INTERNAL_ERROR.getHttpStatus(), response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(ErrorType.INTERNAL_ERROR, response.getBody().getErrorType());
        assertEquals(message, response.getBody().getMessage());
        assertEquals(details, response.getBody().getDetails());
    }
}
