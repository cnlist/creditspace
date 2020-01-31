package us.cnlist.creditspace.creditspaceweb.controller.web.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import us.cnlist.creditspace.creditspaceweb.component.CustomServClient;
import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;
import us.cnlist.objects.people.UserProfile;

import javax.inject.Named;
import java.io.Serializable;

@Named
@SuppressWarnings({"autowired", "SpringJavaAutowiredFieldsWarningInspection"})
public class ProfileFormController extends ControllerCore implements Serializable {

    private UserProfile currentProfile;
    @Autowired
    private transient CustomServClient customerClient;
    @Autowired
    private transient JmsTemplate jmsTemplate;

    public UserProfile getCurrentProfile() {
        if (currentProfile == null) {
            currentProfile = customerClient.getProfileByEmail(getLoggedUser());
        }
        return currentProfile;
    }

    public void setCurrentProfile(UserProfile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public void saveProfile() {
        jmsTemplate.convertAndSend("userserv.profile.edit", this.currentProfile);

    }
}
