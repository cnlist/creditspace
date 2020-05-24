package us.cnlist.creditspace.creditspaceweb.component.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.cnlist.creditspace.creditspaceweb.component.CustomServClient;
import us.cnlist.creditspace.creditspaceweb.util.ServerUrl;
import us.cnlist.objects.contacts.Contact;
import us.cnlist.objects.people.Citizen;
import us.cnlist.objects.people.UserProfile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ContactsClient {

    @Autowired
    private RestTemplate customServRestTemplate;
    @Autowired
    private CustomServClient customServClient;

    public List<Contact> getCustomerContacts(String email) {
        UserProfile profile = customServClient.getProfileByEmail(email);
        return Arrays.asList(Objects.requireNonNull(customServRestTemplate.getForObject(ServerUrl.PROFILE.CONTACTS + profile.getCitizenData().getId(),
                Contact[].class)));
    }

    public void addContactToProfile(Contact contact, String email) {
        Citizen cc = new Citizen();
        cc.setId(customServClient.getProfileByEmail(email).getCitizenData().getId());
        // ContactToCitizenRq rq = new ContactToCitizenRq(cc, contact);
        //  customServRestTemplate.postForEntity(ServerUrl.PROFILE.CONTACTS, rq, Object.class);
    }

    public void deleteContact(Long id) {
        customServRestTemplate.getForObject(ServerUrl.PROFILE.CONTACT + "/delete?id=" + id, Object.class);
    }

    public void editContact(Contact contact) {
        customServRestTemplate.put(ServerUrl.PROFILE.CONTACT, contact);
    }

}