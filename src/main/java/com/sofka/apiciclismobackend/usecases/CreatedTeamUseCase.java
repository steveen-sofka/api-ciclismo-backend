package com.sofka.apiciclismobackend.usecases;

import com.sofka.apiciclismobackend.entities.Team;
import com.sofka.apiciclismobackend.mapper.Mappers;
import com.sofka.apiciclismobackend.models.TeamDTO;
import com.sofka.apiciclismobackend.repository.TeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class CreatedTeamUseCase implements AddTeam {

    private final TeamRepository teamRepository;
    private final Mappers mappers;

    public CreatedTeamUseCase(TeamRepository teamRepository, Mappers mappers) {
        this.teamRepository = teamRepository;
        this.mappers = mappers;
    }

    @Override
    public Mono<Team> apply(TeamDTO teamDTO) {
        Team team = new Team(
                teamDTO.getId(),
                teamDTO.getNameTeam(),
                teamDTO.getCodeTeam(),
                teamDTO.getCountryTeam(),
                teamDTO.getCyclistList());
        return teamRepository.save(team);
    }
}
