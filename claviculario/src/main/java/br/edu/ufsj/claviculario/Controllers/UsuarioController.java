package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.UsuarioDTO;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Services.UsuarioService;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping("/")
    public ResponseEntity<ResponseDTO<Usuario>> cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.cadastrar(usuarioDTO);
    }
    
    @GetMapping("/nome")
    public ResponseEntity<ResponseDTO<Usuario>> listarUsuarioPeloNome(@RequestParam String nome) {
        return this.usuarioService.buscarPeloNome(nome);
    }
    
    @GetMapping("/matricula")
    public ResponseEntity<ResponseDTO<Usuario>> listarUsuarioPelaMatricula(@RequestParam String matricula) {
        return this.usuarioService.buscarPelaMatricula(matricula);
    }
    
    @GetMapping("/id")
    public ResponseEntity<ResponseDTO<Usuario>> listarUsuarioPeloId(@RequestParam Long id) {
        return this.usuarioService.buscarPorId (id);
    }
    
    @GetMapping("/cpf")
    public ResponseEntity<ResponseDTO<Usuario>> listarUsuarioPeloCpf(@RequestParam String cpf) {
        return this.usuarioService.buscarPorCpf (cpf);
    }
    
    @GetMapping("/")
    public ResponseEntity<ResponseDTO<List<Usuario>>> listarTodosUsuarios () {
        return this.usuarioService.listarTodos ();
    }
    
    @PutMapping("/")
    public ResponseEntity<ResponseDTO<Usuario>> atualizar(@RequestParam Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.atualizar(id, usuarioDTO);
    }
    
    @PutMapping("/delete")
    public ResponseEntity<ResponseDTO<Usuario>> deletar(@RequestParam("id") Long id) {
        return this.usuarioService.deletar(id);
    }
}
