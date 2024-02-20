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
     * GUARDAR UN ADMINISTRADOR
     * se valida que no exista otro con el mismo id
     * se valida que no exista otro con el mismo user
     * 
     * @param Admin
     * @return Objeto Response (Meensaje)
     */
    public Response SaveAdministrator(AdministratorModel Admin) {

        //Validacion que todas las propiedades del objeto sean diligenciadas
        if (Admin.getIdentificacion() > 0 && !Admin.getName().isEmpty() && !Admin.getLastName().isEmpty()
                && !Admin.getUser().isEmpty() && !Admin.getPassword().isEmpty()) {

            // Validacion de que no exista un administador con el mismo id que se intenta
            // registrar
            if (this.GetAdministratorIdentificacion(Admin.getIdentificacion()) == null) {

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
                        return new Response("Algo ha salido mal, error Al registrar Administrador, Intnete nuevamente");
                    }
                }
                // En caso que si exista un admin con el mismo user que se quiere regustrar
                else {
                    return new Response("Ya Existe un Administrados Asociado al usuario " + Admin.getUser());
                }

            } else {
                return new Response(
                        "Ya Existe un Administrados Asociado a la identificación:  " + Admin.getIdentificacion());
            }

            // si el Objeto que llega por parametro es null
        } else {
            return new Response(
                    "No se pudo Registrar el Administrador, por favor proporcione la Inofrmacion correctamente");
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
}
