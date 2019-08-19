package br.com.vtferrari.sandwich.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResource {

    private String id;
    private String description;
    private CategoryResource category;
}
