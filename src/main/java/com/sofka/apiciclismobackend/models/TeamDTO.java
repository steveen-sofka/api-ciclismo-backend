package com.sofka.apiciclismobackend.models;

import com.sofka.apiciclismobackend.entities.Cyclist;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class TeamDTO {

    @Id
    private String id;
    private String nameTeam;
    private String codeTeam;
    private String countryTeam;
    private List<Cyclist> cyclistList;

    // Constructors

    public TeamDTO(){}

    public TeamDTO(String id, String nameTeam, String codeTeam, String countryTeam, List<Cyclist> cyclistList) {
        this.id = id;
        this.nameTeam = nameTeam;
        this.codeTeam = codeTeam;
        this.countryTeam = countryTeam;
        this.cyclistList = cyclistList;
    }
}
