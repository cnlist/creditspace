package us.cnlist.creditspace.creditspaceweb.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class ControllerCore {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isUserAuthenticated() {

        return getAuthentication() != null
                && getAuthentication().isAuthenticated()
                && !(getAuthentication() instanceof AnonymousAuthenticationToken);

    }


    public String getLoggedUser() {
        String name = getAuthentication()
                .getName();
        System.out.println("name: " + name);
        return name;
    }

    public FacesContext context() {
        return FacesContext.getCurrentInstance();
    }

    public void facesRedirect(String where) {

        UIViewRoot newView =
                context().getApplication().getViewHandler().createView(
                        context(),
                        where);
        context().setViewRoot(newView);
        context().renderResponse();
    }

    public void msgError(String title, String text){
        context().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR,
                title,text));
    }

    public void msgInfo(String title, String message){
        context().addMessage("", new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                title,
                message
        ));
    }

}
