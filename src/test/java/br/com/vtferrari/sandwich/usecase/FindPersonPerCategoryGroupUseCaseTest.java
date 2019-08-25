package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.PersonRepository;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindPersonPerCategoryGroupUseCaseTest {

    @InjectMocks
    private FindPersonPerCategoryGroupUseCase findPersonPerCategoryGroupUseCase;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void testShouldFindPersonPerCategory() {
        when(personRepository.findByCategoryGroup(anyString())).thenReturn(Flux.just(Person.builder().build()));

        final List<Person> people = findPersonPerCategoryGroupUseCase.execute("A1").collectList().block();

        assertEquals(1, people.size());
        verify(personRepository, atLeastOnce()).findByCategoryGroup(anyString());
    }
}