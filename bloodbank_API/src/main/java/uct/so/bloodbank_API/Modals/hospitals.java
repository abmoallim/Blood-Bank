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
@Table(name = "tb_hospital")
@CrossOrigin
public class hospitals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String location;
    private String status;


    @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<donations> donationsList;

    @OneToMany(mappedBy = "hospital",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<records> recordsList;



}
