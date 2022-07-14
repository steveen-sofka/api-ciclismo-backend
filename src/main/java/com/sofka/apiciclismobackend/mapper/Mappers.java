package com.sofka.apiciclismobackend.mapper;

import com.sofka.apiciclismobackend.entities.Cyclist;
import com.sofka.apiciclismobackend.entities.Team;
import com.sofka.apiciclismobackend.models.CyclistDTO;
import com.sofka.apiciclismobackend.models.TeamDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Mappers {

    // Mapper de TeamDTO a Team Entidad
    public Function<TeamDTO, Team> mapperTeamDtoToEntity(){
        return updateTeam -> {
            var team = new Team();
            team.setId(updateTeam.getId());
            team.setNameTeam(updateTeam.getNameTeam());
            team.setCodeTeam(updateTeam.getCodeTeam());
            team.setCountryTeam(updateTeam.getCountryTeam());
            team.setCyclistList(updateTeam.getCyclistList());
            return team;
        };
    }

    // Mapper de Team entidad a TeamDto
    public Function<Team, TeamDTO> mapperEntityToTeamDto(){
        return entity -> new TeamDTO(
                entity.getId(),
                entity.getNameTeam(),
                entity.getCodeTeam(),
                entity.getCountryTeam(),
                entity.getCyclistList()
        );
    }

    // Mapper de CyclistDto a Cyclist entidad
    public Function<CyclistDTO, Cyclist> mapperCyclistDtoToEntity(){
        return updateCyclist -> {
            var cyclist = new Cyclist();
            cyclist.setId(updateCyclist.getId());
            cyclist.setNameCyclist(updateCyclist.getNameCyclist());
            cyclist.setNumberCompetitor(updateCyclist.getNumberCompetitor());
            cyclist.setNationalityCyclist(updateCyclist.getNationalityCyclist());
            return cyclist;
        };
    }

    // Mapper de Cyclist entidad a CyclistDto
    public Function<Cyclist, CyclistDTO> mapperEntityToCyclisDto(){
        return entity ->  new CyclistDTO(
                entity.getId(),
                entity.getNameCyclist(),
                entity.getNumberCompetitor(),
                entity.getNationalityCyclist()
        );
    }
}
