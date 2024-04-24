package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.EmprestimoDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Models.Emprestimo;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Repositories.ChaveRepository;
import br.edu.ufsj.claviculario.Repositories.EmprestimoRepository;
import br.edu.ufsj.claviculario.Repositories.UsuarioRepository;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import br.edu.ufsj.claviculario.Utils.Timestamps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmprestimoService {
    private static final String MSG_SUCESSO = "Sucesso";
    
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ChaveRepository chaveRepository;
    
    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             UsuarioRepository usuarioRepository,
                             ChaveRepository chaveRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.chaveRepository = chaveRepository;
    }
    
    public ResponseEntity<ResponseDTO<Emprestimo>> emprestar(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = EmprestimoDTO.dtoToEmprestimo(emprestimoDTO, usuarioRepository, chaveRepository);
        this.emprestimoRepository.save(emprestimo);
        
        Chave chaveEmprestada = emprestimo.getChaveRetirada();
        Usuario usuarioQuePegou = emprestimo.getUsuarioQuePegou();
        
        chaveEmprestada.setUsuarioDePosse(usuarioQuePegou);
        chaveEmprestada.setDisponivel(false);
//        usuarioQuePegou.setChavesQuePorta((Set<Chave>) chaveEmprestada);
        
        chaveRepository.save(chaveEmprestada);
        usuarioRepository.save(usuarioQuePegou);
        
        return ResponseEntity.ok()
                .body(ResponseDTO.<Emprestimo>builder()
                        .message(MSG_SUCESSO)
                        .detail(emprestimo)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Emprestimo>> buscarPorID(Long id) {
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepository.findById(id);
        if (optionalEmprestimo.isPresent()) {
            Emprestimo emprestimo = optionalEmprestimo.get();
            return ResponseEntity.ok(
                    ResponseDTO.<Emprestimo>builder()
                            .message(MSG_SUCESSO)
                            .detail(emprestimo)
                            .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public ResponseEntity<ResponseDTO<List<Emprestimo>>> buscarTodas() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return ResponseEntity.ok(
                ResponseDTO.<List<Emprestimo>>builder()
                        .message(MSG_SUCESSO)
                        .detail(emprestimos)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Emprestimo>> devolver(Long id, Long usuarioQueRecebeuId) {
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepository.findById(id);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioQueRecebeuId);
        if (optionalEmprestimo.isPresent() && optionalUsuario.isPresent()) {
            Emprestimo devolucao = optionalEmprestimo.get();
            Usuario usuarioQueRecebeu = optionalUsuario.get();
            devolucao.setUsuarioQueRecebeuDeVolta(usuarioQueRecebeu);
            devolucao.setDevolucao(Timestamps.obterMomentoAtual());
            this.emprestimoRepository.save(devolucao);
            return ResponseEntity.ok(
                    ResponseDTO.<Emprestimo>builder()
                            .message(MSG_SUCESSO)
                            .detail(devolucao)
                            .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}