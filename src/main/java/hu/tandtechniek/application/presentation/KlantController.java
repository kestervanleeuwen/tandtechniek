package hu.tandtechniek.application.presentation;

import hu.tandtechniek.application.application.KlantService;
import hu.tandtechniek.application.domain.Klant;
import hu.tandtechniek.application.domain.Opdracht;
import hu.tandtechniek.application.presentation.DTO.KlantDTO;
import hu.tandtechniek.application.presentation.DTO.OpdrachtDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/klant")
public class KlantController {
    @Autowired private KlantService klantService;

    @PostMapping
    public ResponseEntity<Klant> saveKlant(@RequestBody KlantDTO klantDTO) {
        klantService.saveKlant(
                klantDTO.voornaam,
                klantDTO.achternaam,
                klantDTO.bedrijfsnaam,
                klantDTO.email,
                klantDTO.telefoon,
                klantDTO.plaats,
                klantDTO.adres,
                klantDTO.postcode
        );
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Klant>> getKlanten() {
        List<Klant> klanten = klantService.findAllKlanten();
        return ResponseEntity.ok(klanten);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Klant> getKlantById(@PathVariable int id) {
        Klant klant = klantService.getKlantById(id);
        if (klant == null) {
            return new ResponseEntity("klant met id: " + id + " is niet gevonden", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(klant);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Opdracht> deleteKlant(@PathVariable int id) {
        Klant klant = klantService.getKlantById(id);
        if (klant == null) {
            return new ResponseEntity("klant met id: " + id + " niet gevonden", HttpStatus.NOT_FOUND);
        }
        klantService.deleteKlant(id);
        return new ResponseEntity("Opdracht verwijderd", HttpStatus.OK);
    }
}
