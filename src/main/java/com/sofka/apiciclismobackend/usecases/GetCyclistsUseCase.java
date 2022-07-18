package com.sofka.apiciclismobackend.usecases;

import com.sofka.apiciclismobackend.mapper.Mappers;
import com.sofka.apiciclismobackend.repository.CyclistRepository;
import com.sofka.apiciclismobackend.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Supplier;

@Service
@Validated
public class GetCyclistsUseCase implements Supplier<Flux<Object>> {

    private final CyclistRepository cyclistRepository;
    private final TeamRepository teamRepository;
    private final Mappers mappers;

    public GetCyclistsUseCase(CyclistRepository cyclistRepository, TeamRepository teamRepository, Mappers mappers) {
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
        this.mappers = mappers;
    }

    @Override
    public Flux<Object> get() {
        return teamRepository.findAll()
                .map(team -> team.getCyclistList().stream()
                        .map(mappers.mapperEntityToCyclisDto()));
    }

    public Flux<Object> getCyclistByNationality(String nationality){
        return teamRepository.findAll()
                .map(team -> team.getCyclistList().stream()
                        .filter(cyclist -> cyclist.getNationalityCyclist().equals(nationality))
                        .map(mappers.mapperEntityToCyclisDto()));
    }

    public Flux<Object> getCyclistById(String id) {
        return teamRepository.findAll()
                .map(team -> team.getCyclistList().stream()
                        .filter(cyclist -> cyclist.getId().equals(id))
                        .map(mappers.mapperEntityToCyclisDto()));
    }

    public Flux<Object> deleteCyclist(String id) {
        Objects.requireNonNull(id, "Id is required");
        return teamRepository.findAll()
                .map(team -> team.getCyclistList().stream()
                        .map(cyclist -> teamRepository.deleteById(id)));
    }
}
