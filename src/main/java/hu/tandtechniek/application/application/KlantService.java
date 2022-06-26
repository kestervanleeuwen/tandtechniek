package hu.tandtechniek.application.application;

import hu.tandtechniek.application.data.KlantRepository;
import hu.tandtechniek.application.domain.Klant;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class KlantService {
    private final KlantRepository klantRepository;

    public KlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    public Klant saveKlant(String voornaam, String achternaam, String bedrijfsnaam, String email, String telefoon,
                              String plaats, String adres, String postcode) {

        Klant klant = new Klant(voornaam, achternaam, bedrijfsnaam, email, telefoon, plaats, adres, postcode);
        return klantRepository.save(klant);
    }

    public void deleteKlant(int id) {
        Klant klant = klantRepository.findByKlantId(id);
        klantRepository.delete(klant);
    }

    public List<Klant> findAllKlanten() {
        return klantRepository.findAll();
    }

    public Klant getKlantById(int id) {
        return klantRepository.findByKlantId(id);
    }
}

