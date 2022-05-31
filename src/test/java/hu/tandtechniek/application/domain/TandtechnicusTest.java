package hu.tandtechniek.application.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TandtechnicusTest {

    @Test
    @DisplayName("adding an assignment to the list means the list should not be empty")
    void addOpdracht() {
        Tandtechnicus tandtechnicus = new Tandtechnicus("Test", "Test", 100);

        Opdracht opdracht = new Opdracht("Test", "Dit is een Test");

        tandtechnicus.addOpdracht(opdracht);

        assertFalse(tandtechnicus.getOpdrachten().isEmpty());
    }
}