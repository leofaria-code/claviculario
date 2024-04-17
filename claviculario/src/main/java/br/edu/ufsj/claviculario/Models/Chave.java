package br.edu.ufsj.claviculario.Models;

import br.edu.ufsj.claviculario.Enums.Blocos;
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
    
    @Column(columnDefinition = "VARCHAR(1)")
    @Enumerated(EnumType.STRING)
    private Blocos bloco;
    
    private int sala;
    
    private char complemento;
    
    private String name;
    
    private boolean disponivel;
    
    @Setter @Getter
    @ManyToMany(mappedBy = "chavesAutorizadas")
    private Collection<Usuario> usuariosAutorizados;
    
    @Setter @Getter
    @ManyToMany(mappedBy = "chavesGerenciadas")
    private Collection<Usuario> usuariosGestores;
    
}
