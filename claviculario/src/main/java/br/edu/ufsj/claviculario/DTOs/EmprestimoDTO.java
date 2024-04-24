package br.edu.ufsj.claviculario.DTOs;

import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Models.Emprestimo;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Repositories.ChaveRepository;
import br.edu.ufsj.claviculario.Repositories.UsuarioRepository;
import br.edu.ufsj.claviculario.Utils.Timestamps;

import java.util.Optional;

public record EmprestimoDTO(Long usuarioQueEmprestouId, Long chaveId, Long usuarioQuePegouId) {
    
    public static Emprestimo dtoToEmprestimo(EmprestimoDTO emprestimoDTO, UsuarioRepository usuarioRepository, ChaveRepository chaveRepository) {
        if (emprestimoDTO.usuarioQueEmprestouId() == null || emprestimoDTO.chaveId() == null || emprestimoDTO.usuarioQuePegouId() == null) {
            throw new IllegalArgumentException("Todos os IDs devem ser fornecidos no DTO.");
        }
        
        Optional<Usuario> usuarioQueEmprestouOpt = usuarioRepository.findById(emprestimoDTO.usuarioQueEmprestouId());
        Optional<Chave> chaveOpt = chaveRepository.findById(emprestimoDTO.chaveId());
        Optional<Usuario> usuarioQuePegouOpt = usuarioRepository.findById(emprestimoDTO.usuarioQuePegouId());
        
        if (usuarioQueEmprestouOpt.isPresent() && chaveOpt.isPresent() && usuarioQuePegouOpt.isPresent()) {
            Usuario usuarioQueEmprestou = usuarioQueEmprestouOpt.get();
            Chave chave = chaveOpt.get();
            Usuario usuarioQuePegou = usuarioQuePegouOpt.get();
            
            return Emprestimo.builder()
                    .retirada(Timestamps.obterMomentoAtual())
                    .usuarioQueEmprestou(usuarioQueEmprestou)
                    .chaveRetirada(chave)
                    .usuarioQuePegou(usuarioQuePegou)
                    .build();
        } else {
            // Lógica para lidar com IDs inválidos
            throw new IllegalArgumentException("IDs inválidos fornecidos no DTO");
        }
    }
}