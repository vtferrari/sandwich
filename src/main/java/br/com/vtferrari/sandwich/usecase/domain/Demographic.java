package br.com.vtferrari.sandwich.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@Document("demographic")
@NoArgsConstructor
@AllArgsConstructor
public class Demographic {
    @Id
    private String group;
    private Map<String,Long> interest;
}
