package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Voorraad {
    private int voorraadId;
    private String voorraadNaam;
    private int prijs;
    private int aantal;
    private int inVoorraad;

    public Voorraad(String voorraadNaam, int prijs, int aantal, int voorraadAantal) {
        this.voorraadNaam = voorraadNaam;
        this.prijs = prijs;
        this.aantal = aantal;
        this.inVoorraad = voorraadAantal;
    }

    public void koopMeerVoorraad(int aantal) {
        this.inVoorraad = inVoorraad + aantal;
    }
}
