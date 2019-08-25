package br.com.vtferrari.sandwich.handler;

import br.com.vtferrari.sandwich.usecase.InsertOrIncrementDemographicGroupUseCase;
import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MapReduceKafkaListener {

    private final ObjectMapper objectMapper;
    private final CategoryToDemographicConverter categoryToDemographicConverter;
    private final InsertOrIncrementDemographicGroupUseCase insertOrIncrementDemographicGroupUseCase;


    @KafkaListener(topics = "count.interest.person", groupId = "test13")
    public void listener(String message) {
        final var category = convertToObject(message);
        final var demographic = categoryToDemographicConverter.convert(category);

        insertOrIncrementDemographicGroupUseCase.execute(demographic);
    }

    private Category convertToObject(String message) {
        try {
            return objectMapper.readValue(message, Category.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not convert message", e);
        }
    }
}
