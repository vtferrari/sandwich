package br.com.vtferrari.sandwich.controller;

import br.com.vtferrari.sandwich.controller.convert.ListOfDemographicToDemographicsResourceConverter;
import br.com.vtferrari.sandwich.usecase.FindAllDemographicGroupUseCase;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DemographicGroupControllerTest {

    @InjectMocks
    private DemographicGroupController demographicGroupController;
    @Spy
    private ListOfDemographicToDemographicsResourceConverter listOfDemographicToDemographicsResourceConverter;
    @Mock
    private FindAllDemographicGroupUseCase findAllDemographicGroupUseCase;

    @Test
    public void testShouldGetAll() {

        final var build = Demographic
                .builder()
                .group("A1")
                .interest(Map.of("test", 221L))
                .build();
        when(findAllDemographicGroupUseCase.execute()).thenReturn(Flux.just(build));

        final var block = demographicGroupController.getAll().block();

        verify(findAllDemographicGroupUseCase, atLeastOnce()).execute();
        assertEquals(1, block.getContent().size());
    }
}