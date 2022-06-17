package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "voorraad")
public class Voorraad {
    @Id
    @GeneratedValue
    private int voorraadId;
    @Column
    private String voorraadNaam;
    @Column
    private int prijs;
    @Column
    private int aantal;
    @Column
    private int inVoorraad;

    public Voorraad(String voorraadNaam, int prijs, int aantal, int voorraadAantal) {
        this.voorraadNaam = voorraadNaam;
        this.prijs = prijs;
        this.aantal = aantal;
        this.inVoorraad = voorraadAantal;
    }

    public Voorraad() {

    }

    public void koopMeerVoorraad(int aantal) {
        this.inVoorraad = inVoorraad + aantal;
    }
}
