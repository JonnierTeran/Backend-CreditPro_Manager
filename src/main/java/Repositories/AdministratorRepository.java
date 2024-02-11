package Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;





//Decordaror para  indicar que este sera el repositorio
@Repository
public interface  AdministratorRepository  extends JpaRepository<AdministratorModel, Integer> {

    /*
     * Registrar un Admin
     */


     /**
      * Actualizar Un Admin
      */

}
