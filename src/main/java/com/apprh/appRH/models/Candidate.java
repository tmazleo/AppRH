package com.apprh.appRH.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table(name = "TB_CANDIDATE")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,unique = true)
    @NotEmpty
    private String rg;

    @Column(nullable = false,unique = true)
    @NotEmpty
    private String candidateName;

    @Column(nullable = false,unique = true)
    @NotEmpty
    private String email;
    @ManyToOne
    private AppRhOpportunity opportunity;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppRhOpportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(AppRhOpportunity opportunity) {
        this.opportunity = opportunity;
    }
}
