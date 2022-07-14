package com.sofka.apiciclismobackend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Team {
    @Id
    private String id;
    private String nameTeam;
    private String codeTeam;
    private String countryTeam;
    private List<Cyclist> cyclistList;

    // Constructors

    public Team(){}

    public Team(String id, String nameTeam, String codeTeam, String countryTeam, List<Cyclist> cyclistList) {
        this.id = id;
        this.nameTeam = nameTeam;
        this.codeTeam = codeTeam;
        this.countryTeam = countryTeam;
        this.cyclistList = cyclistList;
    }
}
