package us.cnlist.creditspace.creditspaceweb.controller.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import us.cnlist.objects.messages.rq.NamedUserRegisterRq;
import us.cnlist.objects.people.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
@Data
public class RegistrationController {

    private String firstName;
    private String password;
    private String passwordAgain;
    private String email;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void registerNewUser() {
        NamedUserRegisterRq rq = new NamedUserRegisterRq();
        rq.setName(firstName);
        User user = new User();
        user.setLogin(email);
        user.setPassword(password);
        rq.setUser(user);
        jmsTemplate.convertAndSend("userserv.register",rq);
    }

}