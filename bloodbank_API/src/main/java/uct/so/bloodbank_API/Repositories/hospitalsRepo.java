package uct.so.bloodbank_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import uct.so.bloodbank_API.Modals.hospitals;

import java.util.List;

@Repository
public interface hospitalsRepo extends JpaRepository<hospitals,Long> {

    //name LIKE CONCAT('%', ?2, '%')
    @Query(value = "SELECT * FROM tb_hospital WHERE name LIKE CONCAT('%', ?1, '%') ",nativeQuery = true)
    List<hospitals> getByName(String name);
}
