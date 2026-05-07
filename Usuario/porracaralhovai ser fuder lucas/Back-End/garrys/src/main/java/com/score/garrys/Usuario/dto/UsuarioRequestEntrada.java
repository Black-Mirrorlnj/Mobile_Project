package com.score.garrys.dto;

class UsuarioRequest {

    //  Atributos
    private String username;
    private String password;
    private String email;

    //  Construtor vazio
    UsuarioRequest() {
    }

    //  Construtor completo
    UsuarioRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //  Getter e Setter - username
    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    //  Getter e Setter - password
    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    //  Getter e Setter - email
    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }
}
