package com.example.Project.Repositories;

import com.example.Project.Models.Dev;
import com.example.Project.Models.Maj;
import com.example.Project.Models.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MajRepository extends JpaRepository<Maj, Integer> {
    @Query("SELECT m FROM Maj m WHERE m.ordre < :currentOrdre ORDER BY m.ordre ASC")
    List<Maj> findPreviousMaj(@Param("currentOrdre") Short currentOrdre);

    Maj findMajByDev(Dev dev);
}
