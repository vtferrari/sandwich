package br.com.vtferrari.sandwich.repository.custom;

import br.com.vtferrari.sandwich.usecase.domain.Demographic;

public interface CustomDemographicGroupRepository {
    void increment(Demographic demographic);
}
