package com.backend.BackendSpringBootCreditpro.Services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    // Instancia automatica // Inyeccion de dependencia para JAVAMAILSENDER
    @Autowired
    private JavaMailSender mailSender;

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

    // #region BuscarAdmin por User

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

    // #endregion

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

    // #region Restaurar cuenta por email

    /**
     * @Descripcion Metodo para recuperar una cuenta basado en un usuario registrado
     * @param User
     * @return Response con la informacion de estado del correo
     */
    public Response RestoreAccount(String User) {

        AdministratorModel Admin = this.GetAdministrator(User);

        if (Admin != null) {

            // Envio del correo utilizando JAVAMAILSENDER

            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom("jonnierteranmorales@gmail.com");
            email.setTo(User);
            email.setSubject("SOPORTE TECNICO CEDITPRO MANAGER - RECUPERACIÓN DE CUENTA");
            email.setText("prbando");
            mailSender.send(email);

            return new Response("El correo de recuperación de cuenta ha sido envado exitosamente al email: " + User);

        } else {
            return new Response("El usuario " + User + " No existe, verifique el email ingresado e intente nuevamente");
        }

    }

    // #endregion

}
