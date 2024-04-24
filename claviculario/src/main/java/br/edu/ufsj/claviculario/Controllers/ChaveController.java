package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.ChaveDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Models.Usuario;
import br.edu.ufsj.claviculario.Services.ChaveService;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //CRUD
@RequestMapping("/chave")
public class ChaveController {
    
    private final ChaveService chaveService;
    
    @Autowired
    public ChaveController(ChaveService chaveService) {
        this.chaveService = chaveService;
    }
    
    @PostMapping("/")
    public ResponseEntity<ResponseDTO<Chave>> cadastrar(@RequestBody ChaveDTO chaveDTO) {
        return this.chaveService.cadastrar(chaveDTO);
    }
    
    @GetMapping("/id")
    public ResponseEntity<ResponseDTO<Chave>> listarChavePeloId (@RequestParam Long id) {
        return this.chaveService.buscarPorID (id);
    }
    
    @GetMapping("/nome")
    public ResponseEntity<ResponseDTO<Chave>> listarChavePeloNome (@RequestParam String name) {
        return this.chaveService.listarPeloNome (name);
    }
    
    @GetMapping("/")
    public ResponseEntity<ResponseDTO<List<Chave>>> listarTodasChaves () {
        return this.chaveService.listarTodas ();
    }
    
    @PutMapping("/delete")
    public ResponseEntity<ResponseDTO<Chave>> deletarChave(@RequestParam Long id) {
        return this.chaveService.deletarChave (id);
    }
    
    @PutMapping("/gestao")
    public ResponseEntity<ResponseDTO<List<Usuario>>> atribuirGestor(@RequestParam Long idChave, @RequestParam Long idUsuario) {
        return this.chaveService.atribuirUsuarioGestor (idChave, idUsuario);
    }
}
