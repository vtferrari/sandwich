package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.DemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class InsertOrIncrementDemographicGroupUseCase {
    private final DemographicGroupRepository demographicGroupRepository;

    public void execute(Demographic demographic) {
        if (isNull(demographicGroupRepository.findById(demographic.getGroup()))) {
            demographicGroupRepository.save(demographic);
        }
        demographicGroupRepository.increment(demographic);
    }
}
