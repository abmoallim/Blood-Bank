package uct.so.bloodbank_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uct.so.bloodbank_API.Modals.donors;

@Repository
public interface donorsRepo extends JpaRepository<donors,Long> {
}
