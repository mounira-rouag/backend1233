package com.example.Project.Controller;

import com.example.Project.Dto.Dto;
import com.example.Project.Models.Dev;
import com.example.Project.Models.Maj;
import com.example.Project.Models.Marque;
import com.example.Project.Models.VersionMaj;
import com.example.Project.Repositories.DevRepository;
import com.example.Project.Repositories.MajRepository;
import com.example.Project.Services.MajServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/maj")
public class MAJController {

    private final MajRepository majRepo;
    private final MajServiceImpl majServieImpl;
    private final DevRepository devRepository;
    public MAJController(MajRepository majRepo, MajServiceImpl majServieImpl, DevRepository devRepository) {
        this.majRepo = majRepo;
        this.majServieImpl = majServieImpl;
        this.devRepository = devRepository;
    }
    int previousIdMaj=0;
    @GetMapping("/all-versions")
    public List<Maj> getAllVersions() {
        return majRepo.findAll();
}

    @PostMapping("maj/creat")
    public Maj createMaj(@RequestBody Maj maj){

        return majRepo.save(maj);
    }
    @GetMapping("/{id}")
    public Maj finMajById(@PathVariable int id){
        return majRepo.findById(id).get();
    }

    @GetMapping("/findByDev/{id}")
    public Maj finMajByDev(@PathVariable int id){
        Dev dev=devRepository.findById(id).get();
        return majRepo.findMajByDev(dev);
    }

   /** @GetMapping("/previous-maj")
    public List<Maj> getPreviousMaj() {

        return majServieImpl.findPreviousMaj();
    }*/

    /**@GetMapping("/persiousrecorde/{idMaj}")
    public ResponseEntity<Integer> findPreviousMaj (@PathVariable int idMaj){
        List<Maj> majs=majServieImpl.findPreviousMaj();
        for (int i = 0; i < majs.size(); i++) {
            if (majs.get(i).getIdMaj() == idMaj) {

                if (i > 0) {
                     previousIdMaj = majs.get(i - 1).getIdMaj();
                    return ResponseEntity.ok(previousIdMaj);
                } else {
                    // If the current Maj is the first one in the list, return -1
                    // or handle this case based on your requirements
                    return ResponseEntity.notFound().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }*/
    @GetMapping("/sub-versions/{idMaj}/{listType}")
    public List<VersionMaj> getSubVersions(@PathVariable int idMaj, @PathVariable String listType) {
        List<VersionMaj> sousVersions = majServieImpl.findSubVersions(idMaj, listType);
        return sousVersions;
    }
}
