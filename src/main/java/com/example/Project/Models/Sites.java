package com.example.Project.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name="`SITES`")
public class Sites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`IdSite`")
    private int IdSite;
    @Column(name = "`NomSite`")
    private String NomSite;

    @JsonIgnore
    @OneToMany(mappedBy = "sites")
    private List<CDC> cdc;


    @JsonIgnore
    @OneToMany(mappedBy = "sites")
    private List<Dev> dev;
    public Sites(int idSite ) {
        IdSite = idSite;

    }
}
