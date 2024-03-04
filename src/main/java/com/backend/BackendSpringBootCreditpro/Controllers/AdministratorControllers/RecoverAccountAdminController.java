package com.backend.BackendSpringBootCreditpro.Controllers.AdministratorControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Services.AdministratorServices.RecoverAccountAdminService;

@RestController // Controlador de servicios Rest
@RequestMapping(path = "Administrator/RecoverAccount") // Ruta http para el controlador
public class RecoverAccountAdminController {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    RecoverAccountAdminService _RecoverAccountAdminService;

    public RecoverAccountAdminController() {
    }

    // #region Endpoint para recuperar una cuenta por User

    /**
     * @description Endpoint para envio de credenciales por correo
     * @param User
     * @return Response con la informacion del envio de correo
     */
    @GetMapping(path = "RecoverAccount/{User}")
    public ResponseEntity<Response> RecoverAccountController(@PathVariable String User) {

        if (!User.isEmpty()) {
            // Retorna 200 status OK si se envia correctamente el correo
            return ResponseEntity.ok()
                    .body(this._RecoverAccountAdminService.RestoreAccount(User));
        } else {
            // Retorna 200 ststus OK si se encuentra el administrador con ese id
            return ResponseEntity.badRequest()
                    .body(new Response("Ingrese correctamente el Email"));
        }

    }
    // #endregion

}
