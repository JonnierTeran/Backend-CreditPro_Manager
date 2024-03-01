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


    //#region Endpoint para Registrar un Admin

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

    //#endregion

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


     //#region Enpoint para buscar un admin por su User

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


    //#endregion


    //#region Endpoint para buscar un admin por Id

    /**
     * Metodo de escucha para la ruta getAdmin/UserByID
     * 
     * @param user
     * @return Objeto AdmisnitratorModel Si existe en la bd
     */
    @GetMapping(path = "UserByID/{identificacion}")
    public ResponseEntity<AdministratorModel> getAdministratorByIdController(@PathVariable Long identificacion) {

        // Validacion que la cadena no sea null
        if (identificacion > 0) {

            // Se valida que la respuesta no sea null y se retorna el objeto sino retoruna
            // null
            if (this._AdministratorService.GetAdministratorIdentificacion(identificacion) != null) {

                // Retorna 200 ststus OK si  se encuentra el administrador con ese id
                return ResponseEntity.ok()
                        .body(this._AdministratorService.GetAdministratorIdentificacion(identificacion));
            } else {

                return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encuentra el administrador
            }

        } else {
            return ResponseEntity.badRequest().body(null); // Retorna 400 bad request si el parametro user es invalido
        }

    }

    //#endregion


    //#region Endpoint para recuperar una cuenta por user

    /**
     * @description Endpoint para envio de credenciales por correo
     * @param User
     * @return Response con la informacion del envio de correo
     */
    @GetMapping(path = "RecoverAccount/{User}")
    public ResponseEntity<Response> RecoverAccountController(@PathVariable String User) {

        if (!User.isEmpty()) {
            // Retorna 200 status OK si  se envia correctamente el correo
            return ResponseEntity.ok()
            .body(this._AdministratorService.RestoreAccount(User));        
        }else{
            // Retorna 200 ststus OK si  se encuentra el administrador con ese id
            return ResponseEntity.badRequest()
            .body(new Response("Ingrese correctamente el Email"));
        }
    
    }
    //#endregion
}
