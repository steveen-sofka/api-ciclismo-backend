package com.sofka.apiciclismobackend.repository;

import com.sofka.apiciclismobackend.entities.Team;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
}
