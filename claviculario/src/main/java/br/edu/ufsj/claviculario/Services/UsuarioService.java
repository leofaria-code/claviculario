package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.UsuarioDTO;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Repositories.UsuarioRepository;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import br.edu.ufsj.claviculario.Utils.Timestamps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {
    
    private static final String MSG_SUCESSO = "Sucesso";
    
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    //C - CREATE
    public ResponseEntity<ResponseDTO<Usuario>> cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioDTO.dtoToUsuario(usuarioDTO);
        Timestamp cadastro = Timestamps.obterMomentoAtual();
        usuario.setDataCadastro(cadastro);
        usuario.setAtivo(true);
        this.usuarioRepository.save(usuario);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuario)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.ok(
                    ResponseDTO.<Usuario>builder()
                            .message(MSG_SUCESSO)
                            .detail(usuario)
                            .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //R - Read Nome
    public ResponseEntity<ResponseDTO<Usuario>> listarPeloNome (String nome) {
        Usuario usuario = this.usuarioRepository.findUsuarioByNome(nome);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuario)
                        .build());
    }
    
    //R - Read Matricula
    public ResponseEntity<ResponseDTO<Usuario>> listarPelaMatricula (String matricula) {
        Usuario usuario = this.usuarioRepository.findUsuarioByMatricula(matricula);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuario)
                        .build());
    }
    
    //R - Read All
    public ResponseEntity<ResponseDTO<List<Usuario>>> listarTodos() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return ResponseEntity.ok()
                .body(ResponseDTO.<List<Usuario>>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuarios)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> atualizar(UsuarioDTO usuarioDTO) {
        Usuario usuarioSelecionado = this.usuarioRepository.findUsuarioByMatricula(usuarioDTO.matricula());
        usuarioSelecionado.setTipo(usuarioDTO.tipo());
        usuarioSelecionado.setNome(usuarioDTO.nome());
        usuarioSelecionado.setPhone(usuarioDTO.phone());
        usuarioSelecionado.setEmail(usuarioDTO.email());
        usuarioSelecionado.setDataAtualizacao(Timestamps.obterMomentoAtual());
        usuarioSelecionado.setAtivo(true);
        this.usuarioRepository.save(usuarioSelecionado);
        
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuarioSelecionado)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> deletar(String matricula) {
        Usuario usuarioSelecionado = this.usuarioRepository.findUsuarioByMatricula(matricula);
        usuarioSelecionado.setAtivo(false);
        this.usuarioRepository.save(usuarioSelecionado);
        
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuarioSelecionado)
                        .build());
        
    }
}
