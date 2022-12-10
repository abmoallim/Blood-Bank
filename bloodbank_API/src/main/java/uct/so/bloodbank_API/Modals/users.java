package uct.so.bloodbank_API.Modals;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_users")
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
//    @JsonIgnore
    private roles role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    @JsonIgnore
    private donors donor;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private recipients recipient;


}
