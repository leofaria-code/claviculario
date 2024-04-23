package br.edu.ufsj.claviculario.Repositories;

import br.edu.ufsj.claviculario.Models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
