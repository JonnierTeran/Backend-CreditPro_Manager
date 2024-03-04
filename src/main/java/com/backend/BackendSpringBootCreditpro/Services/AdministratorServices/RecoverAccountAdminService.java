package com.backend.BackendSpringBootCreditpro.Services.AdministratorServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.BackendSpringBootCreditpro.Models.AdministratorModel;
import com.backend.BackendSpringBootCreditpro.Models.Response;
import com.backend.BackendSpringBootCreditpro.Repositories.AdministratorRepository;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

//Notacion de servicio
@Service
public class RecoverAccountAdminService {

    // Instancia Automatica // Inyeccion de Dependencias
    @Autowired
    AdministratorRepository _AdministratorRepository;

    @Autowired
    QueriesAdministratorService _QueriesAdministratorService;

    public RecoverAccountAdminService() {
    }

    // #region Restaurar cuenta por email

    /**
     * @Descripcion Metodo para recuperar una cuenta basado en un usuario registrado
     * @param User
     * @return Response con la informacion de estado del correo
     */
    public Response RestoreAccount(String User) {

        AdministratorModel Admin = this._QueriesAdministratorService.GetAdministrator(User);

        if (Admin != null) {

            // Verificacion que el usuario si tenga apikey
            if (Admin.getApikeyResend() != null && !Admin.getApikeyResend().isEmpty()) {

                // Envio del correo utilizando la API DE RESEND MAILS

                Resend resend = new Resend(Admin.getApikeyResend());

                SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                        .from("onboarding@resend.dev")
                        .to(User)
                        .subject("SOPORTE TECNICO CEDITPRO MANAGER - RECUPERACIÓN DE CUENTA")
                        .html("<strong>Cordial saludo. <br> </strong> <br> Querido usuario  <strong>" + Admin.getName()
                                + " "
                                + Admin.getLastName()
                                + ", </strong> el equipo de soporte tecnico se permite enviar a usted luego de solicitar el restablecimiento de su contraseña para ingresar al sofware <strong> CREDITPRO-MANAGER </strong> su password de acceso, se recomienda de forma obligatoria realizar un cambio de contraseña al ingresar al aplicativo web de forma urgente para garantizar seguridad y calidad en el mantenimiento de la información, recuerde que el email de acceso es el mismo de la cuenta en la que recibe este email.  <br> <br>  Contraseña de Acceso: "
                                + Admin.getPassword()
                                + " <br> <br>  Gracias por utilizar nuestros serviciós. <br> <hr> <strong> CREDITPRO-MANAGER </strong> <br> Si necestia soporte tecnico comunicarse al correo: <strong> jonnierteranmorales@gmail.com </strong>")
                        .build();

                try {
                    SendEmailResponse data = resend.emails().send(sendEmailRequest);
                    System.out.println(data.getId());
                    return new Response(
                            "La información de recuperación de cuenta ha sido enviada exitosamente al email: " + User);
                } catch (ResendException e) {
                    e.printStackTrace();
                    return new Response("Error al enviar el correo");
                }
            } else {
                return new Response("El usuario " + User
                        + " No tiene APIKEY de restablecimiento, pongase en contacto con Jonnierteranmorales@gmail.com para mas informacion");
            }

        } else {
            return new Response("El usuario " + User + " No existe, verifique el email ingresado e intente nuevamente");
        }

    }

    // #endregion

}
