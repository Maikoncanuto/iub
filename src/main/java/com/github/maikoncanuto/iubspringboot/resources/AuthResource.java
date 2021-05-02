package com.github.maikoncanuto.iubspringboot.resources;

import com.github.maikoncanuto.iubspringboot.dtos.RequestIUB;
import com.github.maikoncanuto.iubspringboot.dtos.ResponseIUB;
import com.github.maikoncanuto.iubspringboot.services.PasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@Api(value = "Auth")
public class AuthResource {

    private final PasswordService passwordService;

    @PostMapping("/auth/validate-password")
    @ApiOperation(value = "Valida senha informada",
            notes = "Método que valida senha informada com alguns critérios")
    public ResponseEntity<ResponseIUB<Boolean>> validatePassword(
            @ApiParam(name = "payload", type = "String", value = "Senha informada", example = "M@1k0n#9876", required = true)
            @RequestBody final RequestIUB<String> payload) {

        final var responseValidatePassword = passwordService.validatePassword(payload.getPayload());

        return status(OK).body(new ResponseIUB<>(responseValidatePassword));
    }

}
