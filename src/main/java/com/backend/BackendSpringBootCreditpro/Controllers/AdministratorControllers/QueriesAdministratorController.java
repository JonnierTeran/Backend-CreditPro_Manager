package com.backend.BackendSpringBootCreditpro.Controllers.AdministratorControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Services.AdministratorServices.QueriesAdministratorService;

@RestController // Controlador de servicios Rest
@RequestMapping(path = "Administrator/QueriesAdministrator") // Ruta http para el controlador
public class QueriesAdministratorController {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    QueriesAdministratorService _QueriesAdministratorService;

    public QueriesAdministratorController() {
    }

    // #region Endpoint para buscar un admin por Id

    /**
     * Metodo de escucha para la ruta getAdmin/UserByID
     * 
     * @param user
     * @return Objeto AdmisnitratorModel Si existe en la bd
     */
    @GetMapping(path = "getAdminByID/{identificacion}")
    public ResponseEntity<AdministratorModel> getAdministratorByIdController(@PathVariable Long identificacion) {

        // Validacion que la cadena no sea null
        if (identificacion > 0) {

            AdministratorModel Admin = this._QueriesAdministratorService.GetAdministratorIdentificacion(identificacion);
            // Se valida que la respuesta no sea null y se retorna el objeto sino retoruna
            // null
            if (Admin != null) {

                // Retorna 200 ststus OK si se encuentra el administrador con ese id
                return ResponseEntity.ok().body(Admin);
            } else {

                return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encuentra el administrador
            }

        } else {
            return ResponseEntity.badRequest().body(null); // Retorna 400 bad request si el parametro user es invalido
        }

    }

    // #endregion

    
    // #region Enpoint para buscar un admin por su User

    /**
     * Metodo de escucha para la ruta getAdmin/USER
     * 
     * @param user
     * @return Objeto AdmisnitratorModel Si existe en la bd
     */
    @GetMapping(path = "getAdmin/{user}")
    public ResponseEntity<AdministratorModel> getAdministratorByUserController(@PathVariable String user) {

        // Validacion que la cadena no sea null
        if (!user.isEmpty()) {

            AdministratorModel Admin = this._QueriesAdministratorService.GetAdministrator(user);
            // Se valida que la respuesta no sea null y se retorna el objeto sino retoruna
            // null
            if (Admin != null) {

                return ResponseEntity.ok().body(Admin);
            } else {

                return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encuentra el administrador
            }

        } else {
            return ResponseEntity.badRequest().body(null); // Retorna 400 bad request si el parametro user es invalido
        }

    }
    // #endregion

}
