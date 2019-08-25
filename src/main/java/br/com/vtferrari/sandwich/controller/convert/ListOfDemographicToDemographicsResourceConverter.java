package br.com.vtferrari.sandwich.controller.convert;

import br.com.vtferrari.sandwich.controller.resource.DemographicResource;
import br.com.vtferrari.sandwich.controller.resource.DemographicsResource;
import br.com.vtferrari.sandwich.usecase.domain.Demographic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListOfDemographicToDemographicsResourceConverter {
    public DemographicsResource convert(List<Demographic> demographics) {

        final List<DemographicResource> demographicResources = demographics
                .stream()
                .map(this::getDemographicResource)
                .collect(Collectors.toList());
        return DemographicsResource
                .builder()
                .content(demographicResources)
                .build();

    }

    private DemographicResource getDemographicResource(Demographic demographic) {
        return DemographicResource
                .builder()
                .group(demographic.getGroup())
                .interest(demographic.getInterest())
                .build();
    }
}
