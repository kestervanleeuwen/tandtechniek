package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Opdracht {
    private int opdrachtNummer;
    private String opdrachtNaam;
    private String omschrijving;
    private int totaalKosten;
    private int totaalPrijs;
    private Date startDatum;
    private Date eindDatum;
    private String status;
    private Klant klant;
    private List<OpdrachtType> opdrachtTypes;
    private Tandtechnicus tandtechnicus;

    public Opdracht(String opdrachtNaam, String omschrijving) {
        this.opdrachtNaam = opdrachtNaam;
        this.omschrijving = omschrijving;
        this.status = "open";
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
