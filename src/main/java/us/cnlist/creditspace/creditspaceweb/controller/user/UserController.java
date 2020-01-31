package us.cnlist.creditspace.creditspaceweb.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import us.cnlist.creditspace.creditspaceweb.component.CustomServClient;
import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;
import us.cnlist.objects.people.UserProfile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
@SuppressWarnings({"autowired", "SpringJavaAutowiredFieldsWarningInspection"})
public class UserController extends ControllerCore {


    @Autowired
    private CustomServClient customerClient;

    public void redirectFromIndex() {
        if (isUserAuthenticated()) {
            facesRedirect("/upf/index.xhtml");
        }
    }

    public UserProfile getUserProfile() {
      return customerClient.getProfileByEmail(getLoggedUser());
          }

}
