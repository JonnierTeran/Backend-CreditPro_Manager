package com.backend.BackendSpringBootCreditpro.Services;

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
            // Metodo Save de JpaRepository para realizar un registro en la base de datos
            // que retorna un JSON del objeto registrado
            AdministratorModel AdminAux = this._AdministratorRepository.save(Admin);

            if (AdminAux != null) {
                return new Response("Administrador Registrado Exitosamente");
            } else {
                return new Response("Error Al registrar Administrador");
            }
        } else {
            return new Response(
                    "No se puedo Registrar Administrado, por favor proporcione la Inofrmacion correctamente");
        }
    }

}
