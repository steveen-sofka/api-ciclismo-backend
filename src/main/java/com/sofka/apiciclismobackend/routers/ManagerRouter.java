package com.sofka.apiciclismobackend.routers;

import com.sofka.apiciclismobackend.models.TeamDTO;
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

    // Obtener un team por
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

}
