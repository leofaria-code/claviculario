package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.UserDTO;
import br.edu.ufsj.claviculario.Models.User;
import br.edu.ufsj.claviculario.Services.UserService;
import br.edu.ufsj.claviculario.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //CRUD
@RequestMapping("/usuario")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/")   //C - CREATE
    public ResponseEntity<ResponseDTO<UserDTO>> cadastrar(@RequestBody UserDTO userDTO) {
        return this.userService.cadastrar(userDTO);
    }
    
    @GetMapping("/{name}")   //R - READ NAME
    public ResponseEntity<ResponseDTO<User>> listarUserPeloNome (@PathVariable("name") String name) {
        return this.userService.listarPeloNome (name);
    }
    
    @GetMapping("/{matricula}")   //R - READ MATRICULA
    public ResponseEntity<ResponseDTO<User>> listarUserPelaMatricula (@PathVariable("matricula") String matricula) {
        return this.userService.listarPelaMatricula (matricula);
    }
}
