package com.backend.BackendSpringBootCreditpro.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Repositories.AdministratorRepository;

/**
 * @Notacion Service Beans
 */
@Service
public class AdministratorService {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    AdministratorRepository _AdministratorRepository;

    /**
     * @param Admin
     * @return Objeto Response (Meensaje)
     */
    public Response SaveAdministrator(AdministratorModel Admin) {

        if (Admin != null) {

            // Validacion de que no exista un admin con el mismo user ingresado
            if (this.GetAdministrator(Admin.getUser()) == null) {

                // Metodo Save de JpaRepository para realizar un registro en la base de datos
                // que retorna un JSON del objeto registrado
                AdministratorModel AdminAux = this._AdministratorRepository.save(Admin);

                // El metodo save regresa el objeto registrado en la bd, si no es null ha sido
                // registrado con exito
                if (AdminAux != null) {
                    return new Response("Administrador Registrado Exitosamente");
                } else {
                    return new Response("Error Al registrar Administrador");
                }
            } 
            // En caso que si exista un admin con el mismo user que se quiere regustrar
            else {
                return new Response("Ya Existe un Administrados Asociado al usuario " + Admin.getUser());
            }

            // si el Objeto que llega por parametro es null
        } else {
            return new Response(
                    "No se puedo Registrar Administrado, por favor proporcione la Inofrmacion correctamente");
        }
    }

    /**
     * @Descripcion Metodo para buscar por un user un admin si es que existe.
     * @param user
     * @return Informacion del Abministrador con ese Email.
     */
    public AdministratorModel GetAdministrator(String user) {

        // Metodo para validar si la cadena tiene caracteres
        if (!user.isEmpty()) {

            // Tipo Optional porque puede o no existir un admin con ese user
            Optional<AdministratorModel> Administrator = this._AdministratorRepository.findByUser(user);

            // Verificar sy hay algo presente en el objeto opcional
            if (Administrator.isPresent()) {

                return Administrator.get();

            } else {

                return null;
            }
        } else {
            return null;
        }
    }

}
