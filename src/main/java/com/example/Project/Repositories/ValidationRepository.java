package com.example.Project.Repositories;

import com.example.Project.Models.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation,Integer> {

    @Query("SELECT v.nomVer FROM VersionMaj v JOIN Validation val ON v.IdVer = val.IdVer WHERE val.IdValid = ?1")
    String findVersionNameByValidationId(int validationId);
}
