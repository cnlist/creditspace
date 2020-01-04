package us.cnlist.creditspace.creditspaceweb.controller.web.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import us.cnlist.creditspace.creditspaceweb.component.CustomServClient;
import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;
import us.cnlist.objects.people.UserProfile;

import javax.inject.Named;
import java.io.Serializable;

@Named
public class ProfileFormController extends ControllerCore implements Serializable {

    private final String DESTINATION_SAVE_PROFILE = "userserv.profile.edit";
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private UserProfile currentProfile;
    @Autowired
    private CustomServClient customerClient;
    @Autowired
    private JmsTemplate jmsTemplate;

    public ProfileFormController() {

    }

    public UserProfile getCurrentProfile() {
        if (currentProfile==null) {
            currentProfile = customerClient.getProfileByEmail(getLoggedUser());
        }
        return currentProfile;
    }

    public void setCurrentProfile(UserProfile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public void saveProfile() {

        jmsTemplate.convertAndSend(DESTINATION_SAVE_PROFILE, this.currentProfile);

    }
}
