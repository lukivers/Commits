package core.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private String university;
    private String specialty;
    private int courseYear;
}