package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.ChaveDTO;
import br.edu.ufsj.claviculario.Models.Chave;
import br.edu.ufsj.claviculario.Services.ChaveService;
import br.edu.ufsj.claviculario.util.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/{name}")   //R - READ NAME
    public ResponseEntity<ResponseDTO<Chave>> listarChavePeloNome (@PathVariable("name") String name) {
        return this.chaveService.listarPeloNome (name);
    }
    
    @GetMapping("/{nickname}")   //R - READ NICKNAME
    public ResponseEntity<ResponseDTO<Chave>> listarChavePeloApelido (@PathVariable("nickname") String nickname) {
        return this.chaveService.listarPeloApelido (nickname);
    }
}
