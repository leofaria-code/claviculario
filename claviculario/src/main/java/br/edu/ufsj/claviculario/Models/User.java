package br.edu.ufsj.claviculario.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_user")
@Getter @Setter @Builder
@NoArgsConstructor(force = true) @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String matricula;
    private String name;
    private String phone;
    
    //private final List<Chave> salasQueGerencia;
    //private final List<Chave> salasQuePodeAcessar;
    
}
