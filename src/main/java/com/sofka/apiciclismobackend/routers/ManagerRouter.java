package com.sofka.apiciclismobackend.routers;

import com.sofka.apiciclismobackend.entities.Cyclist;
import com.sofka.apiciclismobackend.models.CyclistDTO;
import com.sofka.apiciclismobackend.models.TeamDTO;
import com.sofka.apiciclismobackend.usecases.CreatedCyclistUseCase;
import com.sofka.apiciclismobackend.usecases.CreatedTeamUseCase;
import com.sofka.apiciclismobackend.usecases.GetTeamUseCase;
import com.sofka.apiciclismobackend.usecases.GetTeamsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ManagerRouter {

    // guardar Team
    @Bean
    public RouterFunction<ServerResponse> addTeam(CreatedTeamUseCase createdTeamUseCase){

        Function<TeamDTO, Mono<ServerResponse>> executor = TeamDTO -> createdTeamUseCase
                .apply(TeamDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(
                POST("/team").and(accept(MediaType.APPLICATION_JSON)),
                request -> request
                        .bodyToMono(TeamDTO.class)
                        .flatMap(executor)
        );

    }

    // obtener todos los teams
    @Bean
    public RouterFunction<ServerResponse> getTeams(GetTeamsUseCase getTeamsUseCase){
        return route(GET("/teams"),
            request -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromPublisher(getTeamsUseCase.get(),
                            TeamDTO.class)));
    }

    // Obtener un team por ID
    @Bean
    public RouterFunction<ServerResponse> getTeamByID(GetTeamUseCase getTeamUseCase){
        return route(GET("team/{id}").and(accept(MediaType.APPLICATION_JSON)),
                    request -> ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(getTeamUseCase.apply(
                                    request.pathVariable("id")),
                                    TeamDTO.class))
        );
    }

    // Eliminar team por ID
    @Bean
    public RouterFunction<ServerResponse> deleteTeamByID(GetTeamUseCase getTeamUseCase){
        return route(DELETE("team/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getTeamUseCase.deleteTeam(request.pathVariable("id")),Void.class))
        );
    }

    // Agregar Ciclista al team por id
    @Bean
    public RouterFunction<ServerResponse> addCyclist(CreatedCyclistUseCase createdCyclistUseCase) {
        return route(
                PUT("/team/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    String idTeam = request.pathVariable("id");
                    Mono<CyclistDTO> cyclistDTOMono =  request.bodyToMono(CyclistDTO.class);
                    return cyclistDTOMono.flatMap(cyclistDTO ->
                                    createdCyclistUseCase.addCyclist(cyclistDTO, idTeam))
                                    .flatMap(result -> ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(result));
                });
    }
}
