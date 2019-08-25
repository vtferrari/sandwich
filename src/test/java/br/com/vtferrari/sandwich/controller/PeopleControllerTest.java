package br.com.vtferrari.sandwich.controller;

import br.com.vtferrari.sandwich.controller.convert.ListOfPersonToPeopleResourceConverter;
import br.com.vtferrari.sandwich.controller.convert.PersonResourceToPersonConverter;
import br.com.vtferrari.sandwich.controller.resource.CategoryResource;
import br.com.vtferrari.sandwich.controller.resource.PeopleResource;
import br.com.vtferrari.sandwich.controller.resource.PersonResource;
import br.com.vtferrari.sandwich.usecase.FindPersonPerCategoryGroupUseCase;
import br.com.vtferrari.sandwich.usecase.SavePersonUseCase;
import br.com.vtferrari.sandwich.usecase.SendToQueueUseCase;
import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PeopleControllerTest {

    @InjectMocks
    public PeopleController peopleController;
    @Mock
    private FindPersonPerCategoryGroupUseCase findPersonPerCategoryGroupUseCase;
    @Spy
    private ListOfPersonToPeopleResourceConverter listOfPersonToPeopleResourceConverter;
    @Spy
    private PersonResourceToPersonConverter personResourceToPersonConverter;
    @Mock
    private SendToQueueUseCase sendToQueueUseCase;
    @Mock
    private SavePersonUseCase savePersonUseCase;


    @Test
    public void testShouldGetByGroup() {
        final var build = Person
                .builder()
                .category(Category
                        .builder()
                        .group("A1")
                        .interest(List.of("test"))
                        .build())
                .content("test")
                .build();

        when(findPersonPerCategoryGroupUseCase.execute(anyString())).thenReturn(Flux.just(build));

        final PeopleResource a1 = peopleController.getByGroup("A1").block();

        assertEquals(1, a1.getContent().size());
        verify(findPersonPerCategoryGroupUseCase, atLeastOnce()).execute(anyString());
        verify(listOfPersonToPeopleResourceConverter, atLeastOnce()).convert(anyList());
    }


    @Test
    public void testShouldSave() {
        final var build = PersonResource
                .builder()
                .category(CategoryResource
                        .builder()
                        .id("A1")
                        .interest(List.of("test"))
                        .build())
                .description("test")
                .build();

        when(savePersonUseCase.execute(any(Person.class))).thenReturn(Mono.just(Person.builder().build()));

        peopleController.save(build).block();

        verify(personResourceToPersonConverter, atLeastOnce()).convert(any(PersonResource.class));
        verify(savePersonUseCase, atLeastOnce()).execute(any(Person.class));
        verify(sendToQueueUseCase, atLeastOnce()).execute(any(Person.class));
    }

}