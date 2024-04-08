package al.lhind.tab.claim.repository;

import al.lhind.tab.claim.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
