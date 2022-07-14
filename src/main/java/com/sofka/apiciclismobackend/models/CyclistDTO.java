package com.sofka.apiciclismobackend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CyclistDTO {
    @Id
    private String id;
    private String idTeam;
    private String nameCyclist;
    private Integer numberCompetitor;
    private String nationalityCyclist;

    // Constructors
    public CyclistDTO() {}

    public CyclistDTO(String id, String idTeam, String nameCyclist, Integer numberCompetitor, String nationalityCyclist) {
        this.id = id;
        this.idTeam = idTeam;
        this.nameCyclist = nameCyclist;
        this.numberCompetitor = numberCompetitor;
        this.nationalityCyclist = nationalityCyclist;
    }

}
