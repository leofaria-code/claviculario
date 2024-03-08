package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor(force = true) @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class UserDTO {
    private String matricula;
    private String name;
    private String phone;
    
    public static User dtoToUser(UserDTO userDTO) {
        return User.builder()
                .matricula(userDTO.getMatricula())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .build();
    }
}