package br.edu.ufsj.claviculario.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collection;

@Entity
@Table (name = "chaves")
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
    
    @Setter
    @Getter
    @ManyToMany(mappedBy = "keys")
    private Collection<Usuario> usuarios;
    
}
