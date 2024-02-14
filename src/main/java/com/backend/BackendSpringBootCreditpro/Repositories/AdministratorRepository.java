package com.backend.BackendSpringBootCreditpro.Repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;





//Decordaror para  indicar que este sera el repositorio
@Repository
public interface  AdministratorRepository  extends JpaRepository<AdministratorModel, Long> {

    /**
     * @Descripcion nomenclatura de JPA para hacer consultas automaticas findBy filtrar por. 
     * @Abstract Es abstracto porque no contiene un cuerpo.
     * @param user
     * @return Consulta SQL con JPA que Obtiene el Administrador filtrado por su user
     */
    public abstract Optional<AdministratorModel> findByUser(String user);

}
