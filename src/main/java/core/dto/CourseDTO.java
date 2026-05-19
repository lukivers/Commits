package core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 200)
    private String title;

    private String description;
    private int durationHours;
    private String level; // BEGINNER, INTERMEDIATE, ADVANCED
    private Long instructorId;
    private Boolean published;
}