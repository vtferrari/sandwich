package br.com.vtferrari.sandwich.repository;

import br.com.vtferrari.sandwich.repository.custom.CustomDemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DemographicGroupRepository extends ReactiveMongoRepository<Demographic, String>, CustomDemographicGroupRepository {
}
