package Innso.innso.repository;

import Innso.innso.entity.ClientCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCaseRepository extends JpaRepository<ClientCase, Long> {
}
