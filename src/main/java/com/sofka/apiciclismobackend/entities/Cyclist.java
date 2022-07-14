package com.sofka.apiciclismobackend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Cyclist {
    @Id
    private String id;
    private String idTeam;
    private String nameCyclist;
    private Integer numberCompetitor;
    private String nationalityCyclist;

    // Constructors
    public Cyclist() {}

    public Cyclist(String id, String idTeam, String nameCyclist, Integer numberCompetitor, String nationalityCyclist) {
        this.id = id;
        this.idTeam = idTeam;
        this.nameCyclist = nameCyclist;
        this.numberCompetitor = numberCompetitor;
        this.nationalityCyclist = nationalityCyclist;
    }
}
