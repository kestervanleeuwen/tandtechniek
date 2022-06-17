package hu.tandtechniek.application.domain;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "opdrachtType")
public class OpdrachtType {
    @Id
    @GeneratedValue
    private int opdrachtTypeNummer;
    @Column
    private String opdrachtTypeNaam;
    @Column
    private String beschrijving;
    @Column
    private int urenNodig;
    @OneToMany
    private List<Voorraad> voorraad;

    public OpdrachtType(String opdrachtTypeNaam, String beschrijving, int urenNodig) {
        this.opdrachtTypeNaam = opdrachtTypeNaam;
        this.beschrijving = beschrijving;
        this.urenNodig = urenNodig;
        this.voorraad = new ArrayList<Voorraad>();
    }

    public OpdrachtType() {

    }

    public void addVoorraad(Voorraad voorraad) {
        this.voorraad.add(voorraad);
    }

}
