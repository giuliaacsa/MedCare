package com.medcare.medcare.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamentos")
public class Medicamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome_medicamento", nullable = false)
    private String nomeMedicamento;
    
    @Column(name = "dosagem_valor", nullable = false)
    private Double dosagemValor;
    
    @Column(name = "dosagem_unidade", nullable = false)
    private String dosagemUnidade;
    
    @Column(name = "frequencia_por_dia", nullable = false)
    private Integer frequenciaPorDia;
    
    @Column(name = "horarios_personalizados", length = 1000)
    private String horariosPersonalizados;
    
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    
    @Column(name = "data_fim")
    private LocalDate dataFim;
    
    @Column(name = "observacoes", length = 2000)
    private String observacoes;
    
    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

     // APENAS ESTES DOIS CAMPOS NOVOS
     @Column(name = "tomado")
     private Boolean tomado = false;
     
     @Column(name = "ultima_tomada")
     private LocalDateTime ultimaTomada;
    
    // ... getters e setters ...
    
    public Boolean getTomado() { return tomado; }
    public void setTomado(Boolean tomado) { 
        this.tomado = tomado;
        if (tomado) {
            this.ultimaTomada = LocalDateTime.now();
        }
    }
    
    public LocalDateTime getUltimaTomada() { return ultimaTomada; }
    public void setUltimaTomada(LocalDateTime ultimaTomada) { this.ultimaTomada = ultimaTomada; }
    
    // Construtores
    public Medicamento() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public Medicamento(String nomeMedicamento, Double dosagemValor, String dosagemUnidade, 
                      Integer frequenciaPorDia, LocalDate dataInicio) {
        this();
        this.nomeMedicamento = nomeMedicamento;
        this.dosagemValor = dosagemValor;
        this.dosagemUnidade = dosagemUnidade;
        this.frequenciaPorDia = frequenciaPorDia;
        this.dataInicio = dataInicio;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNomeMedicamento() { return nomeMedicamento; }
    public void setNomeMedicamento(String nomeMedicamento) { 
        this.nomeMedicamento = nomeMedicamento; 
    }
    
    public Double getDosagemValor() { return dosagemValor; }
    public void setDosagemValor(Double dosagemValor) { this.dosagemValor = dosagemValor; }
    
    public String getDosagemUnidade() { return dosagemUnidade; }
    public void setDosagemUnidade(String dosagemUnidade) { this.dosagemUnidade = dosagemUnidade; }
    
    public Integer getFrequenciaPorDia() { return frequenciaPorDia; }
    public void setFrequenciaPorDia(Integer frequenciaPorDia) { this.frequenciaPorDia = frequenciaPorDia; }
    
    public String getHorariosPersonalizados() { return horariosPersonalizados; }
    public void setHorariosPersonalizados(String horariosPersonalizados) { 
        this.horariosPersonalizados = horariosPersonalizados; 
    }
    
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { 
        this.ativo = ativo; 
        this.atualizadoEm = LocalDateTime.now();
    }
    
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
    
    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return "Medicamento{" +
                "id=" + id +
                ", nomeMedicamento='" + nomeMedicamento + '\'' +
                ", dosagemValor=" + dosagemValor +
                ", dosagemUnidade='" + dosagemUnidade + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}