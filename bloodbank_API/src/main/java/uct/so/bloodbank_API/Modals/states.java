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
@Table(name = "tb_states")
@CrossOrigin
public class states {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String stateName;

    @OneToMany(mappedBy = "state",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<donors> donors;

    @OneToMany(mappedBy = "state",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<recipients> recipients;
}
