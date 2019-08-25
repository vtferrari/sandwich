package br.com.vtferrari.sandwich.repository;

import br.com.vtferrari.sandwich.repository.custom.CustomDemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DemographicGroupRepository extends ReactiveMongoRepository<Demographic, String>, CustomDemographicGroupRepository {
}
