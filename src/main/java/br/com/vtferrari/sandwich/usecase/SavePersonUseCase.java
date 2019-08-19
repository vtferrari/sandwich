package br.com.vtferrari.sandwich.usecase;

import br.com.vtferrari.sandwich.repository.PersonRepository;
import br.com.vtferrari.sandwich.usecase.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SavePersonUseCase {

    private final PersonRepository personRepository;

    public Mono<Person> execute(Person person){
        return personRepository.save(person);
    }
}
