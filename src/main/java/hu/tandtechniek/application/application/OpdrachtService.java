package hu.tandtechniek.application.application;

import hu.tandtechniek.application.data.KlantRepository;
import hu.tandtechniek.application.data.OpdrachtRepository;
import hu.tandtechniek.application.data.TandtechnicusRepository;
import hu.tandtechniek.application.domain.Klant;
import hu.tandtechniek.application.domain.Opdracht;
import hu.tandtechniek.application.domain.OpdrachtType;
import hu.tandtechniek.application.domain.Tandtechnicus;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OpdrachtService {
    private final OpdrachtRepository opdrachtRepository;
    private final TandtechnicusRepository tandtechnicusRepository;
    private final KlantRepository klantRepository;

    public OpdrachtService(OpdrachtRepository opdrachtRepository, TandtechnicusRepository tandtechnicusRepository, KlantRepository klantRepository) {
        this.opdrachtRepository = opdrachtRepository;
        this.tandtechnicusRepository = tandtechnicusRepository;
        this.klantRepository = klantRepository;
    }

    public Opdracht findOpdrachtById(int id) {
        return opdrachtRepository.findByOpdrachtNummer(id);
    }

    public List<Opdracht> findAllOpdrachten() {
        return opdrachtRepository.findAll();
    }

    public List<Opdracht> findAllOpdrachtenByTandtechnicus(int id) {
        Tandtechnicus tandtechnicus = tandtechnicusRepository.findByTandtechnicusId(id);
        return opdrachtRepository.findAllByTandtechnicus(tandtechnicus);
    }

    public Opdracht saveOpdracht(String opdrachtNaam, String omschrijving, Date startdatum, Date einddatum, int klantId,
                                 int tandtechnicusId) {
        Opdracht opdracht = new Opdracht(opdrachtNaam, omschrijving);
        Klant klant = klantRepository.findByKlantId(klantId);
        Tandtechnicus tandtechnicus = tandtechnicusRepository.findByTandtechnicusId(tandtechnicusId);

        opdracht.setStartDatum(startdatum);
        opdracht.setEindDatum(einddatum);
        opdracht.setKlant(klant);
        opdracht.setTandtechnicus(tandtechnicus);

        return opdrachtRepository.save(opdracht);
    }

    public Opdracht updateOpdracht(int opdrachtNummer, String opdrachtNaam, String omschrijving, Date startdatum, Date einddatum, int klantId,
                                   int tandtechnicusId) {
        Opdracht opdracht = opdrachtRepository.findByOpdrachtNummer(opdrachtNummer);
        Klant klant = klantRepository.findByKlantId(klantId);
        Tandtechnicus tandtechnicus = tandtechnicusRepository.findByTandtechnicusId(tandtechnicusId);

        opdracht.setOpdrachtNaam(opdrachtNaam);
        opdracht.setOmschrijving(omschrijving);
        opdracht.setStartDatum(startdatum);
        opdracht.setEindDatum(einddatum);
        opdracht.setKlant(klant);
        opdracht.setTandtechnicus(tandtechnicus);

        return opdrachtRepository.save(opdracht);
    }

    public void deleteOpdracht(int id) {
        Opdracht opdracht = opdrachtRepository.findByOpdrachtNummer(id);
        opdrachtRepository.delete(opdracht);
    }
    public Opdracht finishOpdracht(int id) {
        Opdracht opdracht = opdrachtRepository.findByOpdrachtNummer(id);
        opdracht.finishOpdracht();
        return opdrachtRepository.save(opdracht);
    }




}
