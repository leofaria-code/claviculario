package br.edu.ufsj.claviculario.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;


@Entity
//@Table(name = "tb_chave")
@Getter @Setter @Builder
@NoArgsConstructor(force = true) @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chave {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private char bloco;
    private int andar;
    private int sala;
    private char complemento;
    
    private String name = String.valueOf(getBloco()) + getSala() + getComplemento();
        
    private String nickname;
    
    //private List<User> usersResponsaveis;
    //private List<User> usersComAcesso;
}
