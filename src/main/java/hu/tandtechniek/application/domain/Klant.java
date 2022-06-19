package hu.tandtechniek.application.domain;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "klant")
public class Klant {
    @Id
    @GeneratedValue
    private int klantId;
    @Column
    private String voornaam;
    @Column
    private String achternaam;
    @Column
    private String bedrijfsnaam;
    @Column
    private String email;
    @Column
    private String telefoon;
    @Column
    private String plaats;
    @Column
    private String adres;
    @Column
    private String postcode;
    @OneToMany
    @JsonIgnore
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

    public Klant() {

    }

    public void addOpdracht(Opdracht opdracht) {
        this.opdrachten.add(opdracht);
    }

    public String getHeelAdres() {
        return adres + " " + postcode + " " + plaats;
    }



}
