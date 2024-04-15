package com.example.Project.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="`POINT_ENTREE`")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PointEntree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`IdPE`")
    private Integer idPE;

    @Column(name = "`Description`")
    private String description;

    @Column(name = "`IdAction`")
    private Integer idAction;



    @Column(name = "`AfficheMenu`")
    private boolean afficheMenu;

    @Column(name = "`Autodetect`")
    private boolean autodetect;

    @Column(name = "`AutoLect`")
    private boolean autoLect;

    @Column(name = "`NumDico`")
    private Integer numDico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`IdFamille`", referencedColumnName = "`IdFamille`", insertable = false, updatable = false)
    private Famille famille;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "`PE_BY_MENU`",
            joinColumns = @JoinColumn(name = "`IdPE`"),
            inverseJoinColumns = @JoinColumn(name = "`IdMenu`"))
    private List<Menu> menus;
}
