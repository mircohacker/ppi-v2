package hello.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne
    private Lecture lecture;

    @ManyToOne
    private User user;

    private LocalDateTime timestamp;
}
