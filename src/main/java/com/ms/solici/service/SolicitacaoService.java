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
    private SolicitacaoRepository repository;

    public List<SolicitacaoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SolicitacaoResponseDTO buscarPorId(Long id) {
        Solicitacao s = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada: " + id));
        return toResponse(s);
    }

    public SolicitacaoResponseDTO salvar(SolicitacaoRequestDTO dto) {
        Solicitacao s = new Solicitacao();
        s.setSoftware(dto.getSoftware());
        s.setSolicitante(dto.getSolicitante());
        s.setDataSolicitacao(LocalDate.now());

        return toResponse(repository.save(s));
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Solicitação não encontrada: " + id);
        }
        repository.deleteById(id);
    }

    private SolicitacaoResponseDTO toResponse(Solicitacao s) {
        return new SolicitacaoResponseDTO(
                s.getId(),
                s.getSoftware(),
                s.getSolicitante(),
                s.getDataSolicitacao());
    }
}
