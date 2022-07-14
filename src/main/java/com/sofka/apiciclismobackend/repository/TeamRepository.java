package com.sofka.apiciclismobackend.repository;

import com.sofka.apiciclismobackend.entities.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
    @Override
    Mono<Team> save(Team team);
}
