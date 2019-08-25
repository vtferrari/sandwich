package br.com.vtferrari.sandwich.handler.converer;

import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CategoryToDemographicConverterTest {

    @InjectMocks
    public CategoryToDemographicConverter categoryToDemographicConverter;

    @Test
    public void testShouldConvert() {

        final var build = Category
                .builder()
                .group("A1")
                .interest(List.of("test1","test2"))
                .build();

        final Demographic convert = categoryToDemographicConverter.convert(build);
        assertEquals("A1",convert.getGroup());
        assertEquals(2,convert.getInterest().size());
    }
}