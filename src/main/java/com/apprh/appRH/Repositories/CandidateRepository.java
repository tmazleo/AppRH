package com.apprh.appRH.Repositories;

import com.apprh.appRH.models.AppRhOpportunity;
import com.apprh.appRH.models.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CandidateRepository extends CrudRepository<Candidate, String> {

    Iterable<Candidate> findByOpportunity(AppRhOpportunity opportunity);

    Candidate findByRg(String rg);

    Candidate findById(UUID id);

    List<Candidate> findByCandidateName(String candidateName);
}
