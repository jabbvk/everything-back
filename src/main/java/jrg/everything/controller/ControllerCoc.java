package jrg.everything.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jrg.everything.errors.Errors;
import jrg.everything.service.CocService;

@Tag(name = "Clash of clans")
@RestController
@RequestMapping("/coc")
public class ControllerCoc {

    @Autowired
    private CocService cocService;

    @Operation(summary = "Obtener info de un usuario", description = "Devuelve la información de un usuario de Clash of Clans.")
    @Parameter(
        name = "tag",
        description = "TAG del usuario a consultar.",
        example = "890G28CCY",
        required = true
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Cuerpo del request con datos del usuario.",
        content = @Content(
            examples = {
                @ExampleObject(
                    name = "Ejemplo de consulta",
                    value = "{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjU4ZTU5ZTUzLWZiMTEtNDc4Mi05NWFmLTkyMzJlMTgzOGE2OSIsImlhdCI6MTczMTk1MjAzNSwic3ViIjoiZGV2ZWxvcGVyLzQxNDE4N2ZmLWY1OTYtMzg5NC0zYjRjLTA4MDcyNGMwMmM0MCIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjE4OC4xMjcuMTcxLjQwIl0sInR5cGUiOiJjbGllbnQifV19.h2sHZERvIkBay3otu5GIuZKK1HsZ5YkZc0Xfj4hWlLf0Z3B7z2O4jpEkg5H_Z_Cpmbs_N2kLEYrODZpU58rEQw\"}"
                )
            }
        )
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", 
            description = "Información del usuario.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = "{\"tag\":\"890G28CCY\"}")
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "TAG no encontrado.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = "{\"error\":\"COC_PLAYER_NOT_FOUND\"}")
            )
        )
    })
    @PostMapping("/playerInfo/{tag}")
    public ResponseEntity<String> getPlayerInfo(@PathVariable String tag, @RequestBody Map<String, Object> payload) {

        String token = (String) payload.get("token");

        String playerInfo = cocService.getPlayerInfo(tag, token);
        
        if (playerInfo == null) {
            return new ResponseEntity<>(Errors.COC_PLAYER_NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(playerInfo, HttpStatus.OK);
    }
}

