package com.ms.solici.controller;

import com.ms.solici.dto.SolicitacaoRequestDTO;
import com.ms.solici.dto.SolicitacaoResponseDTO;
import com.ms.solici.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
@CrossOrigin(origins = "*")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoResponseDTO>> listarTodas() {
        List<SolicitacaoResponseDTO> solicitacoes = solicitacaoService.listarTodas();
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        SolicitacaoResponseDTO solicitacao = solicitacaoService.buscarPorId(id);
        return ResponseEntity.ok(solicitacao);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoResponseDTO> criar(@Valid @RequestBody SolicitacaoRequestDTO requestDTO) {
        SolicitacaoResponseDTO solicitacao = solicitacaoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> atualizar(@PathVariable Long id, 
                                                           @Valid @RequestBody SolicitacaoRequestDTO requestDTO) {
        SolicitacaoResponseDTO solicitacao = solicitacaoService.atualizar(id, requestDTO);
        return ResponseEntity.ok(solicitacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        solicitacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Microserviço de Solicitações está funcionando!");
    }
}