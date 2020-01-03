package us.cnlist.creditspace.creditspaceweb.controller.web.forms;

import org.springframework.beans.factory.annotation.Autowired;
import us.cnlist.creditspace.creditspaceweb.component.CustomServClient;
import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;
import us.cnlist.objects.contacts.Contact;

import javax.inject.Named;
import java.util.List;

@Named
public class ContactController extends ControllerCore {

    @Autowired
    private CustomServClient customServClient;

    public List<Contact> getProfileContacts() {
        return customServClient.getCustomerContacts(getLoggedUser());
    }

}
