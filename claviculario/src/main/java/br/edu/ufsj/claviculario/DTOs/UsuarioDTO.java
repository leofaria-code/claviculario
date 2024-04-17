package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Enums.UsuariosTipos;
import br.edu.ufsj.claviculario.Models.Usuario;


public record UsuarioDTO(UsuariosTipos tipo, String matricula, String nome, String phone, String email) {
    
    public static Usuario dtoToUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .tipo(usuarioDTO.tipo())
                .matricula(usuarioDTO.matricula())
                .nome(usuarioDTO.nome())
                .phone(usuarioDTO.phone())
                .email(usuarioDTO.email())
                .build();
    }
}

