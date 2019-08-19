package br.com.vtferrari.sandwich.usecase.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
public class Person {

    @Id
    private String id;
    private String content;
    private Category category;
}
