package uct.so.bloodbank_API.Modals;

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
@Table(name = "tb_recipients")

public class recipients {
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

    @OneToMany(mappedBy = "recipient",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<records> recordsList;


}
