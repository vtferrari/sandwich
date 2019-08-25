package br.com.vtferrari.sandwich.controller.convert;

import br.com.vtferrari.sandwich.handler.converer.CategoryToDemographicConverter;
import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ListOfPersonToPeopleResourceConverterTest {
    @InjectMocks
    public ListOfPersonToPeopleResourceConverter listOfPersonToPeopleResourceConverter;

    @Test
    public void testShouldConvert() {

        final var category = Category.builder()
                .group("A1")
                .interest(List.of("test1", "test2"))
                .build();
        final var build = Person
                .builder()
                .content("test")
                .category(category)
                .build();

        final var convert = listOfPersonToPeopleResourceConverter.convert(List.of(build));
        assertEquals("A1",convert.getContent().get(0).getCategory().getId());
        assertEquals(2,convert.getContent().get(0).getCategory().getInterest().size());
    }
}