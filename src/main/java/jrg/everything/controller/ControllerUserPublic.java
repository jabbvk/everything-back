package jrg.everything.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jrg.everything.models.User;
import jrg.everything.service.UserService;
import jrg.everything.utils.PasswordUtil;

@Tag(name = "User")
@RestController
@RequestMapping("/user")
public class ControllerUserPublic {

    @Autowired
    private UserService userService;

    // ################################################################################################################
    //                                                  preRegister
    // ################################################################################################################
    @Operation(summary = "Confirmar registro de usuario", description = "Confirma el registro de un usuario con el código recibido en el email.")
    @PostMapping("/preRegister")
    public String preRegister(@RequestBody Map<String, Object> payload) {

        // sendCodeToEmail((String) payload.get("email"));

        return "register";
    }
    
    // ################################################################################################################
    //                                                  register
    // ################################################################################################################
    @Operation(summary = "Registrar usuario", description = "Registra a un usuario en la base de datos.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Cuerpo del request con datos del usuario.",
        content = @Content(
            examples = {
                @ExampleObject(
                    name = "Ejemplo de usuario",
                    value = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"password\": \"password123\", \"confirmPassword\": \"password123\" }"
                )
            }
        )
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", 
            description = "Usuario registrado correctamente.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = "{\"error\":\"Usuario registrado correctamente\"}")
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Error al registrar el usuario.",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = "{\"error\":\"Usuario no encontrado\"}")
            )
        )
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> payload) {

        // sendConfirmationToMail((String) payload.get("email"));

        User user = new User();
        user.setName((String) payload.get("name"));
        user.setEmail((String) payload.get("email"));
        user.setPassword(PasswordUtil.hashPassword((String) payload.get("password")));
        userService.saveUser(user);

        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
