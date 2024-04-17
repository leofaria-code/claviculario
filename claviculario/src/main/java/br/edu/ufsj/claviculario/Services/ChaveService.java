package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.ChaveDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Repositories.ChaveRepository;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Chave chave = ChaveDTO.dtoToChave(chaveDTO);
        chave.setDisponivel(true);
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
    
    //R - Read All
    public ResponseEntity<ResponseDTO<List<Chave>>> listarTodas () {
        List<Chave> chaves = this.chaveRepository.findAll();
        return ResponseEntity.ok()
                .body(ResponseDTO.<List<Chave>>builder()
                        .message(MSG_SUCESSO)
                        .detail(chaves)
                        .build());
    }
    
    
}
