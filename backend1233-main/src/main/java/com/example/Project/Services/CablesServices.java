package com.example.Project.Services;

import com.example.Project.Models.Cables;
import com.example.Project.Repositories.CablesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CablesServices {

    private final CablesRepository cablesRepository;

    public CablesServices(CablesRepository cablesRepository) {
        this.cablesRepository = cablesRepository;
    }


    public List<Cables> selectAllAdaptersExcept(String specificName) {
        return cablesRepository.findAll()
                .stream()
                .filter(adapter -> !adapter.getActiaName().equals(specificName) && !adapter.getAtalName().equals(specificName))
                .collect(Collectors.toList());
    }
}
