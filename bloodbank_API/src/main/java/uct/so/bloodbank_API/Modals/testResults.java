package uct.so.bloodbank_API.Modals;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_test_results")
public class testResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="donor_id", referencedColumnName = "id")
    donors donors;

    @ManyToOne(optional = false)
    @JoinColumn(name="hospital_id", referencedColumnName = "id")
    hospitals hospital;

    private Boolean isHealthy;
    private String description;
    private Date date;
}
