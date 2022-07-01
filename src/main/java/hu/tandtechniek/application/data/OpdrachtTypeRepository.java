package hu.tandtechniek.application.data;

import hu.tandtechniek.application.domain.OpdrachtType;
import hu.tandtechniek.application.domain.Tandtechnicus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpdrachtTypeRepository extends JpaRepository<OpdrachtType, String> {
    OpdrachtType findByOpdrachtTypeNummer(int id);
}