package com.backend.BackendSpringBootCreditpro.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Services.AdministratorService;



@RestController  //Controlador de servicios Rest
@RequestMapping(path = "Administrator")  //Ruta http para el controlador
public class AdministratorController {

    @Autowired
    AdministratorService _AdministratorService;

     @PostMapping(path = "saveAdmin")
    public ResponseEntity<Response> saveAdministrator(@RequestBody AdministratorModel admin) {
        
        if (admin != null) {
            // Lógica para verificar la validez de los datos antes de llamar al servicio
            Response adminResponse = this._AdministratorService.SaveAdministrator(admin);
            return new ResponseEntity<>(adminResponse, HttpStatus.OK);
        } else {
            // Si los datos no son válidos, devuelve una respuesta con código de estado 400 Bad Request
            return new ResponseEntity<>(new Response("Ingrese la información de forma correcta"),
                                         HttpStatus.BAD_REQUEST);
        }
    }


   @GetMapping(path="Prueba")
   public ResponseEntity<Response> Prueba(){
    return new ResponseEntity<>(new Response("Si sirve"), HttpStatus.OK);
   }
    
}
