package br.edu.ufsj.claviculario.Models;

import br.edu.ufsj.claviculario.Enums.UsuariosTipos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.NumberFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private UsuariosTipos tipo;
    
    @Column(unique = true, nullable = false) @NonNull
    @NumberFormat(pattern = "2024")
    private String matricula;
    
    @Column(unique = true, nullable = false) @NonNull
    private String nome;
    
    @Column(unique = true, nullable = false) @NonNull
    @NumberFormat(pattern = "9876")
    private String phone;
    
    private String email;
    
    private Timestamp dataCadastro;

    private Timestamp dataAtualizacao;
    
    private boolean ativo;
    
    @ManyToMany
    @JoinTable(
            name = "usuario_chaves_autorizadas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "chave_id")
    )
    private Set<Chave> chavesAutorizadas = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
            name = "usuario_chaves_gerenciadas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "chave_id")
    )
    private Set<Chave> chavesGerenciadas = new HashSet<>();
    
}
