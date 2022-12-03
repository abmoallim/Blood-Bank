package uct.so.bloodbank_API.Modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private roles role;

    @OneToOne(mappedBy = "user")
    private donors donor;

    @OneToOne(mappedBy = "user")
    private recipients recipient;


}
