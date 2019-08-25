package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.DemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindAllDemographicGroupUseCaseTest {

    @InjectMocks
    private FindAllDemographicGroupUseCase findAllDemographicGroupUseCase;
    @Mock
    private DemographicGroupRepository demographicGroupRepository;

    @Test
    public void testShouldExecuteFindAll() {

        when(demographicGroupRepository.findAll()).thenReturn(Flux.just(Demographic.builder().build()));

        final var block = findAllDemographicGroupUseCase.execute().collectList().block();

        assertEquals(1, block.size());
        verify(demographicGroupRepository, atLeastOnce()).findAll();
    }
}