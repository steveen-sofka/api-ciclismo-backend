package com.sofka.apiciclismobackend.models;

import com.sofka.apiciclismobackend.entities.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    @Id
    private String id;
    private String nameTeam;
    private String codeTeam;
    private String countryTeam;
    private List<Cyclist> cyclistList;


}
