package br.edu.ufsj.claviculario.Models;

import br.edu.ufsj.claviculario.Utils.Timestamps;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Entity
@Table
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gestao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chave_gerenciada_id")
    @JsonManagedReference
    private Chave chaveGerenciada;
    
    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_gestor_id")
    @JsonManagedReference
    private Usuario usuarioGestor;
    
    @Builder.Default
    private Timestamp dataRegistro = Timestamps.obterMomentoAtual();
}
