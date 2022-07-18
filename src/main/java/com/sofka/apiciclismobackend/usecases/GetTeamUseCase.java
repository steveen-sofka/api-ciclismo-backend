package com.sofka.apiciclismobackend.usecases;

import com.sofka.apiciclismobackend.mapper.Mappers;
import com.sofka.apiciclismobackend.models.TeamDTO;
import com.sofka.apiciclismobackend.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetTeamUseCase implements Function<String, Mono<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final Mappers mappers;

    public GetTeamUseCase(TeamRepository teamRepository, Mappers mappers) {
        this.teamRepository = teamRepository;
        this.mappers = mappers;
    }

    @Override
    public Mono<TeamDTO> apply(String id) {
        return teamRepository.findById(id)
                .map(mappers.mapperEntityToTeamDto());
    }

    public Mono<Void> deleteTeam(String id) {
        Objects.requireNonNull(id, "Id is required");
        return teamRepository.deleteById(id);
    }

}
