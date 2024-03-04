package com.backend.BackendSpringBootCreditpro.Controllers.AdministratorControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Services.AdministratorServices.SaveAdministratorService;



@RestController // Controlador de servicios Rest
@RequestMapping(path = "Administrator/SaveAdministrator") // Ruta http para el controlador
public class SaveAdministratorController {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    SaveAdministratorService _SaveAdministratorService;

    public SaveAdministratorController() {
    }

    // #region Endpoint para Registrar un Admin

    /**
     * REGISTRAR UN ADMINISTRADOR
     * 
     * @param admin
     * @return Object ResposeEntity con un Objeto Response (Meensaje) y el estado de
     *         la Peticion.
     */
    @PostMapping(path = "saveAdmin")
    public ResponseEntity<Response> saveAdministratorController(@RequestBody AdministratorModel admin) {

        if (admin.getIdentificacion() != null && admin.getIdentificacion() > 0 && !admin.getName().isEmpty() && !admin.getLastName().isEmpty()
        && !admin.getUser().isEmpty() && !admin.getPassword().isEmpty()) {
            // Lógica para verificar la validez de los datos antes de llamar al servicio
            Response adminResponse = this._SaveAdministratorService.SaveAdministrator(admin);
            return new ResponseEntity<>(adminResponse, HttpStatus.OK); // Retorna el objeto con la respuesta y el estado
                                                                       // 200 que indica que todo ha salido bien
        } else {
            // Si los datos no son válidos, devuelve una respuesta con código de estado 400
            // Bad Request
            return new ResponseEntity<>(new Response("Ingrese la información de forma correcta"),
                    HttpStatus.BAD_REQUEST); // retorna la respuesta y estado 400 bad reques que indica que no se
                                             // ingreso bien el cuerpo de la peticion
        }
    }

    // #endregion
}
