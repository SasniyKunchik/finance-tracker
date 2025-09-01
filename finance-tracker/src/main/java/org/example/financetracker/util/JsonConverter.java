package org.example.financetracker.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.entity.dto.CreatePaymentTransactionRequest;
import org.springframework.stereotype.Component;

import javax.management.RuntimeOperationsException;

@Slf4j
@Component
public class JsonConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing json", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String toJson(Object obj){
        try{
            return mapper.writeValueAsString(obj);
        } catch(JsonProcessingException e){
            log.error("Error serializing json", e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
