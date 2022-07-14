package com.sofka.apiciclismobackend.usecases;

import com.sofka.apiciclismobackend.entities.Team;
import com.sofka.apiciclismobackend.models.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface AddTeam {
    Mono<Team> apply(@Valid TeamDTO teamDTO);
}
