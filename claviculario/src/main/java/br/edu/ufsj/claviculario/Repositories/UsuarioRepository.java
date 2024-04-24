package br.edu.ufsj.claviculario.Repositories;

import br.edu.ufsj.claviculario.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findUsuarioByCpf(String cpf);
    Usuario findUsuarioByMatricula(String matricula);
    Usuario findUsuarioByNome(String nome);
}
