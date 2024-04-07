package com.example.Project.Services;

import com.example.Project.Models.Maj;
import com.example.Project.Models.VersionMaj;
import com.example.Project.Repositories.MajRepository;
import com.example.Project.Repositories.VersionMajRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MajServiceImpl {
    private final MajRepository majRepo;
    private final VersionMajRepository versionMajRepo;
    int previousIdMaj = 0;

    public MajServiceImpl(MajRepository majRepo, VersionMajRepository versionMajRepo) {
        this.majRepo = majRepo;
        this.versionMajRepo = versionMajRepo;
    }

    List<VersionMaj> sousversions;

    public List<Maj> findAllPreviousMaj() {
        short currentOrdre = 195;
        return majRepo.findPreviousMaj(currentOrdre);
    }

    public int findPreviousMaj(int idMaj) {
        List<Maj> majs = findAllPreviousMaj();
        int previousIdMaj = 0; // Initialize previousIdMaj to handle cases where no previous Maj is found

        for (int i = 0; i < majs.size(); i++) {
            if (majs.get(i).getIdMaj() == idMaj) {
                if (i > 0) {
                    previousIdMaj = majs.get(i - 1).getIdMaj();
                    break; // Break out of the loop once the previous Maj is found
                }
            }
        }

        return previousIdMaj;
    }

    public List<VersionMaj> findSubVersions(int idMaj, String ListType) {
        List<VersionMaj> sousVersions;
        previousIdMaj = findPreviousMaj(idMaj);
        if (ListType == "Véhicule") {
            if (previousIdMaj == 0) {
                sousVersions = versionMajRepo.findByTestIntegrationAndPreviousVersionNull(idMaj);
            } else {
                sousVersions = versionMajRepo.findByTestIntegrationAndPreviousVersionNtNull(idMaj, previousIdMaj);
            }
        } else {
            if (previousIdMaj == 0) {
                sousVersions = versionMajRepo.findByTestUnitaireAndPreviousVersionNull(idMaj);
            } else {
                sousVersions = versionMajRepo.findByTestUnitaireAndPreviousVersionNtNull(idMaj, previousIdMaj);
            }
        }
        sousVersions = sousVersions.stream()
                .filter(versionMaj -> !versionMaj.getNomVer().endsWith(".xx"))
                .collect(Collectors.toList());

        return sousVersions;

    }
}
