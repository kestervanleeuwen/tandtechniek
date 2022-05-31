package hu.tandtechniek.application.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpdrachtTypeTest {

    @Test
    @DisplayName("adding an assignment to the list means the list should not be empty")
    void addVoorraad() {
        OpdrachtType opdrachtType = new OpdrachtType("Test", "Dit is een Test", 10);

        Voorraad voorraad = new Voorraad("Test", 10, 10, 10);

        opdrachtType.addVoorraad(voorraad);

        assertFalse(opdrachtType.getVoorraad().isEmpty());
    }
}