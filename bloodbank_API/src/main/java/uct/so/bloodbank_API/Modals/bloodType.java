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
@Table(name = "tb_bloodType")

public class bloodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "bloodName")
    private String bloodName;
    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "bloodType",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<donors> donors;

    @OneToMany(mappedBy = "bloodType",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<recipients> recipients;


}
