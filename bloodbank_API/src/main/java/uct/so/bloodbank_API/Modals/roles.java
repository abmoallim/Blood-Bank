package uct.so.bloodbank_API.Modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@Table(name = "tb_roles")
public class roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "role_name")
    private String role_name;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<users> users;



}
