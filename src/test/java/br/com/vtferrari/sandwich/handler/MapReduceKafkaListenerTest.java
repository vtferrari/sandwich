package br.com.vtferrari.sandwich.handler;

import br.com.vtferrari.sandwich.handler.converer.CategoryToDemographicConverter;
import br.com.vtferrari.sandwich.usecase.InsertOrIncrementDemographicGroupUseCase;
import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MapReduceKafkaListenerTest {

    @InjectMocks
    private MapReduceKafkaListener mapReduceKafkaListener;
    @Spy
    private ObjectMapper objectMapper;
    @Spy
    private CategoryToDemographicConverter categoryToDemographicConverter;
    @Mock
    private InsertOrIncrementDemographicGroupUseCase insertOrIncrementDemographicGroupUseCase;


    @Test
    public void testShouldInsertOrIncrementDemographicGroup() throws Exception {

        mapReduceKafkaListener.listener("{\"group\":\"A1\",\"interest\":[\"test1\",\"test2\"]}");

        verify(objectMapper).readValue(anyString(),eq(Category.class));
        verify(categoryToDemographicConverter).convert(any(Category.class));
        verify(insertOrIncrementDemographicGroupUseCase).execute(any(Demographic.class));
    }

    @Test(expected = RuntimeException.class)
    public void testShouldNotInsertOrIncrementDemographicGroup() throws Exception {

        mapReduceKafkaListener.listener("{\"group\":\"A1\",\"interest\":\"[\"test\"]\"}");

        verify(objectMapper).readValue(anyString(),eq(Category.class));
        verify(categoryToDemographicConverter).convert(any(Category.class));
        verify(insertOrIncrementDemographicGroupUseCase).execute(any(Demographic.class));
    }
}