package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Enums.Blocos;
import br.edu.ufsj.claviculario.Models.Chave;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ChaveDTO(@NotNull Blocos bloco, @Min(1) @Max(999) int sala, char complemento) {
    
    public static Chave dtoToChave(ChaveDTO chaveDTO) {
        return Chave.builder()
                .bloco(chaveDTO.bloco)
                .sala(chaveDTO.sala)
                .complemento(chaveDTO.complemento)
                .nome(chaveDTO.bloco + "-" + chaveDTO.sala + chaveDTO.complemento)
                .build();
    }

}
