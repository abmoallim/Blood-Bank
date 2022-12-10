package uct.so.bloodbank_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uct.so.bloodbank_API.Modals.donors;

@Repository
public interface donorsRepo extends JpaRepository<donors,Long> {

    @Modifying
    @Query(value = "update tb_donors d set d.user_id = 2 where d.id = 1",nativeQuery = true)
    void UpdateUID();

}
