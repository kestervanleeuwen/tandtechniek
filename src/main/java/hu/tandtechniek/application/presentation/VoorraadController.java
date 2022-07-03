package hu.tandtechniek.application.presentation;

import hu.tandtechniek.application.application.VoorraadService;
import hu.tandtechniek.application.domain.Opdracht;
import hu.tandtechniek.application.domain.OpdrachtType;
import hu.tandtechniek.application.domain.Voorraad;
import hu.tandtechniek.application.presentation.DTO.OpdrachtTypeDTO;
import hu.tandtechniek.application.presentation.DTO.VoorraadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "voorraad")
@Secured("ROLE_USER")
public class VoorraadController {
    @Autowired private VoorraadService voorraadService;

    @PostMapping
    public ResponseEntity<Voorraad> saveVoorraad(@Valid @RequestBody VoorraadDTO voorraadDTO) {
        voorraadService.saveOpdrachtType (
                voorraadDTO.voorraadNaam,
                voorraadDTO.prijs,
                voorraadDTO.aantal,
                voorraadDTO.inVoorraad
        );
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Voorraad>> getVoorraad() {
        List<Voorraad> voorraadList = voorraadService.findAllVoorraad();
        return ResponseEntity.ok(voorraadList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Voorraad> getByVoorraadId(@PathVariable int id) {
        Voorraad voorraad = voorraadService.getVoorraadById(id);
        if (voorraad == null) {
            return new ResponseEntity("voorraad met id: " + id + " is niet gevonden", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(voorraad);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Voorraad> deleteVoorraad(@PathVariable int id) {
        Voorraad voorraad = voorraadService.getVoorraadById(id);
        if (voorraad == null) {
            return new ResponseEntity("voorraad met id: " + id + " niet gevonden", HttpStatus.NOT_FOUND);
        }
        voorraadService.deleteVoorraad(id);
        return new ResponseEntity("Voorraad verwijderd", HttpStatus.OK);
    }
}
