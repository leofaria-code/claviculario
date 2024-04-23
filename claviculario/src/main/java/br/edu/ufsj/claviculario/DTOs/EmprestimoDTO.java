package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Models.Emprestimo;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Utils.Timestamps;

public record EmprestimoDTO(Usuario usuarioQueEmprestou, Chave chave, Usuario usuarioQuePegou) {
    
    public static Emprestimo dtoToEmprestimo(EmprestimoDTO emprestimoDTO) {
        return Emprestimo.builder()
                .retirada(Timestamps.obterMomentoAtual())
                .usuarioQueEmprestou(emprestimoDTO.usuarioQueEmprestou)
                .chaveRetirada(emprestimoDTO.chave)
                .usuarioQuePegou(emprestimoDTO.usuarioQuePegou)
                .build();
    }
}