package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.DemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FindAllDemographicGroupUseCase {
    private final DemographicGroupRepository demographicGroupRepository;

    public Flux<Demographic> execute() {
        return demographicGroupRepository.findAll();
    }
}
