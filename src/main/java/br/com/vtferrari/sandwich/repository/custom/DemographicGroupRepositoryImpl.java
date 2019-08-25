package br.com.vtferrari.sandwich.repository.custom;

import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
@RequiredArgsConstructor
public class DemographicGroupRepositoryImpl implements CustomDemographicGroupRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void increment(final Demographic demographic) {
        final var query = new Query(where("_id").is(demographic.getGroup()));
        final var update = new Update();

        demographic.getInterest().forEach((key, value) -> update.inc("interest.".concat(key), 1));

        mongoTemplate.updateMulti(query, update, Demographic.class);


    }
}
