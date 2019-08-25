package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.DemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsertOrIncrementDemographicGroupUseCase {
    private final DemographicGroupRepository demographicGroupRepository;

    public void execute(Demographic demographic) {
        demographicGroupRepository.findById(demographic.getGroup())
                .switchIfEmpty(demographicGroupRepository.save(demographic))
                .doOnNext(d -> demographicGroupRepository.increment(demographic))
                .block();
    }
}
