package br.edu.ufsj.claviculario.Models;

import br.edu.ufsj.claviculario.Enums.Blocos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Collection;
import java.util.List;

@Entity
@Table (name = "chave")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Chave {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(1)")
    @Enumerated(EnumType.STRING)
    private Blocos bloco;
    
    @Column
    private int sala;
    
    @Column
    private char complemento;
    
    @Column
    private String nome;
    
    @Column
    private boolean disponivel;
    
    @Setter @Getter
    @JsonBackReference
    @ManyToMany(mappedBy = "chavesQueTemAutorizacaoDeRetirar")
    private Collection<Usuario> usuariosAutorizados;
    
    @Setter @Getter
    @JsonBackReference
    @ManyToMany(mappedBy = "chavesQueGerencia")
    private Collection<Usuario> usuariosGestores;
    
    @Setter @Getter
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioDePosse;
    
    @OneToMany(mappedBy = "chaveRetirada")
    @JsonBackReference
    private List<Emprestimo> emprestimos;
    
}
