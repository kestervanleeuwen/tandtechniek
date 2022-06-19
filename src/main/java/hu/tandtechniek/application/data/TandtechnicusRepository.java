package hu.tandtechniek.application.data;

import hu.tandtechniek.application.domain.Tandtechnicus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TandtechnicusRepository extends JpaRepository<Tandtechnicus, String> {
    Tandtechnicus findByTandtechnicusId(int id);
}
