package uct.so.bloodbank_API.Modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_donors")
@CrossOrigin
public class donors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String phone;
    private Date brithDate;
    private String address;
    private String Weight;

    @ManyToOne(optional = false)
    @JoinColumn(name="blood_id", referencedColumnName = "id")
    bloodType bloodType;
    @ManyToOne(optional = false)
    @JoinColumn(name="state_id", referencedColumnName = "id")
    states state;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private users user;

    @OneToMany(mappedBy = "donor",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<donations> donationsList;

    @OneToMany(mappedBy = "donor",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<testResults> testResults;



}
