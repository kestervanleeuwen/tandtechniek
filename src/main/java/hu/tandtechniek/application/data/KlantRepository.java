package hu.tandtechniek.application.data;

import hu.tandtechniek.application.domain.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlantRepository extends JpaRepository<Klant, String> {
    Klant findByKlantId(int id);
}
