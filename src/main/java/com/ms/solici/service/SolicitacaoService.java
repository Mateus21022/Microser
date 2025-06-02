package com.ms.solici.service;

import com.ms.solici.dto.SolicitacaoRequestDTO;
import com.ms.solici.dto.SolicitacaoResponseDTO;
import com.ms.solici.exception.RecursoNaoEncontradoException;
import com.ms.solici.model.Solicitacao;
import com.ms.solici.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    public List<SolicitacaoResponseDTO> listarTodas() {
        return solicitacaoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public SolicitacaoResponseDTO buscarPorId(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada com ID: " + id));
        return converterParaResponseDTO(solicitacao);
    }

    public SolicitacaoResponseDTO criar(SolicitacaoRequestDTO requestDTO) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setSoftware(requestDTO.getSoftware());
        solicitacao.setSolicitante(requestDTO.getSolicitante());
        solicitacao.setDataSolicitacao(LocalDate.now());

        Solicitacao solicitacaoSalva = solicitacaoRepository.save(solicitacao);
        return converterParaResponseDTO(solicitacaoSalva);
    }

    public SolicitacaoResponseDTO atualizar(Long id, SolicitacaoRequestDTO requestDTO) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada com ID: " + id));

        solicitacao.setSoftware(requestDTO.getSoftware());
        solicitacao.setSolicitante(requestDTO.getSolicitante());

        Solicitacao solicitacaoAtualizada = solicitacaoRepository.save(solicitacao);
        return converterParaResponseDTO(solicitacaoAtualizada);
    }

    public void deletar(Long id) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Solicitação não encontrada com ID: " + id);
        }
        solicitacaoRepository.deleteById(id);
    }

    private SolicitacaoResponseDTO converterParaResponseDTO(Solicitacao solicitacao) {
        return new SolicitacaoResponseDTO(
                solicitacao.getId(),
                solicitacao.getSoftware(),
                solicitacao.getSolicitante(),
                solicitacao.getDataSolicitacao()
        );
    }
}