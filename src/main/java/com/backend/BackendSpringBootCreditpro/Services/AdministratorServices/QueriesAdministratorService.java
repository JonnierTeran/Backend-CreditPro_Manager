package com.backend.BackendSpringBootCreditpro.Services.AdministratorServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Repositories.AdministratorRepository;

/**
 * @Notacion Service Service
 */
@Service
public class QueriesAdministratorService {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    AdministratorRepository _AdministratorRepository;


    // #region Buscar un Administrador por Id

    /**
     * @Descripcion Metodo para buscar por identificacion un admin si es que existe.
     * @param Identificacion
     * @return Informacion del Abministrador con esa identificacion.
     */
    public AdministratorModel GetAdministratorIdentificacion(Long Identificacion) {
        // Metodo para validar si el paramertro tiene caracteres validos
        if (Identificacion > 0) {

            // Tipo Optional porque puede o no existir un admin con esa identificacion
            Optional<AdministratorModel> Administrator = this._AdministratorRepository
                    .findByIdentificacion(Identificacion);

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

    // #endregion


    // #region BuscarAdmin por User

    /**
     * @Descripcion Metodo para buscar por un user un admin si es que existe.
     * @param String user
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

    // #endregion

}
