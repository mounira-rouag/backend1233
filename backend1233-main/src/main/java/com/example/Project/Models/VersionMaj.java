package com.example.Project.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="`VERSION_MAJ`")
public class VersionMaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`IdVer`")
    private int IdVer;

    @Column(name = "`NomVer`")
    private String nomVer;

    @Column(name = "`Ordre`")
    private Integer ordre;

    @Column(name = "`IdMaj`")
    private int idMaj;

    @Column(name = "`Compilateur`")
    private String compilateur;

    @Column(name = "`MainUpdateVer`")
    private String mainUpdateVer;

}
