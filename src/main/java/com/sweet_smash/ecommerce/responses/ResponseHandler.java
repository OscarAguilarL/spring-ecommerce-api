package com.sweet_smash.ecommerce.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(
            boolean success,
            String message,
            HttpStatus httpStatus,
            Object responseObject
    ) {

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", success);
        responseMap.put("message", message);
        responseMap.put("data", responseObject);

        return new ResponseEntity<>(responseMap, httpStatus);
    }
}
