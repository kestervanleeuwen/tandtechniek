package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "tandtechnicus")
public class Tandtechnicus {
    @Id
    @GeneratedValue
    private int tandtechnicusId;
    @Column
    private String voornaam;
    @Column
    private String achternaam;
    @Column
    private int salarisPerUur;
    @OneToMany
    private List<Opdracht> opdrachten;

    public Tandtechnicus(String voornaam, String achternaam, int salarisPerUur) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.salarisPerUur = salarisPerUur;
        opdrachten = new ArrayList<Opdracht>();
    }

    public Tandtechnicus() {

    }

    public void addOpdracht(Opdracht opdracht) {
        this.opdrachten.add(opdracht);
    }



}
