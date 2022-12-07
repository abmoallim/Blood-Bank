package uct.so.bloodbank_API.Modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_donors")

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
    @JsonIgnore
    private users user;



    @OneToMany(mappedBy = "donors",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<testResults> testResults;



}
