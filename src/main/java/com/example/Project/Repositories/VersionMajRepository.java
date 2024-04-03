package com.example.Project.Repositories;

import com.example.Project.Models.VersionMaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionMajRepository extends JpaRepository<VersionMaj,Integer> {
    List<VersionMaj> findVersionMajByIdMaj(int idMaj);
}
