package com.sofka.apiciclismobackend.routers;

import com.sofka.apiciclismobackend.models.CyclistDTO;
import com.sofka.apiciclismobackend.models.TeamDTO;
import com.sofka.apiciclismobackend.usecases.*;
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

    // 🟪🟪🟪🟪🟪🟪---> CRUD TEAM <---🟪🟪🟪🟪🟪
    // Created Team and Update Team ✔
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

    // Get Team by ID ✔
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

    // Get all teams ✔
    @Bean
    public RouterFunction<ServerResponse> getTeams(GetTeamsUseCase getTeamsUseCase){
        return route(GET("/teams"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamsUseCase.get(),
                                TeamDTO.class)));
    }
    // Delete team by ID ✔
    @Bean
    public RouterFunction<ServerResponse> deleteTeamByID(GetTeamUseCase getTeamUseCase){
        return route(DELETE("team/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getTeamUseCase.deleteTeam(request.pathVariable("id")),Void.class))
        );
    }

    // 🟨🟨🟨🟨🟨---> CRUD CYCLIST <---🟨🟨🟨🟨🟨

    // Add Cyclist at Team By Id ✔
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

    // Get All Cyclists ✔
    @Bean
    public RouterFunction<ServerResponse> getCyclists(GetCyclistsUseCase getCyclistsUseCase){
        return route(GET("/cyclists"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistsUseCase.get(),
                                Object.class)));
    }

    // Obtener ciclista by id ✔
    @Bean
    public RouterFunction<ServerResponse> getCyclistByID(GetCyclistsUseCase getCyclistsUseCase){
        return route(GET("cyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistsUseCase.getCyclistById(
                                        request.pathVariable("id")),
                                Object.class))
        );
    }

    // Delete Cyclist By Id ❌
    @Bean
    public RouterFunction<ServerResponse> deleteCyclistById(GetCyclistsUseCase getCyclistsUseCase){
        return route(DELETE("cyclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getCyclistsUseCase.deleteCyclist(request.pathVariable("id")),Object.class))
        );
    }

    // 🟦🟦🟦🟦🟦 ---> ENDPOINTS CUSTOMIZED <--- 🟦🟦🟦🟦🟦

    // Get Cyclists By CodeTeam ✔
    @Bean
    public RouterFunction<ServerResponse> getCyclistsByCodeTeam(GetTeamsUseCase getTeamsUseCase){
        return route(GET("cyclists/{codeTeam}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamsUseCase.getCyclistsByCode(
                                        request.pathVariable("codeTeam")),
                                TeamDTO.class))
        );
    }

    // Get Team By Country ✔
    @Bean
    public RouterFunction<ServerResponse> getTeamByCountry(GetTeamsUseCase getTeamsUseCase){
        return route(GET("team/country/{countryTeam}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTeamsUseCase.getTeamByCountry(
                                        request.pathVariable("countryTeam")),
                                TeamDTO.class))
        );
    }

    // Get Cyclist By NationalityCyclist
    @Bean
    public RouterFunction<ServerResponse> getCyclistsByNationatily(GetCyclistsUseCase getCyclistsUseCase){
        return route(GET("/cyclist/nationality/{nationality}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getCyclistsUseCase.getCyclistByNationality(
                                        request.pathVariable("nationality")
                                ),
                                Object.class)));
    }

}
