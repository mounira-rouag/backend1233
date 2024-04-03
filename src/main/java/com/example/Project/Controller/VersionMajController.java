package com.example.Project.Controller;

import com.example.Project.Models.Famille;
import com.example.Project.Models.Vehicule;
import com.example.Project.Models.VersionMaj;
import com.example.Project.Repositories.VersionMajRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/version-Maj")
public class VersionMajController {
    private final VersionMajRepository versionMajRepo;

    public VersionMajController(VersionMajRepository versionMajRepo) {
        this.versionMajRepo = versionMajRepo;
    }
    @GetMapping("/find-idmaj/{idmaj}")
    public List<VersionMaj> getVersionMajByMajId(@PathVariable int idmaj) {

        return versionMajRepo.findVersionMajByIdMaj(idmaj);
    }

}
