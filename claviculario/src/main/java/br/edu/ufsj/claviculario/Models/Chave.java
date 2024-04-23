package br.edu.ufsj.claviculario.Models;

import br.edu.ufsj.claviculario.Enums.Blocos;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collection;

@Entity
@Table (name = "chaves")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Chave {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(1)")
    @Enumerated(EnumType.STRING)
    private Blocos bloco;
    
    private int sala;
    
    private char complemento;
    
    private String nome;
    
    private boolean disponivel;
    
    @Setter @Getter
    @ManyToMany(mappedBy = "chavesQueTemAutorizacaoDeRetirar")
    private Collection<Usuario> usuariosAutorizados;
    
    @Setter @Getter
    @ManyToMany(mappedBy = "chavesQueGerencia")
    private Collection<Usuario> usuariosGestores;
    
    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioDePosse;
    
}
