package core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String fullName;

    @Size(max = 1000)
    private String bio;
}