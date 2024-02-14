package com.backend.BackendSpringBootCreditpro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Services.AdministratorService;

@RestController // Controlador de servicios Rest
@RequestMapping(path = "Administrator") // Ruta http para el controlador
public class AdministratorController {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    AdministratorService _AdministratorService;

    /**
     * REGISTRAR UN ADMINISTRADOR
     * 
     * @param admin
     * @return Object ResposeEntity con un Objeto Response (Meensaje) y el estado de
     *         la Peticion.
     */
    @PostMapping(path = "saveAdmin")
    public ResponseEntity<Response> saveAdministratorController(@RequestBody AdministratorModel admin) {

        if (admin != null) {
            // Lógica para verificar la validez de los datos antes de llamar al servicio
            Response adminResponse = this._AdministratorService.SaveAdministrator(admin);
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

    /*
     * @GetMapping(path = "getAdmin/{user}")
     * public ResponseEntity<?> getAdministratorController(@PathVariable String
     * user){
     * 
     * if (!user.isEmpty()) {
     * if (this._AdministratorService.GetAdministrator(user) != null) {
     * 
     * return
     * ResponseEntity.ok().body(this._AdministratorService.GetAdministrator(user));
     * } else{
     * 
     * return ResponseEntity.ok().body("El administrador Solicitado no existe");
     * }
     * // return new
     * ResponseEntity<>(this._AdministratorService.GetAdministrator(user),
     * HttpStatus.OK);
     * 
     * }else{
     * return ResponseEntity
     * .badRequest()
     * .body("Por favor, Ingrese correctamente la informacion solicitada");
     * }
     * 
     * }
     */

    /**
     * Metodo de escucha para la ruta getAdmin/USER
     * 
     * @param user
     * @return Objeto AdmisnitratorModel Si existe en la bd
     */
    @GetMapping(path = "getAdmin/{user}")
    public ResponseEntity<AdministratorModel> getAdministratorController(@PathVariable String user) {

        // Validacion que la cadena no sea null
        if (!user.isEmpty()) {

            // Se valida que la respuesta no sea null y se retorna el objeto sino retoruna
            // null
            if (this._AdministratorService.GetAdministrator(user) != null) {

                return ResponseEntity.ok().body(this._AdministratorService.GetAdministrator(user));
            } else {

                return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encuentra el administrador
            }

        } else {
            return ResponseEntity.badRequest().body(null); // Retorna 400 bad request si el parametro user es invalido
        }

    }

}
