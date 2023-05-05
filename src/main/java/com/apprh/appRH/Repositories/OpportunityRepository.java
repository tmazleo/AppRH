package com.apprh.appRH.Repositories;

import com.apprh.appRH.models.AppRhOpportunity;
import org.springframework.data.repository.CrudRepository; //method save

import java.util.List;
import java.util.UUID;

public interface OpportunityRepository extends CrudRepository<AppRhOpportunity, String> {
    AppRhOpportunity findById(UUID id);
    List<AppRhOpportunity> findByName(String name);
}
