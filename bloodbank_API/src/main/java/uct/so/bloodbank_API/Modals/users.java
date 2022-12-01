package uct.so.bloodbank_API.Modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@Table(name = "tb_users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name="role_id", referencedColumnName = "id")
    roles role;


}
