package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.PersonRepository;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SavePersonUseCaseTest {

    @InjectMocks
    private SavePersonUseCase savePersonUseCase;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void testShouldSavePerson() {
        when(personRepository.save(any(Person.class))).thenReturn(Mono.just(Person.builder().id("1").build()));

        final var people = savePersonUseCase.execute(Person.builder().build()).block();

        verify(personRepository, atLeastOnce()).save(any(Person.class));
        assertEquals("1", people.getId());
    }
}