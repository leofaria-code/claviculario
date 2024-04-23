package br.edu.ufsj.claviculario.Models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Emprestimo {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Timestamp retirada;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_que_emprestou_id")
    private Usuario usuarioQueEmprestou;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chave_retirada_id")
    private Chave chaveRetirada;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_que_pegou_id")
    private Usuario usuarioQuePegou;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_que_recebeu_de_volta_id")
    private Usuario usuarioQueRecebeuDeVolta;
    
    private Timestamp devolucao;
    
}