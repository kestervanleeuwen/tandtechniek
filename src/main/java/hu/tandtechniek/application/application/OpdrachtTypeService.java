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
public class OpdrachtTypeService {
    private final OpdrachtTypeRepository opdrachtTypeRepository;
    private final VoorraadRepository voorraadRepository;

    public OpdrachtTypeService(OpdrachtTypeRepository opdrachtTypeRepository, VoorraadRepository voorraadRepository) {
        this.opdrachtTypeRepository = opdrachtTypeRepository;
        this.voorraadRepository = voorraadRepository;
    }

    public OpdrachtType saveOpdrachtType(String opdrachtTypeNaam, String beschrijving, int urenNodig, List<Integer> voorraadIds) {
        List<Voorraad> voorraadList = new ArrayList<>();
        OpdrachtType opdrachtType = new OpdrachtType(opdrachtTypeNaam, beschrijving, urenNodig);
        if (voorraadIds.size() > 0) {
            for (int id : voorraadIds) {
                Voorraad voorraad = voorraadRepository.findByVoorraadId(id);
                voorraadList.add(voorraad);
            }
            opdrachtType.setVoorraad(voorraadList);
        }
        return opdrachtTypeRepository.save(opdrachtType);
    }

    public OpdrachtType updateOpdrachtType(int opdrachtTypeNummer, String opdrachtTypeNaam, String beschrijving, int urenNodig, List<Integer> voorraadIds) {
        List<Voorraad> voorraadList = new ArrayList<>();
        for (int id : voorraadIds) {
            Voorraad voorraad = voorraadRepository.findByVoorraadId(id);
            voorraadList.add(voorraad);
        }
        OpdrachtType opdrachtType = opdrachtTypeRepository.findByOpdrachtTypeNummer(opdrachtTypeNummer);
        opdrachtType.setOpdrachtTypeNaam(opdrachtTypeNaam);
        opdrachtType.setBeschrijving(beschrijving);
        opdrachtType.setUrenNodig(urenNodig);
        opdrachtType.setVoorraad(voorraadList);
        return opdrachtTypeRepository.save(opdrachtType);
    }

    public void deleteOpdrachtType(int id) {
        OpdrachtType opdrachtType = opdrachtTypeRepository.findByOpdrachtTypeNummer(id);
        opdrachtTypeRepository.delete(opdrachtType);
    }

    public List<OpdrachtType> findAllOpdrachtType() {
        return opdrachtTypeRepository.findAll();
    }

    public OpdrachtType getOpdrachtTypeById(int id) {
        return opdrachtTypeRepository.findByOpdrachtTypeNummer(id);
    }
}
