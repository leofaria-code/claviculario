package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Models.Usuario;

public record UsuarioDTO(String matricula, String nome, String phone) {
    
    public static Usuario dtoToUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .matricula(usuarioDTO.matricula())
                .nome(usuarioDTO.nome())
                .phone(usuarioDTO.phone())
                .build();
    }
}

