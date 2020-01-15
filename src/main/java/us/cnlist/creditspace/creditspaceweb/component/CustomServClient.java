package us.cnlist.creditspace.creditspaceweb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.cnlist.creditspace.creditspaceweb.util.ServerUrl;
import us.cnlist.objects.contacts.Contact;
import us.cnlist.objects.messages.rq.AuthenticateUserRQ;
import us.cnlist.objects.messages.rs.AuthenticateUserRs;
import us.cnlist.objects.people.UserProfile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CustomServClient {

    @Autowired
    private RestTemplate customServRestTemplate;


    public AuthenticateUserRs doAuth(AuthenticateUserRQ rq) {
        return customServRestTemplate
                .postForObject(ServerUrl.AUTH, rq, AuthenticateUserRs.class);
    }

    public UserProfile getProfileByEmail(String email) {
        return customServRestTemplate.getForObject(ServerUrl.PROFILE.GET_BY_EMAIL + email, UserProfile.class);
    }



}
