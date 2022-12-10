package uct.so.bloodbank_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uct.so.bloodbank_API.Modals.users;

import java.util.List;

@Repository
public interface usersRepo extends JpaRepository<users,Long> {

    @Query(value = "SELECT * FROM tb_users res  WHERE res.role_id = :id", nativeQuery = true)

    List<users> GetDonorUsers(@Param("id") Long id);
}
