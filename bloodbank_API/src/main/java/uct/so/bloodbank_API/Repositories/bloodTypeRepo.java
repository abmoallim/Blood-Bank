package uct.so.bloodbank_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uct.so.bloodbank_API.Modals.bloodType;

@Repository
public interface bloodTypeRepo extends JpaRepository<bloodType,Long> {
}
