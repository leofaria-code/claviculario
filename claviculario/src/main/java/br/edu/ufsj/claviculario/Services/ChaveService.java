package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.ChaveDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Repositories.ChaveRepository;
import br.edu.ufsj.claviculario.util.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChaveService {
    
    private static final String MSG_SUCESSO = "Sucesso";
    
    private final ChaveRepository chaveRepository;
    
    public ChaveService(ChaveRepository chaveRepository) {
        this.chaveRepository = chaveRepository;
    }
    
    //C - CREATE
    public ResponseEntity<ResponseDTO<ChaveDTO>> cadastrar(ChaveDTO chaveDTO) {
        Chave chave =ChaveDTO.dtoToChave(chaveDTO);
        this.chaveRepository.save(chave);
        
        return ResponseEntity.ok()
                .body(ResponseDTO
                        .<ChaveDTO>builder()
                        .message(MSG_SUCESSO)
                        .detail(chaveDTO)
                        .build());
    }
    
    //R - Read
    public ResponseEntity<ResponseDTO<Chave>> listarPeloNome (String name) {
        Chave chave = this.chaveRepository.findChaveByName(name);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Chave>builder()
                        .message(MSG_SUCESSO)
                        .detail(chave)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<Chave>> listarPeloApelido (String nickname) {
        Chave chave = this.chaveRepository.findChaveByNickname(nickname);
        return ResponseEntity.ok()
                .body(ResponseDTO.<Chave>builder()
                        .message(MSG_SUCESSO)
                        .detail(chave)
                        .build());
    }
    
}
