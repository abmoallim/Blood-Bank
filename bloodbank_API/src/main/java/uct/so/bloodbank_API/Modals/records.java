package uct.so.bloodbank_API.Modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@Table(name = "tb_records")

public class records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="recipient_id", referencedColumnName = "id")
    recipients recipient;


    @ManyToOne(optional = false)
    @JoinColumn(name="hospital_id", referencedColumnName = "id")
    hospitals hospital;

    private Integer quantity;
    private Date date;
}
