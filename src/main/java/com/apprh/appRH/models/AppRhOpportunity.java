package com.apprh.appRH.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_OPPORTUNITY")
public class AppRhOpportunity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String descricption;

    @NotEmpty
    private String date;

    @NotEmpty
    private String remunerationValue;

    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.REMOVE) // um para muitos, quando deletar uma vaga, deleta os candidatos
    private List<Candidate> candidates;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricption() {
        return descricption;
    }

    public void setDescricption(String descricption) {
        this.descricption = descricption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemunerationValue() {
        return remunerationValue;
    }

    public void setRemunerationValue(String remunerationValue) {
        this.remunerationValue = remunerationValue;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
