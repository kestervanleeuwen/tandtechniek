package hu.tandtechniek.application.application;

import hu.tandtechniek.application.data.KlantRepository;
import hu.tandtechniek.application.data.OpdrachtRepository;
import hu.tandtechniek.application.data.OpdrachtTypeRepository;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OpdrachtService {
    private final OpdrachtRepository opdrachtRepository;
    private final TandtechnicusRepository tandtechnicusRepository;
    private final KlantRepository klantRepository;
    private final OpdrachtTypeRepository opdrachtTypeRepository;

    public OpdrachtService(OpdrachtRepository opdrachtRepository, TandtechnicusRepository tandtechnicusRepository, KlantRepository klantRepository, OpdrachtTypeRepository opdrachtTypeRepository) {
        this.opdrachtRepository = opdrachtRepository;
        this.tandtechnicusRepository = tandtechnicusRepository;
        this.klantRepository = klantRepository;
        this.opdrachtTypeRepository = opdrachtTypeRepository;
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
                                 int tandtechnicusId, int opdrachtTypeId) {
        Opdracht opdracht = new Opdracht(opdrachtNaam, omschrijving);
        Klant klant = klantRepository.findByKlantId(klantId);
        Tandtechnicus tandtechnicus = tandtechnicusRepository.findByTandtechnicusId(tandtechnicusId);


        OpdrachtType opdrachtType = opdrachtTypeRepository.findByOpdrachtTypeNummer(opdrachtTypeId);

        opdracht.setStartDatum(startdatum);
        opdracht.setEindDatum(einddatum);
        opdracht.setKlant(klant);
        opdracht.setTandtechnicus(tandtechnicus);
        opdracht.setOpdrachtType(opdrachtType);

        return opdrachtRepository.save(opdracht);
    }

    public Opdracht updateOpdracht(int opdrachtNummer, String opdrachtNaam, String omschrijving, Date startdatum, Date einddatum, int klantId,
                                   int tandtechnicusId, int opdrachtTypeId) {
        List <OpdrachtType> opdrachtTypes = new ArrayList<>();
        Opdracht opdracht = opdrachtRepository.findByOpdrachtNummer(opdrachtNummer);
        Klant klant = klantRepository.findByKlantId(klantId);
        Tandtechnicus tandtechnicus = tandtechnicusRepository.findByTandtechnicusId(tandtechnicusId);

        OpdrachtType opdrachtType = opdrachtTypeRepository.findByOpdrachtTypeNummer(opdrachtTypeId);

        opdracht.setOpdrachtNaam(opdrachtNaam);
        opdracht.setOmschrijving(omschrijving);
        opdracht.setStartDatum(startdatum);
        opdracht.setEindDatum(einddatum);
        opdracht.setKlant(klant);
        opdracht.setTandtechnicus(tandtechnicus);
        opdracht.setOpdrachtType(opdrachtType);

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
