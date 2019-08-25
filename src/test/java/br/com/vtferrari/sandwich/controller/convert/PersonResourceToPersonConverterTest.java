package br.com.vtferrari.sandwich.controller.convert;

import br.com.vtferrari.sandwich.controller.resource.CategoryResource;
import br.com.vtferrari.sandwich.controller.resource.PersonResource;
import br.com.vtferrari.sandwich.handler.converer.CategoryToDemographicConverter;
import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonResourceToPersonConverterTest {

    @InjectMocks
    public PersonResourceToPersonConverter personResourceToPersonConverter;

    @Test
    public void testShouldConvert() {

        final var categoryResource = CategoryResource
                .builder()
                .id("A1")
                .interest(List.of("test"))
                .build();
        final var build = PersonResource
                .builder()
                .id("P1")
                .category(categoryResource)
                .description("test")
                .build();
        final var convert = personResourceToPersonConverter.convert(build);
        assertEquals("P1",convert.getId());
        assertEquals("A1",convert.getCategory().getGroup());
        assertEquals("dGVzdA==",convert.getContent());
    }
}