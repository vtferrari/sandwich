package br.com.vtferrari.sandwich.controller;

import br.com.vtferrari.sandwich.controller.convert.ListOfPersonToPeopleResourceConverter;
import br.com.vtferrari.sandwich.controller.convert.PersonResourceToPersonConverter;
import br.com.vtferrari.sandwich.controller.resource.PeopleResource;
import br.com.vtferrari.sandwich.controller.resource.PersonResource;
import br.com.vtferrari.sandwich.usecase.FindPersonPerCategoryGroupUseCase;
import br.com.vtferrari.sandwich.usecase.SavePersonUseCase;
import br.com.vtferrari.sandwich.usecase.SendToQueueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/people")
public class PeopleController {

    private final FindPersonPerCategoryGroupUseCase findPersonPerCategoryGroupUseCase;
    private final SavePersonUseCase savePersonUseCase;
    private final ListOfPersonToPeopleResourceConverter listOfPersonToPeopleResourceConverter;
    private final PersonResourceToPersonConverter personResourceToPersonConverter;
    private final SendToQueueUseCase sendToQueueUseCase;

    @GetMapping
    public Mono<PeopleResource> getByGroup(@RequestParam String group) {
        return findPersonPerCategoryGroupUseCase.execute(group)
                .collectList()
                .map(listOfPersonToPeopleResourceConverter::convert);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> save(@RequestBody PersonResource peopleResource) {
        return Mono.just(peopleResource)
                .map(personResourceToPersonConverter::convert)
                .flatMap(savePersonUseCase::execute)
                .doOnNext(sendToQueueUseCase::execute)
                .then();
    }


}
