package hu.tandtechniek.application.data;

import hu.tandtechniek.application.domain.Tandtechnicus;
import hu.tandtechniek.application.domain.Voorraad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoorraadRepository extends JpaRepository<VoorraadRepository, String> {
    Voorraad findByVoorraadId(int id);
}
