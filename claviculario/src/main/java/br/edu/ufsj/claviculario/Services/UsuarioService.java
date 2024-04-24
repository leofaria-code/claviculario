package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.UsuarioDTO;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Repositories.UsuarioRepository;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import br.edu.ufsj.claviculario.Utils.Timestamps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    
    public ResponseEntity<ResponseDTO<Usuario>> cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioDTO.dtoToUsuario(usuarioDTO);
        this.usuarioRepository.save(usuario);
        return getResponse(usuario);
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return getResponseEntity(usuarioOptional);
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> buscarPorCpf(String cpf) {
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioRepository.findUsuarioByCpf(cpf));
        return getResponseEntity(usuarioOptional);
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> buscarPelaMatricula(String matricula) {
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioRepository.findUsuarioByMatricula(matricula));
        return getResponseEntity(usuarioOptional);
    }

    public ResponseEntity<ResponseDTO<Usuario>> buscarPeloNome(String nome) {
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioRepository.findUsuarioByNome(nome));
        return getResponseEntity(usuarioOptional);
    }
    
    private static ResponseEntity<ResponseDTO<Usuario>> getResponseEntity(Optional<Usuario> usuarioOptional) {
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return getResponse(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    private static ResponseEntity<ResponseDTO<Usuario>> getResponse(Usuario usuario) {
        return ResponseEntity.ok()
                .body(ResponseDTO.<Usuario>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuario)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<List<Usuario>>> listarTodos() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return ResponseEntity.ok()
                .body(ResponseDTO.<List<Usuario>>builder()
                        .message(MSG_SUCESSO)
                        .detail(usuarios)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> deletar(Long id) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioDesativado = usuarioOptional.get();
            usuarioDesativado.setAtivo(false);
            usuarioDesativado.setDataAtualizacao(Timestamps.obterMomentoAtual());
            usuarioRepository.save(usuarioDesativado);
            return getResponse(usuarioDesativado);
        } else {
        return ResponseEntity.notFound().build();
    }
    
    }
    
    public ResponseEntity<ResponseDTO<Usuario>> atualizar(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioAtualizar = usuarioOptional.get();
            usuarioAtualizar.setTipo(usuarioDTO.tipo());
            usuarioAtualizar.setPapel(usuarioDTO.papel());
            usuarioAtualizar.setNome(usuarioDTO.nome());
            usuarioAtualizar.setMatricula(usuarioDTO.matricula());
            usuarioAtualizar.setEmail(usuarioDTO.email());
            usuarioAtualizar.setPhone(usuarioDTO.phone());
            usuarioAtualizar.setAtivo(true);
            usuarioAtualizar.setDataAtualizacao(Timestamps.obterMomentoAtual());
            Usuario usuarioAtualizado = usuarioRepository.save(usuarioAtualizar);
            return getResponse(usuarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}