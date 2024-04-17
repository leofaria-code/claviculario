package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Enums.Blocos;
import br.edu.ufsj.claviculario.Models.Chave;

public record ChaveDTO(Blocos bloco, int sala, char complemento) {
    
    public static Chave dtoToChave(ChaveDTO chaveDTO) {
        return Chave.builder()
                .bloco(chaveDTO.bloco())
                .sala(chaveDTO.sala())
                .complemento(chaveDTO.complemento())
                .name(chaveDTO.bloco() + "-" + chaveDTO.sala() + chaveDTO.complemento())
                .build();
    }

}
