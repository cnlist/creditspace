package us.cnlist.creditspace.creditspaceweb.controller.user;

import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserController extends ControllerCore {

    public void redirectFromIndex() {
        if (isUserAuthenticated()) {
            facesRedirect("/upf/index.xhtml");
        }
    }

}
