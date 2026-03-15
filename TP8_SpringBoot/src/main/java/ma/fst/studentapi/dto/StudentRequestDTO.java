package ma.fst.studentapi.dto;

import jakarta.validation.constraints.*;

public record StudentRequestDTO(

        @NotBlank(message = "Le prénom est obligatoire")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        String lastName,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Format d'email invalide")
        String email,

        @NotBlank(message = "La filière est obligatoire")
        String major,

        @NotNull(message = "L'âge est obligatoire")
        @Min(value = 17, message = "L'âge minimal est 17")
        @Max(value = 100, message = "L'âge maximal est 100")
        Integer age
) {
}



//@NotBlank vérifie qu’une chaîne n’est ni vide ni composée uniquement d’espaces.
//@Email vérifie le format de l’adresse email.
//@NotNull garantit que l’âge est fourni.
//@Min et @Max imposent une plage valide pour l’âge.