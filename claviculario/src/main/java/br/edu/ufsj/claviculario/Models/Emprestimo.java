package br.edu.ufsj.claviculario.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Entity
@Table
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Emprestimo {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Timestamp retirada;
    
    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_que_emprestou_id")
    @JsonIgnore
    private Usuario usuarioQueEmprestou;
    
    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chave_retirada_id")
    @JsonManagedReference
    private Chave chaveRetirada;
    

    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_que_pegou_id")
    @JsonManagedReference
    private Usuario usuarioQuePegou;
    
    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_que_recebeu_de_volta_id")
    @JsonIgnore
    private Usuario usuarioQueRecebeuDeVolta;
    
    private Timestamp devolucao;
    
}