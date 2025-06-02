package com.ms.solici.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String software;
    private String solicitante;
    private LocalDate dataSolicitacao;

    public Solicitacao() {
    }

    public Solicitacao(Long id, String software, String solicitante, LocalDate dataSolicitacao) {
        this.id = id;
        this.software = software;
        this.solicitante = solicitante;
        this.dataSolicitacao = dataSolicitacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "id=" + id +
                ", software='" + software + '\'' +
                ", solicitante='" + solicitante + '\'' +
                ", dataSolicitacao=" + dataSolicitacao +
                '}';
    }
}
