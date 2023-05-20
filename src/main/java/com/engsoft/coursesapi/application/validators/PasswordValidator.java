package com.engsoft.coursesapi.application.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    /**
     * Valida uma senha de acordo com as seguintes regras:
     *
     * - Mínimo de 8 caracteres;
     * - Pelo menos uma letra minúscula;
     * - Pelo menos uma letra maiúscula;
     * - Pelo menos um número;
     * - Pelo menos um caractere especial: @#$%^&+=!;
     * - Não pode ter espaços em branco.
     *
     * @param {string} password A senha a ser validada.
     * @returns {boolean} Retorna `true` se a senha é válida, `false` caso contrário.
     */
    public static boolean validate(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}