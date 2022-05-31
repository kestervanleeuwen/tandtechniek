package hu.tandtechniek.application.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoorraadTest {

    @Test
    @DisplayName("buying more supply should increase the amount of supply correctly")
    void koopMeerVoorraadT() {
        Voorraad voorraad = new Voorraad("Test", 10, 10, 10);

        voorraad.koopMeerVoorraad(10);

        assertEquals(20, voorraad.getInVoorraad());
    }
}