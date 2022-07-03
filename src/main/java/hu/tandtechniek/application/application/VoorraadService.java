package hu.tandtechniek.application.application;

import hu.tandtechniek.application.data.OpdrachtTypeRepository;
import hu.tandtechniek.application.data.VoorraadRepository;
import hu.tandtechniek.application.domain.Klant;
import hu.tandtechniek.application.domain.OpdrachtType;
import hu.tandtechniek.application.domain.Voorraad;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class VoorraadService {
    private final OpdrachtTypeRepository opdrachtTypeRepository;
    private final VoorraadRepository voorraadRepository;

    public VoorraadService(OpdrachtTypeRepository opdrachtTypeRepository, VoorraadRepository voorraadRepository) {
        this.opdrachtTypeRepository = opdrachtTypeRepository;
        this.voorraadRepository = voorraadRepository;
    }

    public Voorraad saveOpdrachtType(String voorraadnaam, int prijs, int aantal, int inVoorraad) {
        Voorraad voorraad = new Voorraad(voorraadnaam, prijs, aantal, inVoorraad);
        return voorraadRepository.save(voorraad);
    }

    public void koopVoorraad(int id, int aantal) {
        Voorraad voorraad = voorraadRepository.findByVoorraadId(id);
        voorraad.koopMeerVoorraad(aantal);
        voorraadRepository.save(voorraad);
    }

    public void deleteVoorraad(int id) {
        Voorraad voorraad = voorraadRepository.findByVoorraadId(id);
        voorraadRepository.delete(voorraad);
    }

    public List<Voorraad> findAllVoorraad() {
        return voorraadRepository.findAll();
    }

    public Voorraad getVoorraadById(int id) {
        return voorraadRepository.findByVoorraadId(id);
    }
}
