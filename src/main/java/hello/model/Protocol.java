package hello.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "protocols")
public class Protocol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private FileWrapper file;
    private LocalDateTime submission;
    private boolean reviewed;
    private boolean accepted;
    @ManyToOne() //TODO cascading?
    private User uploader;

    @ManyToOne
    private Lecture lecture;
}
