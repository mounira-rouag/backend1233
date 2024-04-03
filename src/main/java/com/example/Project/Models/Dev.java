package com.example.Project.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "`DEV`")
public class Dev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`IdDev`")
    private int id;
    @Column(name = "`NomDev`")
    private String devname;
    @Column(name = "`NomDLL`")
    private String dll;
    @Column(name = "`DevDuplique`")
    private boolean devDuplique;
    @Column(name = "`DevComment`")
    private String devComment;
    @Column(name = "`NumBugzilla`")
    private String numBug;
    private String Jira;
    @Column(name = "`IdMajPrecedent`")
    private String IdMajPrecedent;

    @ManyToOne
    @JoinColumn(name = "`IdCDC`")
    private CDC cdc;


    @ManyToOne
    @JoinColumn(name = "`IdEcu`")
    private Ecu ecu;

    @ManyToOne
    @JoinColumn(name = "`IdMaj`")
    private Maj maj;

    @ManyToOne
    @JoinColumn(name = "`IdEtatDev`")
    private EtatDev etatdev;

    @ManyToOne
    @JoinColumn(name = "`IdMenu`")
    private Menu menu;

    public Maj getMaj() {
        return maj;
    }

    @ManyToOne
    @JoinColumn(name = "`IdRC`")
    private User user;

    @ManyToMany
    @JoinTable(  name = "`VEH_BY_DEV`",
            joinColumns = @JoinColumn(name = "`IdDev`"),
            inverseJoinColumns ={ @JoinColumn(name = "`GRPMOD`"),

                                }
    )
    private List<Vehicule> vehicules;


    @ManyToMany
    @JoinTable(  name = "`FCT_BY_DEV`",
            joinColumns = @JoinColumn(name = "`IdDev`"),
            inverseJoinColumns ={ @JoinColumn(name = "`IdFonction`"),

            }
    )
    private List<Fonction> fonctions;


    @ManyToMany
    @JoinTable(  name = "`VALID_BY_DEV`",
            joinColumns = @JoinColumn(name = "`IdDev`"),
            inverseJoinColumns ={ @JoinColumn(name = "`IdValid`"),

            }
    )
    private List<Validation> validations;
    @JsonIgnore
    @ManyToMany
    @JoinTable(  name = "`CABLE_BY_DEV`",
            joinColumns = @JoinColumn(name = "`IdDev`"),
            inverseJoinColumns ={ @JoinColumn(name = "`ID_CABLE`"),

            }
    )
    private List<Cables> cables;
}
