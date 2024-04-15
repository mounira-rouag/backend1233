package com.example.Project.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="`PE_BY_MENU`")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PEByMenu {


    @Column(name="`IdPE`")

    private int IdPE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`IdMenu`")
    private Integer idMenu;

    @Column(name = "`Ligne`")
    private Integer ligne;

}
