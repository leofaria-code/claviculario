package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Models.Chave;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter @Setter
@NoArgsConstructor(force = true) @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ChaveDTO {
    private char bloco;
    private int andar;
    private int sala;
    private char complemento;
    private String nickname;
    
    public static Chave dtoToChave(ChaveDTO chaveDTO) {
        return Chave.builder()
                .bloco(chaveDTO.getBloco())
                .andar(chaveDTO.getAndar())
                .sala(chaveDTO.getSala())
                .complemento(chaveDTO.getComplemento())
                .nickname(chaveDTO.getNickname())
                .build();
        
    }
}
