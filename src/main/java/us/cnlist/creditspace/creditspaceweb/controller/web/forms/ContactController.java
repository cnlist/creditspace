package us.cnlist.creditspace.creditspaceweb.controller.web.forms;

import org.springframework.beans.factory.annotation.Autowired;
import us.cnlist.creditspace.creditspaceweb.component.contacts.ContactsClient;
import us.cnlist.creditspace.creditspaceweb.controller.ControllerCore;
import us.cnlist.objects.contacts.Contact;
import us.cnlist.objects.contacts.ContactType;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SuppressWarnings({"autowired", "SpringJavaAutowiredFieldsWarningInspection"})
public class ContactController extends ControllerCore {

    @Autowired
    private ContactsClient contactsClient;
    private ContactType selectedContactType;
    private String contactValue;

    private Contact tableSelectedContact;

    public List<Contact> getProfileContacts() {
        return contactsClient.getCustomerContacts(getLoggedUser());
    }


    public void createContact() {
        Contact contact = new Contact();
        contact.setContactType(selectedContactType);
        contact.setValue(contactValue);
        contactsClient.addContactToProfile(contact, getLoggedUser());

    }

    public List<ContactTypeDesc> getContactTypes() {
        return Arrays.stream(ContactType.values())
                .map(ContactTypeDesc::new).collect(Collectors.toList());
    }

    public ContactType getSelectedContactType() {
        return selectedContactType;
    }

    public void setSelectedContactType(ContactType selectedContactType) {
        this.selectedContactType = selectedContactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public String desc(ContactType type) {
        return new ContactTypeDesc(type).getDescription();
    }

    public Contact getTableSelectedContact() {
        return tableSelectedContact;
    }

    public void setTableSelectedContact(Contact tableSelectedContact) {
        this.tableSelectedContact = tableSelectedContact;
    }

    public void deleteContact(Long contactId) {
        contactsClient.deleteContact(contactId);
    }

    public void editContact(){
        contactsClient.editContact(tableSelectedContact);
    }

}
