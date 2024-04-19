package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.UsuarioDTO;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Services.UsuarioService;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @GetMapping("/{nome}")   //R - READ NAME
    public ResponseEntity<ResponseDTO<Usuario>> listarUsuarioPeloNome(@PathVariable("nome") String nome) {
        return this.usuarioService.listarPeloNome (nome);
    }
    
    @GetMapping("/matricula/{matricula}")   //R - READ MATRICULA
    public ResponseEntity<ResponseDTO<Usuario>> listarUsuarioPelaMatricula(@PathVariable("matricula") String matricula) {
        return this.usuarioService.listarPelaMatricula (matricula);
    }
    
    @GetMapping("/")   //R - READ ALL
    public ResponseEntity<ResponseDTO<List<Usuario>>> listarTodosUsuarios () {
        return this.usuarioService.listarTodos ();
    }
    
    @PutMapping("/")   //U - UPDATE
    public ResponseEntity<ResponseDTO<Usuario>> atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.atualizar(usuarioDTO);
    }
    
    @PutMapping("/{matricula}")   //D - DELETE
    public ResponseEntity<ResponseDTO<Usuario>> deletar(@PathVariable("matricula") String matricula) {
        return this.usuarioService.deletar(matricula);
    }
}
