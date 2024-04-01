package com.example.Project.Repositories;

import com.example.Project.Models.VersionMaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionMajRepository extends JpaRepository<VersionMaj,Integer> {
}
