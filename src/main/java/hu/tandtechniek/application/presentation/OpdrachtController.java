package hu.tandtechniek.application.presentation;

import hu.tandtechniek.application.application.OpdrachtService;
import hu.tandtechniek.application.domain.Opdracht;
import hu.tandtechniek.application.presentation.DTO.OpdrachtDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("opdracht")
@Secured("ROLE_USER")
public class OpdrachtController {
    @Autowired private OpdrachtService opdrachtService;

    @PostMapping
    public ResponseEntity<Opdracht> saveOpdracht(@Valid @RequestBody OpdrachtDTO opdrachtDTO) {
        opdrachtService.saveOpdracht (
                opdrachtDTO.opdrachtNaam,
                opdrachtDTO.omschrijving,
                opdrachtDTO.startDatum,
                opdrachtDTO.eindDatum,
                opdrachtDTO.klantId,
                opdrachtDTO.tandtechnicusId
        );
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Opdracht>> getOpdrachten() {
        List<Opdracht> opdrachten = opdrachtService.findAllOpdrachten();
        return ResponseEntity.ok(opdrachten);
    }

}
