package us.cnlist.creditspace.creditspaceweb.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@SuppressWarnings({"autowired"})
public class ControllerCore {

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected boolean isUserAuthenticated() {

        return getAuthentication() != null
                && getAuthentication().isAuthenticated()
                && !(getAuthentication() instanceof AnonymousAuthenticationToken);

    }


    protected String getLoggedUser() {
        return getAuthentication().getName();
    }

    public FacesContext context() {
        return FacesContext.getCurrentInstance();
    }

    protected void facesRedirect(String where) {
        UIViewRoot newView =
                context().getApplication().getViewHandler().createView(
                        context(),
                        where);
        context().setViewRoot(newView);
        context().renderResponse();
    }

}
