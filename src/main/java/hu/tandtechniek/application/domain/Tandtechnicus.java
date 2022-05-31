package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tandtechnicus {
    private int tandtechnicusId;
    private String voornaam;
    private String achternaam;
    private int salarisPerUur;
    private List<Opdracht> opdrachten;

    public Tandtechnicus(String voornaam, String achternaam, int salarisPerUur) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.salarisPerUur = salarisPerUur;
        opdrachten = new ArrayList<Opdracht>();
    }

    public void addOpdracht(Opdracht opdracht) {
        this.opdrachten.add(opdracht);
    }



}
