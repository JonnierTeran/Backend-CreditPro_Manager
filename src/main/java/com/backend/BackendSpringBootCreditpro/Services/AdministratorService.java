package com.backend.BackendSpringBootCreditpro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Repositories.AdministratorRepository;

@Service
public class AdministratorService {

    @Autowired
    AdministratorRepository AdministratorRepository;

    // Registar un Usuario
    public Response SaveAdministrator(AdministratorModel Admin) {

        if (Admin != null) 
        {
            AdministratorModel AdminAux = this.AdministratorRepository.save(Admin);

            if (AdminAux != null) 
            {
                return new Response("Administrador Registrado Exitosamente");
            } 
            else 
            {
                return new Response("Error Al registrar Administrador");
            }
        }else 
        {
            return new Response("No se puedo Registrar Administrado, por favor proporcione la Inofrmacion correctamente");
        }
    }

}
