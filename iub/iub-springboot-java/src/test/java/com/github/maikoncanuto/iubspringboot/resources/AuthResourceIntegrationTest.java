package com.github.maikoncanuto.iubspringboot.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.maikoncanuto.iubspringboot.dtos.RequestIUB;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthResourceIntegrationTest {

    private static final String PATH_AUTH_VALIDATE_PASSWORD = "/auth/validate-password";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @ValueSource(strings = {
            "AbTp9!fok",
            "1mIk@n&(9)-02",
            "C@nuto&M1K0#^2-3*4+7%$(9)!"
    })
    void deve_Testar_Senha_Informada_Pelo_Endpoint_E_Retornar_True(final String password) throws Exception {

        final var request = new RequestIUB<>(password);

        mockMvc.perform(post(PATH_AUTH_VALIDATE_PASSWORD)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "C@nut@-Pedr@s#",
            "#C@nuto#-Pedr%!09",
            "C@nuto&M1K0#^2-3*4+7%$(9)!!",
            "AbTp9 fok", "#C@nuto #-Pedr%!09",
            "C@nuto&M1K0#^2  -3*4+7%$(9)!!",
            "AbTp9!foo"
    })
    void deve_Testar_Senha_Errada_Informada_Pelo_Endpoint_E_Retornar_False(final String password) throws Exception {

        final var request = new RequestIUB<>(password);

        mockMvc.perform(post(PATH_AUTH_VALIDATE_PASSWORD)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(false));
    }

}
