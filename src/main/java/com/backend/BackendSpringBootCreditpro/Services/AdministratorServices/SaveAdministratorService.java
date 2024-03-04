package com.backend.BackendSpringBootCreditpro.Services.AdministratorServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Repositories.AdministratorRepository;

//Notacion service
@Service
public class SaveAdministratorService {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    AdministratorRepository _AdministratorRepository;

    @Autowired
    QueriesAdministratorService _QueriesAdministratorService;

    // #region Registrar Administrador

    /**
     * GUARDAR UN ADMINISTRADOR
     * se valida que no exista otro con el mismo id
     * se valida que no exista otro con el mismo user
     * 
     * @param Admin
     * @return Objeto Response (Meensaje)
     */
    public Response SaveAdministrator(AdministratorModel Admin) {

        // Validacion que todas las propiedades del objeto sean diligenciadas
        if (Admin.getIdentificacion() != null && Admin.getIdentificacion() > 0 && !Admin.getName().isEmpty()
                && !Admin.getLastName().isEmpty()
                && !Admin.getUser().isEmpty() && !Admin.getPassword().isEmpty()) {

            // Validacion de que no exista un administador con el mismo id que se intenta
            // registrar, por medio del servicio QueriesAdministrator
            if (this._QueriesAdministratorService.GetAdministratorIdentificacion(Admin.getIdentificacion()) == null) {

                // Validacion de que no exista un admin con el mismo user ingresado por medio
                // del servicio QueriesAdministrator
                if (this._QueriesAdministratorService.GetAdministrator(Admin.getUser()) == null) {

                    // Metodo Save de JpaRepository para realizar un registro en la base de datos
                    // que retorna un JSON del objeto registrado
                    AdministratorModel AdminAux = this._AdministratorRepository.save(Admin);

                    // El metodo save regresa el objeto registrado en la bd, si no es null ha sido
                    // registrado con exito
                    if (AdminAux != null) {
                        return new Response("Administrador Registrado Exitosamente");
                    } else {
                        return new Response("Algo ha salido mal, error Al registrar Administrador, Intnete nuevamente");
                    }
                }
                // En caso que si exista un admin con el mismo user que se quiere regustrar
                else {
                    return new Response("Ya Existe un Administrador Asociado al usuario " + Admin.getUser());
                }

            } else {
                return new Response(
                        "Ya Existe un Administrador Asociado a la identificación:  " + Admin.getIdentificacion());
            }

            // si el Objeto que llega por parametro es null
        } else {
            return new Response(
                    "No se pudo Registrar el Administrador, por favor proporcione la Inofrmacion correctamente");
        }
    }

    // #endregion

}
