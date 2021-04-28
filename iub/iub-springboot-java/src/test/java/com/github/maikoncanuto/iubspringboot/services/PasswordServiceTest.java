package com.github.maikoncanuto.iubspringboot.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PasswordServiceTest {

    @Autowired
    private PasswordService passwordService;

    @Test
    void deve_Testar_Password_Nulo_E_Retornar_False() {
        assertFalse(passwordService.validatePassword(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "    "})
    void deve_Testar_Password_Em_Branco_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaa", "bbbbbbb", "1111111", "11223344"})
    void deve_Testar_Password_Com_Letras_Repetidas_E_Minino_Tamanho_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "AbcD", "ccbb"})
    void deve_Testar_Password_Com_Letras_Diferentes_E_Minino_Tamanho_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = "AAAbbbCc")
    void deve_Testar_Password_Com_Palavras_E_Letras_Repetidas_E_Tamanhos_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = "AbTp9!foo")
    void deve_Testar_Password_Com_Palavras_E_Letras_Repetidas_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = "AbTp9!foo")
    void deve_Testar_Password_Com_Palavras_E_Letras_Repetidas_Com_Especiais_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbTp9 fok", "#C@nuto #-Pedr%!09", "C@nuto&M1K0#^2  -3*4+7%$(9)!!"})
    void deve_Testar_Password_Com_Palavras_E_Espacos_Em_Branco_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"C@nut@-Pedr@s#", "#C@nuto#-Pedr%!09", "C@nuto&M1K0#^2-3*4+7%$(9)!!"})
    void deve_Testar_Password_Com_Caracters_Repetidos_E_Retornar_False(final String password) {
        assertFalse(passwordService.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"AbTp9!fok", "1mIk@n&(9)-02", "C@nuto&M1K0#^2-3*4+7%$(9)!"})
    void deve_Testar_Password_Com_Modelo_Esperado_E_Retornar_True(final String password) {
        assertTrue(passwordService.validatePassword(password));
    }

}
