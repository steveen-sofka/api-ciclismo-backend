package com.sofka.apiciclismobackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CyclistDTO {
    @Id
    private String id;
    private String nameCyclist;
    private Integer numberCompetitor;
    private String nationalityCyclist;
}
