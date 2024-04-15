package com.example.Project.Repositories;

import com.example.Project.Models.VersionMaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionMajRepository extends JpaRepository<VersionMaj,Integer> {
    List<VersionMaj> findVersionMajByIdMaj(int idMaj);

    @Query("SELECT v FROM VersionMaj v WHERE v.idMaj >= :idMaj ORDER BY v.ordre")
    List<VersionMaj> findByTestIntegrationAndPreviousVersionNull(@Param("idMaj") int idMaj);

    @Query("SELECT v FROM VersionMaj v WHERE v.idMaj = :idMaj OR v.idMaj >= :previousIdMaj ORDER BY v.nomVer")
    List<VersionMaj> findByTestIntegrationAndPreviousVersionNtNull(@Param("idMaj") int idMaj, @Param("previousIdMaj") int previousIdMaj);
    @Query("SELECT v FROM VersionMaj v WHERE v.idMaj = :idMaj ORDER BY v.ordre")
    List<VersionMaj> findByTestUnitaireAndPreviousVersionNull(@Param("idMaj") int idMaj);

    @Query("SELECT v FROM VersionMaj v WHERE v.idMaj = :idMaj OR v.idMaj = :previousIdMaj ORDER BY v.nomVer")
    List<VersionMaj> findByTestUnitaireAndPreviousVersionNtNull(@Param("idMaj") int idMaj, @Param("previousIdMaj") int previousIdMaj);
}
