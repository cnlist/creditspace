package us.cnlist.creditspace.creditspaceweb.controller.web.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import us.cnlist.creditspace.creditspaceweb.component.CustomServClient;
import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;
import us.cnlist.objects.people.UserProfile;

import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
public class ProfileFormController extends ControllerCore implements Serializable {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private UserProfile currentProfile;
    @Autowired
    private CustomServClient customerClient;

    public ProfileFormController() {

    }

    public UserProfile getCurrentProfile() {
        currentProfile = customerClient.getProfileByEmail(getLoggedUser());
        return currentProfile;
    }

    public void setCurrentProfile(UserProfile currentProfile) {
        this.currentProfile = currentProfile;
    }

    public void saveProfile() {
        this.currentProfile.getCitizenData().setSecondName("abct");

    }
}
