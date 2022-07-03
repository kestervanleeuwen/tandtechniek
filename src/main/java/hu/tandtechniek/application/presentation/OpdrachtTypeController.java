package hu.tandtechniek.application.presentation;

import hu.tandtechniek.application.application.OpdrachtTypeService;
import hu.tandtechniek.application.domain.Opdracht;
import hu.tandtechniek.application.domain.OpdrachtType;
import hu.tandtechniek.application.presentation.DTO.OpdrachtTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "opdrachtType")
@Secured("ROLE_USER")
public class OpdrachtTypeController {
    @Autowired private OpdrachtTypeService opdrachtTypeService;

    @PostMapping
    public ResponseEntity<OpdrachtType> saveOpdrachtType(@Valid @RequestBody OpdrachtTypeDTO opdrachtTypeDTO) {
        opdrachtTypeService.saveOpdrachtType (
                opdrachtTypeDTO.opdrachtTypeNaam,
                opdrachtTypeDTO.beschrijving,
                opdrachtTypeDTO.urenNodig,
                opdrachtTypeDTO.voorraadIds
        );
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<OpdrachtType> updateOpdrachtType(@Valid @RequestBody OpdrachtTypeDTO opdrachtTypeDTO) {
        opdrachtTypeService.updateOpdrachtType (
                opdrachtTypeDTO.opdrachtTypeNummer,
                opdrachtTypeDTO.opdrachtTypeNaam,
                opdrachtTypeDTO.beschrijving,
                opdrachtTypeDTO.urenNodig,
                opdrachtTypeDTO.voorraadIds
        );
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OpdrachtType>> getOpdrachtTypes() {
        List<OpdrachtType> opdrachtTypes = opdrachtTypeService.findAllOpdrachtType();
        return ResponseEntity.ok(opdrachtTypes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OpdrachtType> getOpdrachtTypeById(@PathVariable int id) {
        OpdrachtType opdrachtType = opdrachtTypeService.getOpdrachtTypeById(id);
        if (opdrachtType == null) {
            return new ResponseEntity("opdrachtType met id: " + id + " is niet gevonden", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(opdrachtType);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Opdracht> deleteOpdrachtType(@PathVariable int id) {
        OpdrachtType opdrachtType = opdrachtTypeService.getOpdrachtTypeById(id);
        if (opdrachtType == null) {
            return new ResponseEntity("opdrachtType met id: " + id + " niet gevonden", HttpStatus.NOT_FOUND);
        }
        opdrachtTypeService.deleteOpdrachtType(id);
        return new ResponseEntity("opdrachtType verwijderd", HttpStatus.OK);
    }

}
