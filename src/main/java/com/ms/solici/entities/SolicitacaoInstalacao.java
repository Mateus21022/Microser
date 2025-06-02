package com.ms.solici.entities;

import com.ms.solici.enums.StatusSolicitacao;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "solicitacao_instalacao")
public class SolicitacaoInstalacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_uso", nullable = false)
    private LocalDate dataUso;

    @Column(name = "observacao", length = 500)
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacao status;

    @Column(name = "laboratorio_id", nullable = false)
    private Long laboratorioId;

    @Column(name = "professor_id", nullable = false)
    private Long professorId;

    @ElementCollection
    @CollectionTable(name = "solicitacao_software", joinColumns = @JoinColumn(name = "solicitacao_id"))
    @Column(name = "software_id")
    private List<Long> softwareIds;

    public SolicitacaoInstalacao() {
        this.status = StatusSolicitacao.PENDENTE;
    }

    public SolicitacaoInstalacao(LocalDate dataUso, String observacao, Long laboratorioId,
            Long professorId, List<Long> softwareIds) {
        this();
        this.dataUso = dataUso;
        this.observacao = observacao;
        this.laboratorioId = laboratorioId;
        this.professorId = professorId;
        this.softwareIds = softwareIds;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataUso() {
        return dataUso;
    }

    public void setDataUso(LocalDate dataUso) {
        this.dataUso = dataUso;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public Long getLaboratorioId() {
        return laboratorioId;
    }

    public void setLaboratorioId(Long laboratorioId) {
        this.laboratorioId = laboratorioId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public List<Long> getSoftwareIds() {
        return softwareIds;
    }

    public void setSoftwareIds(List<Long> softwareIds) {
        this.softwareIds = softwareIds;
    }
}