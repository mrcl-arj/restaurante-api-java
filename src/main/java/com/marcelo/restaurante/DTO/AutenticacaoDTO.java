package com.marcelo.restaurante.DTO;

public class AutenticacaoDTO {

    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AutenticacaoDTO(String jwt) {
        this.jwt = jwt;
    }
}
