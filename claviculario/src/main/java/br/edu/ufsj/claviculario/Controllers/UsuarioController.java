package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.UsuarioDTO;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Services.UsuarioService;
import br.edu.ufsj.claviculario.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //CRUD
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping("/")   //C - CREATE
    public ResponseEntity<ResponseDTO<Usuario>> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.cadastrar(usuarioDTO);
    }
    
    @GetMapping("/nome/{name}")   //R - READ NAME
    public ResponseEntity<ResponseDTO<Usuario>> listarUserPeloNome (@PathVariable("name") String name) {
        return this.usuarioService.listarPeloNome (name);
    }
    
    @GetMapping("/matricula/{matricula}")   //R - READ MATRICULA
    public ResponseEntity<ResponseDTO<Usuario>> listarUserPelaMatricula (@PathVariable("matricula") String matricula) {
        return this.usuarioService.listarPelaMatricula (matricula);
    }
    
    @GetMapping("/usuarios")   //R - READ ALL
    public ResponseEntity<ResponseDTO<Usuario>> listarTodosUsuarios () {
        return this.usuarioService.listarTodos ();
    }
    
    @PutMapping("/update")   //U - UPDATE
    public ResponseEntity<ResponseDTO<Usuario>> atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.atualizar(usuarioDTO);
    }
}
