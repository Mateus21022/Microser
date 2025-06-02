package com.ms.solici.dto;

import jakarta.validation.constraints.NotBlank;

public class SolicitacaoRequestDTO {

    @NotBlank
    private String software;

    @NotBlank
    private String solicitante;

    public SolicitacaoRequestDTO() {
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
}
