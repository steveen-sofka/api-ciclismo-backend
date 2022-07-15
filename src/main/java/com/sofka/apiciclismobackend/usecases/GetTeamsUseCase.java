package com.sofka.apiciclismobackend.usecases;

import com.sofka.apiciclismobackend.entities.Cyclist;
import com.sofka.apiciclismobackend.mapper.Mappers;
import com.sofka.apiciclismobackend.models.TeamDTO;
import com.sofka.apiciclismobackend.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetTeamsUseCase implements Supplier<Flux<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final Mappers mappers;


    public GetTeamsUseCase(TeamRepository teamRepository, Mappers mappers) {
        this.teamRepository = teamRepository;
        this.mappers = mappers;
    }

    @Override
    public Flux<TeamDTO> get() {
        return teamRepository.findAll()
                .map(mappers.mapperEntityToTeamDto());
    }

    public Flux<TeamDTO> getCyclistsByCode(String codeTeam) {
        return teamRepository.findAll()
                .filter(team -> team.getCodeTeam().equals(codeTeam))
                .map(mappers.mapperEntityToTeamDto());
    }

    public Flux<TeamDTO> getTeamByCountry(String countryTeam) {
        return teamRepository.findAll()
                .filter(team -> team.getCountryTeam().equals(countryTeam))
                .map(mappers.mapperEntityToTeamDto());
    }
}
