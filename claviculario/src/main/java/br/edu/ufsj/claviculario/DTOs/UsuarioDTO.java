package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Enums.UsuariosPapeis;
import br.edu.ufsj.claviculario.Enums.UsuariosTipos;
import br.edu.ufsj.claviculario.Models.Usuario;


public record UsuarioDTO(UsuariosPapeis papel, UsuariosTipos tipo, String matricula, String cpf, String nome, String phone, String email) {
    
    public static Usuario dtoToUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .papel(usuarioDTO.papel)
                .tipo(usuarioDTO.tipo)
                .matricula(usuarioDTO.matricula)
                .cpf(usuarioDTO.cpf)
                .nome(usuarioDTO.nome)
                .phone(usuarioDTO.phone)
                .email(usuarioDTO.email)
                .build();
    }
}

