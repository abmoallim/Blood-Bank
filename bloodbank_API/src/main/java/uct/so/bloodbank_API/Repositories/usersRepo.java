package uct.so.bloodbank_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uct.so.bloodbank_API.Modals.users;

@Repository
public interface usersRepo extends JpaRepository<users,Long> {
}
