package hu.tandtechniek.application.application;

import hu.tandtechniek.application.data.OpdrachtTypeRepository;
import hu.tandtechniek.application.data.VoorraadRepository;
import hu.tandtechniek.application.domain.Klant;
import hu.tandtechniek.application.domain.OpdrachtType;
import hu.tandtechniek.application.domain.Voorraad;

import java.util.ArrayList;
import java.util.List;

public class OpdrachtTypeService {
    private final OpdrachtTypeRepository opdrachtTypeRepository;
    private final VoorraadRepository voorraadRepository;

    public OpdrachtTypeService(OpdrachtTypeRepository opdrachtTypeRepository, VoorraadRepository voorraadRepository) {
        this.opdrachtTypeRepository = opdrachtTypeRepository;
        this.voorraadRepository = voorraadRepository;
    }

    public OpdrachtType saveOpdrachtType(String opdrachtTypeNaam, String beschrijving, int urenNodig, List<Integer> voorraadIds) {
        List<Voorraad> voorraadList = new ArrayList<>();
        for (int id : voorraadIds) {
            Voorraad voorraad = voorraadRepository.findByVoorraadId(id);
            voorraadList.add(voorraad);
        }
        OpdrachtType opdrachtType = new OpdrachtType(opdrachtTypeNaam, beschrijving, urenNodig);
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
