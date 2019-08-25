package br.com.vtferrari.sandwich.handler;

import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryToDemographicConverter {

    public Demographic convert(Category category) {
        final var interestMap = category
                .getInterest()
                .stream()
                .collect(Collectors.toMap(interest -> interest, x -> 0L));
        return Demographic
                .builder()
                .group(category.getGroup())
                .interest(interestMap)
                .build();
    }
}
