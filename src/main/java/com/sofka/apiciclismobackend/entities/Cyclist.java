package com.sofka.apiciclismobackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Cyclist {
    @Id
    private String id;
    private String nameCyclist;
    private Integer numberCompetitor;
    private String nationalityCyclist;

}
