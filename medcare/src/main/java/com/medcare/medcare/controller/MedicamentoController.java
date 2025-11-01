package com.medcare.medcare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medcare.medcare.model.Medicamento;
import com.medcare.medcare.service.MedicamentoService;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {
    
    @Autowired
    private MedicamentoService medicamentoService;
    
    // GET - Listar todos os medicamentos
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMedicamentos() {
        try {
            List<Medicamento> medicamentos = medicamentoService.findAll();
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "sucesso");
            response.put("mensagem", "Medicamentos encontrados.");
            response.put("dados", medicamentos);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return criarRespostaErro("Erro ao buscar medicamentos: " + e.getMessage());
        }
    }
    
    // GET - Buscar medicamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getMedicamentoById(@PathVariable Long id) {
        try {
            Optional<Medicamento> medicamento = medicamentoService.findById(id);
            
            if (medicamento.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "sucesso");
                response.put("mensagem", "Medicamento encontrado.");
                response.put("dados", medicamento.get());
                
                return ResponseEntity.ok(response);
            } else {
                return criarRespostaErro("Medicamento não encontrado.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return criarRespostaErro("Erro ao buscar medicamento: " + e.getMessage());
        }
    }
    
    // GET - Medicamentos de hoje
    @GetMapping("/hoje")
    public ResponseEntity<Map<String, Object>> getMedicamentosDeHoje() {
        try {
            List<Medicamento> medicamentos = medicamentoService.findMedicamentosDeHoje();
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "sucesso");
            response.put("mensagem", "Medicamentos de hoje encontrados.");
            response.put("dados", medicamentos);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return criarRespostaErro("Erro ao buscar medicamentos de hoje: " + e.getMessage());
        }
    }
    
    // GET - Medicamentos ativos
    @GetMapping("/ativos")
    public ResponseEntity<Map<String, Object>> getMedicamentosAtivos() {
        try {
            List<Medicamento> medicamentos = medicamentoService.findByAtivoTrue();
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "sucesso");
            response.put("mensagem", "Medicamentos ativos encontrados.");
            response.put("dados", medicamentos);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return criarRespostaErro("Erro ao buscar medicamentos ativos: " + e.getMessage());
        }
    }
    
    // POST - Criar novo medicamento
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMedicamento(@RequestBody Medicamento medicamento) {
        try {
            Medicamento savedMedicamento = medicamentoService.save(medicamento);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "sucesso");
            response.put("mensagem", "Medicamento criado com sucesso.");
            response.put("id", savedMedicamento.getId());
            response.put("dados", savedMedicamento);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return criarRespostaErro("Erro ao criar medicamento: " + e.getMessage());
        }
    }
    
    // PUT - Atualizar medicamento
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamentoDetails) {
        try {
            Optional<Medicamento> medicamentoOptional = medicamentoService.findById(id);
            
            if (medicamentoOptional.isPresent()) {
                Medicamento medicamento = medicamentoOptional.get();
                
                // Atualizar campos
                medicamento.setNomeMedicamento(medicamentoDetails.getNomeMedicamento());
                medicamento.setDosagemValor(medicamentoDetails.getDosagemValor());
                medicamento.setDosagemUnidade(medicamentoDetails.getDosagemUnidade());
                medicamento.setFrequenciaPorDia(medicamentoDetails.getFrequenciaPorDia());
                medicamento.setHorariosPersonalizados(medicamentoDetails.getHorariosPersonalizados());
                medicamento.setDataInicio(medicamentoDetails.getDataInicio());
                medicamento.setDataFim(medicamentoDetails.getDataFim());
                medicamento.setObservacoes(medicamentoDetails.getObservacoes());
                medicamento.setAtivo(medicamentoDetails.getAtivo());
                
                Medicamento updatedMedicamento = medicamentoService.save(medicamento);
                
                Map<String, Object> response = new HashMap<>();
                response.put("status", "sucesso");
                response.put("mensagem", "Medicamento atualizado com sucesso.");
                response.put("dados", updatedMedicamento);
                
                return ResponseEntity.ok(response);
            } else {
                return criarRespostaErro("Medicamento não encontrado.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return criarRespostaErro("Erro ao atualizar medicamento: " + e.getMessage());
        }
    }
    
    // DELETE - Excluir medicamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMedicamento(@PathVariable Long id) {
        try {
            Optional<Medicamento> medicamento = medicamentoService.findById(id);
            
            if (medicamento.isPresent()) {
                medicamentoService.deleteById(id);
                
                Map<String, Object> response = new HashMap<>();
                response.put("status", "sucesso");
                response.put("mensagem", "Medicamento excluído com sucesso.");
                
                return ResponseEntity.ok(response);
            } else {
                return criarRespostaErro("Medicamento não encontrado.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return criarRespostaErro("Erro ao excluir medicamento: " + e.getMessage());
        }
    }
    
    // GET - Buscar medicamentos por nome
    @GetMapping("/buscar")
    public ResponseEntity<Map<String, Object>> searchMedicamentos(@RequestParam String nome) {
        try {
            List<Medicamento> medicamentos = medicamentoService.searchByNome(nome);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "sucesso");
            response.put("mensagem", "Busca realizada com sucesso.");
            response.put("dados", medicamentos);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return criarRespostaErro("Erro na busca: " + e.getMessage());
        }
    }
    
    // GET - Health check da API
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "sucesso");
        response.put("mensagem", "API MedCare está funcionando!");
        response.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(response);
    }
    
    // Método auxiliar para criar respostas de erro
    private ResponseEntity<Map<String, Object>> criarRespostaErro(String mensagem) {
        return criarRespostaErro(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private ResponseEntity<Map<String, Object>> criarRespostaErro(String mensagem, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "erro");
        errorResponse.put("mensagem", mensagem);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @PutMapping("/{id}/tomado")
    public ResponseEntity<Map<String, Object>> marcarComoTomado(@PathVariable Long id, @RequestParam Boolean tomado) {
        try {
            medicamentoService.marcarComoTomado(id, tomado);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "sucesso");
            response.put("mensagem", tomado ? "Medicamento marcado como tomado!" : "Medicamento desmarcado");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return criarRespostaErro("Erro ao atualizar status: " + e.getMessage());
        }
    }
}