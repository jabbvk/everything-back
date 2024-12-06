package jrg.everything.errors;

// Enumeración de errores
public enum Errors {

    // Errores HTTP
    INTERNAL_SERVER_ERROR,
    NOT_FOUND,

    // Errores de usuario
    USER_NOT_FOUND,
    USER_FAIL_LOGIN,
    USER_PASSWORD_NOT_MATCH,

    // Errores de coc
    COC_PLAYER_NOT_FOUND;
}
