package br.edu.ufsj.claviculario.Services;

import br.edu.ufsj.claviculario.DTOs.UserDTO;
import br.edu.ufsj.claviculario.Models.User;
import br.edu.ufsj.claviculario.Repositories.UserRepository;
import br.edu.ufsj.claviculario.util.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    
    private static final String MSG_SUCESSO = "Sucesso";
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    //C - CREATE
    public ResponseEntity<ResponseDTO<UserDTO>> cadastrar(UserDTO userDTO) {
        User user =UserDTO.dtoToUser(userDTO);
        this.userRepository.save(user);
        
        return ResponseEntity.ok()
                .body(ResponseDTO
                        .<UserDTO>builder()
                        .message(MSG_SUCESSO)
                        .detail(userDTO)
                        .build());
    }
    
    //R - Read
    public ResponseEntity<ResponseDTO<User>> listarPeloNome (String name) {
        User user = this.userRepository.findUserByName(name);
        return ResponseEntity.ok()
                .body(ResponseDTO.<User>builder()
                        .message(MSG_SUCESSO)
                        .detail(user)
                        .build());
    }
    
    public ResponseEntity<ResponseDTO<User>> listarPelaMatricula (String matricula) {
        User user = this.userRepository.findUserByMatricula(matricula);
        return ResponseEntity.ok()
                .body(ResponseDTO.<User>builder()
                        .message(MSG_SUCESSO)
                        .detail(user)
                        .build());
    }
    
}
