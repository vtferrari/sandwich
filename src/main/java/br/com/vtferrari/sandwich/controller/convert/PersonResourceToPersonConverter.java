package br.com.vtferrari.sandwich.controller.convert;

import br.com.vtferrari.sandwich.controller.resource.CategoryResource;
import br.com.vtferrari.sandwich.controller.resource.PersonResource;
import br.com.vtferrari.sandwich.usecase.domain.Category;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PersonResourceToPersonConverter {
    public Person convert(PersonResource personResource) {
        return Person
                .builder()
                .id(personResource.getId())
                .category(getCategory(personResource))
                .content(encode(personResource.getDescription()))
                .build();
    }

    private Category getCategory(PersonResource personResource) {
        return Category
                .builder()
                .group(personResource.getCategory().getId())
                .interest(personResource.getCategory().getInterest())
                .build();
    }

    private String encode(String encode){
        return new String(Base64.getEncoder().encode(encode.getBytes()));
    }
}
