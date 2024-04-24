package br.edu.ufsj.claviculario.Models;

import br.edu.ufsj.claviculario.Enums.UsuariosPapeis;
import br.edu.ufsj.claviculario.Enums.UsuariosTipos;
import br.edu.ufsj.claviculario.Utils.Timestamps;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private UsuariosPapeis papel;
    
    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private UsuariosTipos tipo;
    
    @Column(unique = true)
    private String matricula;
    
    @Column(unique = true, nullable = false, updatable = false, columnDefinition = "VARCHAR(11)")
    @Setter(AccessLevel.NONE)
    private String cpf;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(unique = true, nullable = false)
    private String phone;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default @Setter(AccessLevel.NONE)
    private Timestamp dataCadastro = Timestamps.obterMomentoAtual();

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dataAtualizacao;
    
    @Builder.Default
    private boolean ativo = true;
    
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "usuario_chaves_autorizadas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "chave_id")
    )
    @Builder.Default
    private Set<Chave> chavesQueTemAutorizacaoDeRetirar = new HashSet<>();
    
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "usuario_chaves_gerenciadas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "chave_id")
    )
    @Builder.Default
    private Set<Chave> chavesQueGerencia = new HashSet<>();
    
    @OneToMany(mappedBy = "usuarioDePosse")
    @Builder.Default
    @JsonBackReference
    private Set<Chave> chavesQuePorta = new HashSet<>();
    
    @OneToMany(mappedBy = "usuarioQuePegou")
    @Builder.Default
    @JsonBackReference
    private List<Emprestimo> emprestimos = new ArrayList<>();
    
}
