package hu.tandtechniek.application.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpdrachtTest {
    private Tandtechnicus tandtechnicus;
    private Voorraad voorraad;
    private OpdrachtType opdrachtType;
    private Klant klant;
    private Opdracht opdracht;

    @BeforeEach
    public void init() {
        tandtechnicus = new Tandtechnicus("Jan", "Jansen", 100);
        voorraad = new Voorraad("Voorraad", 10, 1, 100);
        klant = new Klant("KlantNaam", "KlantAdres", "KlantPostcode", "KlantPlaats", "0624855521", "Amsterdam", "Keizersgracht", "1021CP");

        List<Voorraad> voorraadLijst = new ArrayList<Voorraad>();
        voorraadLijst.add(voorraad);

        opdrachtType = new OpdrachtType("OpdrachtTypeNaam", "Beschrijving", 10);
        opdrachtType.setVoorraad(voorraadLijst);

        opdracht = new Opdracht("opdrachtTest", "Dit is een test");

        opdracht.setTandtechnicus(tandtechnicus);
        opdracht.setKlant(klant);
        opdracht.setOpdrachtType(opdrachtType);

    }

    @Test
    @DisplayName("asserts true if the calculation equals the correct amount")
    void berekenTotaalKosten() {

        opdracht.berekenTotaalKosten();

        assertEquals(1010, opdracht.getTotaalKosten());
    }

    @Test
    @DisplayName("asserts true if the calculation equals the correct amount of profit (+10%)")
    void berekenTotaalPrijs() {
        opdracht.berekenTotaalKosten();
        opdracht.berekenTotaalPrijs(10);

        assertEquals(1111, opdracht.getTotaalPrijs());
    }

    @Test
    @DisplayName("should return false if startDate is after endDate")
    void validateFalseDates() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = format.parse("01-02-2020");
        Date endDate = format.parse("01-01-2020");

        opdracht.setStartDatum(startDate);
        opdracht.setEindDatum(endDate);

        assertFalse(opdracht.validateDates());
    }

    @Test
    @DisplayName("should return true if startDate is before endDate")
    void validateTrueDates() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = format.parse("01-01-2020");
        Date endDate = format.parse("01-02-2020");

        opdracht.setStartDatum(startDate);
        opdracht.setEindDatum(endDate);

        assertTrue(opdracht.validateDates());
    }

    @Test
    @DisplayName("should set status to closed and substract the amount from the voorraad")
    void finishOpdracht() {
        opdracht.finishOpdracht();
        assertEquals("closed", opdracht.getStatus());
        assertEquals(99, voorraad.getInVoorraad());
    }
}