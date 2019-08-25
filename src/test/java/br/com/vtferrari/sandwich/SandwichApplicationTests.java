package br.com.vtferrari.sandwich;

import br.com.vtferrari.sandwich.controller.DemographicGroupController;
import br.com.vtferrari.sandwich.repository.DemographicGroupRepository;
import br.com.vtferrari.sandwich.usecase.FindAllDemographicGroupUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@Configuration
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, KafkaAutoConfiguration.class})
public class SandwichApplicationTests {

    @Autowired
    private DemographicGroupController controller;
    @MockBean
    private FindAllDemographicGroupUseCase service;
    @MockBean
    public KafkaTemplate<String, String> kafkaTemplate;
    @MockBean
    public DemographicGroupRepository demographicGroupRepository;
    @MockBean
    public MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        when(service.execute()).thenReturn(Flux.empty());
        final var all = controller.getAll().block();
        assertNotNull(all.getContent());
    }

}
