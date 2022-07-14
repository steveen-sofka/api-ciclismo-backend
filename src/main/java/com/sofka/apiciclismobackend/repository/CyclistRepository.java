package com.sofka.apiciclismobackend.repository;

import com.sofka.apiciclismobackend.entities.Cyclist;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CyclistRepository extends ReactiveCrudRepository<Cyclist, String> {

}
