package com.medcare.medcare.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medcare.medcare.model.Medicamento;
import com.medcare.medcare.repository.MedicamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicamentoService {
    
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    public List<Medicamento> findAll() {
        return medicamentoRepository.findAllByOrderByCriadoEmDesc();
    }
    
    public Optional<Medicamento> findById(Long id) {
        return medicamentoRepository.findById(id);
    }
    
    public Medicamento save(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }
    
    public void deleteById(Long id) {
        medicamentoRepository.deleteById(id);
    }
    
    public List<Medicamento> findByAtivoTrue() {
        return medicamentoRepository.findByAtivoTrue();
    }
    
    public List<Medicamento> findMedicamentosDeHoje() {
        return medicamentoRepository.findMedicamentosAtivosNaData(LocalDate.now());
    }
    
    public List<Medicamento> searchByNome(String nome) {
        return medicamentoRepository.findByNomeMedicamentoContainingIgnoreCase(nome);
    }
    
    public long count() {
        return medicamentoRepository.count();
    }
    
    public long countByAtivoTrue() {
        return medicamentoRepository.countByAtivoTrue();
    }

    @Transactional
    public void marcarComoTomado(Long id, Boolean tomado) {
        medicamentoRepository.atualizarStatusTomado(id, tomado, LocalDateTime.now());
    }

}