package hu.tandtechniek.application.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KlantTest {

    @Test
    @DisplayName("adding an assignment to the list means the list should not be empty")
    void addOpdracht() {
        Klant klant = new Klant("Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test");

        Opdracht opdracht = new Opdracht("Test", "Dit is een Test");

        klant.addOpdracht(opdracht);

        assertFalse(klant.getOpdrachten().isEmpty());
    }

    @Test
    @DisplayName("full address should display correctly")
    void getHeelAdres() {
        Klant klant = new Klant("Test", "Test", "Test", "Test", "Test", "Amsterdam", "Keizersgracht", "1028CP");

        assertEquals("Keizersgracht 1028CP Amsterdam", klant.getHeelAdres());
    }
}