package hu.tandtechniek.application.data;

import hu.tandtechniek.application.domain.Opdracht;
import hu.tandtechniek.application.domain.Tandtechnicus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpdrachtRepository extends JpaRepository<Opdracht, String> {
    Optional<Opdracht> findByOpdrachtNummer(Integer id);
    List<Opdracht> findAllByTandtechnicus(Tandtechnicus tandtechnicus);
}
