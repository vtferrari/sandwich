package br.com.vtferrari.sandwich.repository.custom;

import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DemographicGroupRepositoryImplTest {

    @InjectMocks
    private DemographicGroupRepositoryImpl demographicGroupRepository;
    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    public void testShouldIncrement() {
        demographicGroupRepository.increment(Demographic.builder().interest(Map.of("test", 1L)).build());

        verify(mongoTemplate, atLeastOnce()).updateMulti(any(Query.class), any(Update.class), eq(Demographic.class));
    }
}