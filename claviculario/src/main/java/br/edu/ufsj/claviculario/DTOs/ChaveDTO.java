package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Models.Chave;

public record ChaveDTO(char bloco, int andar, int sala, char complemento, String nickname) {
    
    public static Chave dtoToChave(ChaveDTO chaveDTO) {
        return Chave.builder()
                .bloco(chaveDTO.bloco())
                .andar(chaveDTO.andar())
                .sala(chaveDTO.sala())
                .complemento(chaveDTO.complemento())
                .nickname(chaveDTO.nickname())
                .build();
    }

}
