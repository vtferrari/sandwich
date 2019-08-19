package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.PersonRepository;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FindPersonPerCategoryGroupUseCase {

    private final PersonRepository personRepository;

    public Flux<Person> execute(String group) {
        return personRepository.findByCategoryGroup(group);
    }
}
