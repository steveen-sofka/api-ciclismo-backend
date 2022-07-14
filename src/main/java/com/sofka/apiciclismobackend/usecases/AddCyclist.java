package com.sofka.apiciclismobackend.usecases;

import com.sofka.apiciclismobackend.entities.Cyclist;
import com.sofka.apiciclismobackend.entities.Team;
import com.sofka.apiciclismobackend.models.CyclistDTO;
import com.sofka.apiciclismobackend.models.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface AddCyclist {
    Mono<Cyclist> apply(@Valid CyclistDTO cyclistDTO);
    Mono<Object> addCyclist(CyclistDTO cyclistDTO, String idTeam);
}
