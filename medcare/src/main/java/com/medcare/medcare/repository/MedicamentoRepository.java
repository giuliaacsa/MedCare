package com.medcare.medcare.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medcare.medcare.model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    
    List<Medicamento> findByAtivoTrue();
    
    List<Medicamento> findByAtivo(Boolean ativo);
    
    List<Medicamento> findAllByOrderByCriadoEmDesc();
    
    @Query("SELECT m FROM Medicamento m WHERE m.ativo = true AND m.dataInicio <= :data AND (m.dataFim IS NULL OR m.dataFim >= :data)")
    List<Medicamento> findMedicamentosAtivosNaData(@Param("data") LocalDate data);
    
    List<Medicamento> findByNomeMedicamentoContainingIgnoreCase(String nome);
    
    long countByAtivoTrue();

    @Modifying
    @Query("UPDATE Medicamento m SET m.tomado = :tomado, m.ultimaTomada = :data WHERE m.id = :id")
    void atualizarStatusTomado(@Param("id") Long id, @Param("tomado") Boolean tomado, @Param("data") LocalDateTime data);
}