package br.com.vtferrari.sandwich.controller.convert;

import br.com.vtferrari.sandwich.controller.resource.CategoryResource;
import br.com.vtferrari.sandwich.controller.resource.PeopleResource;
import br.com.vtferrari.sandwich.controller.resource.PersonResource;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ListOfPersonToPeopleResourceConverter {

    public PeopleResource convert(final List<Person> people) {

        return PeopleResource
                .builder()
                .content(getListOfPersonResource(people))
                .build();
    }

    private List<PersonResource> getListOfPersonResource(List<Person> people) {
        return people
                .stream()
                .map(this::mapToPersonResource)
                .collect(toList());
    }

    private PersonResource mapToPersonResource(Person person) {

        return PersonResource
                .builder()
                .id(person.getId())
                .category(getCategory(person))
                .description(decode(person.getContent()))
                .build();
    }

    private CategoryResource getCategory(Person person) {
        return CategoryResource
                .builder()
                .id(person.getCategory().getGroup())
                .interest(person.getCategory().getInterest())
                .build();

    }

    private String decode(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }
}