package uct.so.bloodbank_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uct.so.bloodbank_API.Modals.recipients;

import java.util.List;

@Repository
public interface recipientsRepo extends JpaRepository<recipients,Long> {




    @Query(value = "SELECT * FROM tb_recipients R WHERE R.state_id LIKE CONCAT('%', ?1, '%')",nativeQuery = true)
    List<recipients> getByStateID(Long ID);
}
