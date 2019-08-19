package br.com.vtferrari.sandwich.repository;

import br.com.vtferrari.sandwich.usecase.domain.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    Flux<Person> findByCategoryGroup(String group);
}
