package br.edu.ufsj.claviculario.Models;

import br.edu.ufsj.claviculario.Enums.UsuariosPapeis;
import br.edu.ufsj.claviculario.Enums.UsuariosTipos;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private UsuariosPapeis papel;
    
    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private UsuariosTipos tipo;
    
    @Column(unique = true, nullable = false)
    private String matricula;
    
    @Column(unique = true, nullable = false, updatable = false, columnDefinition = "VARCHAR(11)")
    private String cpf;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(unique = true, nullable = false)
    private String phone;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dataAtualizacao;
    
    private boolean ativo;
    
    @ManyToMany
    @JoinTable(
            name = "usuario_chaves_autorizadas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "chave_id")
    )
    private Set<Chave> chavesQueTemAutorizacaoDeRetirar = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
            name = "usuario_chaves_gerenciadas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "chave_id")
    )
    private Set<Chave> chavesQueGerencia = new HashSet<>();
    
    @OneToMany(mappedBy = "usuarioDePosse")
    private Set<Chave> chavesQuePorta = new HashSet<>();
    
}
