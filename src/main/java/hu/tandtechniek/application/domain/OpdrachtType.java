package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OpdrachtType {
    private int opdrachtTypeNummer;
    private String opdrachtTypeNaam;
    private String beschrijving;
    private int urenNodig;
    private List<Voorraad> voorraad;

    public OpdrachtType(String opdrachtTypeNaam, String beschrijving, int urenNodig) {
        this.opdrachtTypeNaam = opdrachtTypeNaam;
        this.beschrijving = beschrijving;
        this.urenNodig = urenNodig;
        this.voorraad = new ArrayList<Voorraad>();
    }

    public void addVoorraad(Voorraad voorraad) {
        this.voorraad.add(voorraad);
    }

}
