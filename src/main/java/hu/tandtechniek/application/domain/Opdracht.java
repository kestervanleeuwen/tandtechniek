package hu.tandtechniek.application.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDatum;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date eindDatum;
    @Column
    private String status;
    @ManyToOne
    @JoinColumn(name = "klant_id")
    private Klant klant;
    @OneToOne
    private OpdrachtType opdrachtType;
    @ManyToOne
    @JoinColumn(name = "tandtechnicus_id")
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
            nieuwTotaalKosten = opdrachtType.getUrenNodig() * tandtechnicus.getSalarisPerUur();
            for (Voorraad voorraad : opdrachtType.getVoorraad()) {
                nieuwTotaalKosten += voorraad.getPrijs() * voorraad.getAantal();
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
        if (opdrachtType != null) {
            for (Voorraad voorraad : opdrachtType.getVoorraad()) {
                voorraad.setInVoorraad(voorraad.getInVoorraad() - voorraad.getAantal());
            }
        }

    }


}
