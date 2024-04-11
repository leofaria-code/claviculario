package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(force = true) @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class UsuarioDTO {
    private String matricula;
    private String nome;
    private String phone;
    
    public static Usuario dtoToUser(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .matricula(usuarioDTO.getMatricula())
                .nome(usuarioDTO.getNome())
                .phone(usuarioDTO.getPhone())
                .build();
    }
}