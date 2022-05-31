package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Klant {
    private int klantId;
    private String voornaam;
    private String achternaam;
    private String bedrijfsnaam;
    private String email;
    private String telefoon;
    private String plaats;
    private String adres;
    private String postcode;
    private List<Opdracht> opdrachten;

    public Klant(String vn, String an, String bn, String email,
                 String tlfn, String plaats, String adres, String postcode) {
        this.voornaam = vn;
        this.achternaam = an;
        this.bedrijfsnaam = bn;
        this.email = email;
        this.telefoon = tlfn;
        this.plaats = plaats;
        this.adres = adres;
        this.postcode = postcode;
        opdrachten = new ArrayList<Opdracht>();
    }

    public void addOpdracht(Opdracht opdracht) {
        this.opdrachten.add(opdracht);
    }

    public String getHeelAdres() {
        return adres + " " + postcode + " " + plaats;
    }



}
