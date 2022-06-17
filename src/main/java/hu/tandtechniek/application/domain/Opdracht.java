package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity(name = "opdracht")
public class Opdracht {
    @Id
    @GeneratedValue
    private int opdrachtNummer;
    @Column
    private String opdrachtNaam;
    @Column
    private String omschrijving;
    @Column
    private int totaalKosten;
    @Column
    private int totaalPrijs;
    @Column
    private Date startDatum;
    @Column
    private Date eindDatum;
    @Column
    private String status;
    @ManyToOne
    private Klant klant;
    @OneToMany
    private List<OpdrachtType> opdrachtTypes;
    @ManyToOne
    private Tandtechnicus tandtechnicus;

    public Opdracht(String opdrachtNaam, String omschrijving) {
        this.opdrachtNaam = opdrachtNaam;
        this.omschrijving = omschrijving;
        this.status = "open";
    }

    public Opdracht() {

    }

    public void berekenTotaalKosten() {
        int nieuwTotaalKosten = 0;
        for (OpdrachtType opdrachtType : opdrachtTypes) {
            nieuwTotaalKosten += opdrachtType.getUrenNodig() * tandtechnicus.getSalarisPerUur();
            for (Voorraad voorraad : opdrachtType.getVoorraad()) {
                nieuwTotaalKosten += voorraad.getPrijs() * voorraad.getAantal();
            }
        }
         totaalKosten = nieuwTotaalKosten;
    }

    public void berekenTotaalPrijs(int winstMarge) {
        totaalPrijs = (int) (totaalKosten * (1 + (double) 10 / 100));
    }

    public boolean validateDates() {
        return startDatum.before(eindDatum);
    }

    public void finishOpdracht() {
        status = "closed";
        for (OpdrachtType opdrachtType : opdrachtTypes) {
            for (Voorraad voorraad : opdrachtType.getVoorraad()) {
                voorraad.setInVoorraad(voorraad.getInVoorraad() - voorraad.getAantal());
            }
        }
    }


}
