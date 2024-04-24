package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.ChaveDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Repositories.ChaveRepository;
import br.edu.ufsj.claviculario.Repositories.UsuarioRepository;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ChaveService {
    private final ChaveRepository chaveRepository;
    private final UsuarioRepository usuarioRepository;
    private final Validator validator;
    
    private static final String MSG_SUCESSO = "Sucesso";
    
    @Autowired
    public ChaveService(ChaveRepository chaveRepository, UsuarioRepository usuarioRepository, Validator validator) {
        this.chaveRepository = chaveRepository;
        this.usuarioRepository = usuarioRepository;
        this.validator = validator;
    }
    
    public ResponseEntity<ResponseDTO<Chave>> cadastrar(ChaveDTO chaveDTO) {
        Set<ConstraintViolation<ChaveDTO>> violations = validator.validate(chaveDTO);
        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Chave chave = ChaveDTO.dtoToChave(chaveDTO);
        this.chaveRepository.save(chave);
        return getResponse(chave);
    }
    
    public ResponseEntity<ResponseDTO<Chave>> buscarPorID (Long id) {
        Optional<Chave> chaveOptional = this.chaveRepository.findById(id);
        return getResponseEntity(chaveOptional);
    }
    
    public ResponseEntity<ResponseDTO<Chave>> listarPeloNome (String nome) {
        Optional<Chave> chaveOptional = Optional.ofNullable(this.chaveRepository.findChaveByNome(nome));
        return getResponseEntity(chaveOptional);
    }
    
    public ResponseEntity<ResponseDTO<List<Chave>>> listarTodas () {
        List<Chave> chaves = this.chaveRepository.findAll();
        return ResponseEntity.ok()
                .body(ResponseDTO.<List<Chave>>builder()
                        .message(MSG_SUCESSO)
                        .detail(chaves)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Chave>> deletarChave(Long id) {
        Optional<Chave> chaveOptional = this.chaveRepository.findById(id);
        if (chaveOptional.isPresent()) {
            Chave chave = chaveOptional.get();
            chave.setDisponivel(false);
            this.chaveRepository.save(chave);
            return getResponse(chave);
        }
        return ResponseEntity.badRequest().build();
    }
    
    private static ResponseEntity<ResponseDTO<Chave>> getResponseEntity(Optional<Chave> chaveOptional) {
        if (chaveOptional.isPresent()) {
            Chave chave = chaveOptional.get();
            return getResponse(chave);
        }
        return ResponseEntity.badRequest().build();
    }
    
    private static ResponseEntity<ResponseDTO<Chave>> getResponse(Chave chave) {
        return ResponseEntity.ok()
                .body(ResponseDTO.<Chave>builder()
                        .message(MSG_SUCESSO)
                        .detail(chave)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<List<Usuario>>> atribuirUsuarioGestor(Long idChave, Long idUsuario) {
        Optional<Chave> chaveOptional = this.chaveRepository.findById(idChave);
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(idUsuario);
        if (chaveOptional.isPresent() && usuarioOptional.isPresent()) {
            Chave chave = chaveOptional.get();
            Usuario usuario = usuarioOptional.get();
            chave.getUsuariosGestores().add(usuario);
            usuario.getChavesQueGerencia().add(chave);
            chaveRepository.save(chave);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok()
                    .body(ResponseDTO.<List<Usuario>>builder()
                            .message(MSG_SUCESSO)
                            .detail(chave.getUsuariosGestores())
                            .build());
        } else {
            return ResponseEntity.badRequest().build();
        }
        
    }
}
