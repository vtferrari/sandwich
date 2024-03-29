package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.DemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InsertOrIncrementDemographicGroupUseCaseTest {

    @InjectMocks
    private InsertOrIncrementDemographicGroupUseCase insertOrIncrementDemographicGroupUseCase;
    @Mock
    private DemographicGroupRepository demographicGroupRepository;

    @Test
    public void testShouldInsertNewDemographicGroupUseCase() {
        when(demographicGroupRepository.findById(anyString())).thenReturn(Mono.empty());
        when(demographicGroupRepository.save(any(Demographic.class))).thenReturn(Mono.just(Demographic.builder().group("A1").build()));
        doNothing().when(demographicGroupRepository).increment(any(Demographic.class));

        insertOrIncrementDemographicGroupUseCase.execute(Demographic.builder().group("A1").build());

        insertOrIncrementDemographicGroupUseCase.execute(Demographic.builder().group("A1").build());
        verify(demographicGroupRepository, atLeastOnce()).findById(anyString());
        verify(demographicGroupRepository, atLeastOnce()).save(any(Demographic.class));
        verify(demographicGroupRepository, atLeastOnce()).increment(any(Demographic.class));
    }

    @Test
    public void testShouldIncrementDemographicGroupUseCase() {
        when(demographicGroupRepository.findById(anyString())).thenReturn(Mono.just(Demographic.builder().group("A1").build()));
        when(demographicGroupRepository.save(any(Demographic.class))).thenReturn(Mono.just(Demographic.builder().group("A1").build()));
        doNothing().when(demographicGroupRepository).increment(any(Demographic.class));

        insertOrIncrementDemographicGroupUseCase.execute(Demographic.builder().group("A1").build());

        verify(demographicGroupRepository, atLeastOnce()).findById(anyString());
        verify(demographicGroupRepository, atLeastOnce()).save(any(Demographic.class));
        verify(demographicGroupRepository, atLeastOnce()).increment(any(Demographic.class));
    }
}