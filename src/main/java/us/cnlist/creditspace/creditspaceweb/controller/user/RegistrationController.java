package us.cnlist.creditspace.creditspaceweb.controller.user;

import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;
import us.cnlist.creditspace.creditspaceweb.util.ServerUrl;
import us.cnlist.objects.requests.UserRegistrationRQ;
import us.cnlist.objects.responses.ErrorType;
import us.cnlist.objects.responses.ResponseType;
import us.cnlist.objects.responses.UserRegistrationRS;

@Named
@RequestScoped
@Data
public class RegistrationController extends ControllerCore {

    private String firstName;
    private String password;
    private String passwordAgain;
    private String email;

    @Autowired
    private RestTemplate customServRestTemplate;

    public void registerNewUser() {

        if (!password.equals(passwordAgain)){
            msgError("Пароли не совпадают","проверьте правильность ввода паролей");
            return;
        }
        if (email.isEmpty()||password.isEmpty()||firstName.isEmpty()){
            msgError("Заполните поля","Необходимо правильно заполнить форму регистрации");
            return;
        }

        UserRegistrationRQ rq = new UserRegistrationRQ();
        rq.setPassword(password);
        rq.setEmail(email);
        UserRegistrationRS rs = customServRestTemplate.postForObject(
                ServerUrl.USER.REGISTER,
                rq,
                UserRegistrationRS.class
        );

        if (rs==null){
            msgError("Ошибка сервера","Произошла внутренняя ошибка");
            return;
        }

        if (rs.getResponseType()== ResponseType.ERROR){

            if (rs.getErrorType()== ErrorType.USER_EXISTS){
                msgError("Пользователь существует","Этот email уже зарегистрирован");
            }else {
                msgError("Ошибка",rs.getErrorMessage());
            }

            return;
        }

        msgInfo("Вы зарегистрированы",
                "Теперь можете войти в систему, используя email и пароль");

    }

}
