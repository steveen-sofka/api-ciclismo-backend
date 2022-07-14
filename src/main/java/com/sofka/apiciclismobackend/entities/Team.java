package com.sofka.apiciclismobackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private String id;
    private String nameTeam;
    private String codeTeam;
    private String countryTeam;
    private List<Cyclist> cyclistList;

    // Constructors


}
