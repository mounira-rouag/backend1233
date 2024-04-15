package com.example.Project.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "`CABLES`")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_CABLE`")
    private Integer idCable;

    @Column(name = "`ACTIA_NAME`")
    private String actiaName;

    @Column(name = "`ATAL_NAME`")
    private String atalName;

    @Column(name = "`VCI`")
    private Integer vci;

    @Column(name = "`NumDico`")
    private Integer numDico;
    @Column(name = "`name`")
  private String Name;

    @JsonIgnore
    @ManyToMany(mappedBy = "cables")
    private List<Dev> devs;
}
