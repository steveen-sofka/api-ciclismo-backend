package com.sofka.apiciclismobackend.usecases;

import com.sofka.apiciclismobackend.entities.Cyclist;
import com.sofka.apiciclismobackend.mapper.Mappers;
import com.sofka.apiciclismobackend.models.CyclistDTO;
import com.sofka.apiciclismobackend.models.TeamDTO;
import com.sofka.apiciclismobackend.repository.CyclistRepository;
import com.sofka.apiciclismobackend.repository.TeamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CreatedCyclistUseCase implements AddCyclist {

    private final CyclistRepository cyclistRepository;
    private final TeamRepository teamRepository;
    private final Mappers mappers;
    private final CreatedTeamUseCase createdTeamUseCase;

    public CreatedCyclistUseCase(CyclistRepository cyclistRepository, TeamRepository teamRepository, Mappers mappers, CreatedTeamUseCase createdTeamUseCase) {
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
        this.mappers = mappers;
        this.createdTeamUseCase = createdTeamUseCase;
    }

    @Override
    public Mono<Cyclist> apply(CyclistDTO cyclistDTO) {
        Cyclist cyclist = new Cyclist(
                cyclistDTO.getId(),
                cyclistDTO.getNameCyclist(),
                cyclistDTO.getNumberCompetitor(),
                cyclistDTO.getNationalityCyclist()
        );
        return cyclistRepository.save(cyclist);
    }

    @Override
    public Mono<Object> addCyclist(CyclistDTO cyclistDTO, String idTeam) {
        return teamRepository
                .findById(idTeam)
                .flatMap(team -> {

                    TeamDTO teamDTO = mappers.mapperEntityToTeamDto().apply(team);
                    List<Cyclist> cyclistList = teamDTO.getCyclistList();
                    cyclistList.add(mappers.mapperCyclistDtoToEntity().apply(cyclistDTO));
                    teamDTO.setCyclistList(cyclistList);

                    return createdTeamUseCase.apply(teamDTO);
                });
    }
}
