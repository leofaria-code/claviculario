package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.ChaveDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Services.ChaveService;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //CRUD
@RequestMapping("/chave")
public class ChaveController {
    
    private final ChaveService chaveService;
    
    public ChaveController(ChaveService chaveService) {
        this.chaveService = chaveService;
    }
    
    @PostMapping("/")   //C - CREATE
    public ResponseEntity<ResponseDTO<ChaveDTO>> cadastrar(@RequestBody ChaveDTO chaveDTO) {
        return this.chaveService.cadastrar(chaveDTO);
    }
    
    @GetMapping("/{nome}")   //R - READ NAME
    public ResponseEntity<ResponseDTO<Chave>> listarChavePeloNome (@PathVariable("nome") String name) {
        return this.chaveService.listarPeloNome (name);
    }
    
    @GetMapping("/")   //R - READ ALL
    public ResponseEntity<ResponseDTO<List<Chave>>> listarTodasChaves () {
        return this.chaveService.listarTodas ();
    }
    
}
