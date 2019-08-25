package br.com.vtferrari.sandwich.controller;

import br.com.vtferrari.sandwich.controller.convert.ListOfDemographicToDemographicsResourceConverter;
import br.com.vtferrari.sandwich.controller.resource.DemographicsResource;
import br.com.vtferrari.sandwich.usecase.FindAllDemographicGroupUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/demographics")
public class DemographicGroupController {

    private final FindAllDemographicGroupUseCase findAllDemographicGroupUseCase;
    private final ListOfDemographicToDemographicsResourceConverter listOfDemographicToDemographicsResourceConverter;

    @GetMapping
    public Mono<DemographicsResource> getAll() {
        return findAllDemographicGroupUseCase.execute()
                .collectList()
                .map(listOfDemographicToDemographicsResourceConverter::convert);
    }
}
