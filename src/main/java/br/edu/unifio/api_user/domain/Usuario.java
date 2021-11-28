package br.edu.unifio.api_user.domain;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer USR_ID;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 2, max = 50, message = "O tamanho do campo nome deve ser entre 3 e 50")
    private String email;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 2, max = 50, message = "O tamanho do campo nome deve ser entre 3 e 50")
    private String senha;

}
