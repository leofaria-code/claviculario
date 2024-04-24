package br.edu.ufsj.claviculario.Controllers;

import br.edu.ufsj.claviculario.DTOs.EmprestimoDTO;
import br.edu.ufsj.claviculario.Models.Emprestimo;
import br.edu.ufsj.claviculario.Services.EmprestimoService;
import br.edu.ufsj.claviculario.Utils.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //CRUD
@RequestMapping("/emprestimo")
public class EmprestimoController {
    
    private final EmprestimoService emprestimoService;
    
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }
    
    @PostMapping("/")   //C - CREATE
    public ResponseEntity<ResponseDTO<Emprestimo>> cadastrarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        return this.emprestimoService.emprestar(emprestimoDTO);
    }
    
    @GetMapping("/")
    public ResponseEntity<ResponseDTO<Emprestimo>> listarEmprestimoPorId(@RequestParam Long id) {
        return this.emprestimoService.buscarPorID(id);
    }
    
    @GetMapping("/emprestimos")
    public ResponseEntity<ResponseDTO<List<Emprestimo>>> listarEmprestimos() {
        return this.emprestimoService.buscarTodas();
    }
    
    @PutMapping("/")
    public ResponseEntity<ResponseDTO<Emprestimo>> finalizarEmprestimo(@RequestParam Long id, Long usuarioQueRecebeuId) {
        return this.emprestimoService.devolver(id, usuarioQueRecebeuId);
    }
}
