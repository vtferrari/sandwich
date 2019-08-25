package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SendToQueueUseCaseTest {


    @InjectMocks
    private SendToQueueUseCase sendToQueueUseCase;
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;
    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void testShouldSentToQueue() {

        when(kafkaTemplate.send(eq("count.interest.person"), anyString())).thenReturn(null);

        sendToQueueUseCase.execute(Person.builder().category(Category.builder().group("A1").interest(List.of("test")).build()).build());

        verify(kafkaTemplate, atLeastOnce()).send(eq("count.interest.person"), anyString());
    }

    @Test(expected = RuntimeException.class)
    public void testShouldNotSentToQueue() throws Exception {

        when(objectMapper.writeValueAsString(any(Category.class))).thenThrow(new JsonProcessingException("") {});

        sendToQueueUseCase.execute(Person.builder().category(Category.builder().group("A1").interest(List.of("test")).build()).build());
    }
}