package br.com.vtferrari.sandwich.controller.convert;

import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ListOfDemographicToDemographicsResourceConverterTest {

    @InjectMocks
    public ListOfDemographicToDemographicsResourceConverter listOfDemographicToDemographicsResourceConverter;

    @Test
    public void testShouldConvert() {

        final var build = Demographic
                .builder()
                .group("A1")
                .interest(Map.of("test1", 1L))
                .build();

        final var convert = listOfDemographicToDemographicsResourceConverter.convert(List.of(build));
        assertEquals("A1", convert.getContent().get(0).getGroup());
        assertEquals(1, convert.getContent().get(0).getInterest().size());
    }
}