package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.usecase.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendToQueueUseCase {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void execute(Person person) {
        try {
            final var data = objectMapper.writeValueAsString(person.getCategory());
            kafkaTemplate.send("count.interest.person", data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to convert message", e);
        }
    }
}
