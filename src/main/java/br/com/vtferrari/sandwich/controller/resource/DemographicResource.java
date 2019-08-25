package br.com.vtferrari.sandwich.controller.resource;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemographicResource {
    private String group;
    private Map<String, Long> interest;
}
