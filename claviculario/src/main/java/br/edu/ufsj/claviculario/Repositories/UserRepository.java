package br.edu.ufsj.claviculario.Repositories;

import br.edu.ufsj.claviculario.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName (String name);
    
    User findUserByMatricula (String matricula);

}
