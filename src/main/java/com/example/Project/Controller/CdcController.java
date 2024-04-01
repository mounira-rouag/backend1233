package com.example.Project.Controller;

import com.example.Project.Models.CDC;
import com.example.Project.Models.Dev;
import com.example.Project.Models.Ecu;
import com.example.Project.Repositories.CDCRepository;
import com.example.Project.Repositories.DevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/cdc")
@RestController
public class CdcController {

    final CDCRepository cdcRepository;
    final DevRepository devRepository;

    public CdcController(CDCRepository cdcRepository, DevRepository devRepository) {
        this.cdcRepository = cdcRepository;
        this.devRepository = devRepository;
    }

    @GetMapping("/all")
    public List<CDC> getAllCdc() {
        return cdcRepository.findAll();
    }

    @PostMapping("cretae")
    public CDC saveCdc(@RequestBody CDC cdc){
        return cdcRepository.save(cdc);
    }

    @GetMapping("/{id}")
    public CDC getCdcById(@PathVariable int id){
        return cdcRepository.findById(id).get();
    }

    @GetMapping("/by-dev/{devid}")
    public CDC getCdcByDev(@PathVariable int devid){
        Dev dev=devRepository.getById(devid);
        return cdcRepository.findByDev(dev);
    }


}
