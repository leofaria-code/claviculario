package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.ChaveDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Repositories.ChaveRepository;
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
    private final Validator validator;
    
    @Autowired
    public ChaveService(ChaveRepository chaveRepository, Validator validator) {
        this.chaveRepository = chaveRepository;
        this.validator = validator;
    }
    
    public ResponseEntity<ResponseDTO<Chave>> cadastrar(ChaveDTO chaveDTO) {
        Set<ConstraintViolation<ChaveDTO>> violations = validator.validate(chaveDTO);
        if (!violations.isEmpty()) {
            // Lógica para lidar com violações de validação
            return ResponseEntity.badRequest().build();
        }
        Chave chave = ChaveDTO.dtoToChave(chaveDTO);
        chave.setDisponivel(true);
        this.chaveRepository.save(chave);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Chave>builder()
                        .message(MSG_SUCESSO)
                        .detail(chave)
                        .build());
    }

    
    public ResponseEntity<ResponseDTO<Optional<Chave>>> buscarPorID (Long id) {
        Optional<Chave> chave = this.chaveRepository.findById(id);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Optional<Chave>>builder()
                        .message(MSG_SUCESSO)
                        .detail(chave)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Optional<Chave>>> listarPeloNome (String nome) {
        Optional<Chave> chave = Optional.ofNullable(this.chaveRepository.findChaveByNome(nome));
        return ResponseEntity.ok()
                .body(ResponseDTO.<Optional<Chave>>builder()
                        .message(MSG_SUCESSO)
                        .detail(chave)
                        .build());
    }
    
    private static final String MSG_SUCESSO = "Sucesso";
    
    public ResponseEntity<ResponseDTO<List<Chave>>> listarTodas () {
        List<Chave> chaves = this.chaveRepository.findAll();
        return ResponseEntity.ok()
                .body(ResponseDTO.<List<Chave>>builder()
                        .message(MSG_SUCESSO)
                        .detail(chaves)
                        .build());
    }
    
}
