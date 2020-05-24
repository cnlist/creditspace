package us.cnlist.creditspace.creditspaceweb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.cnlist.creditspace.creditspaceweb.util.ServerUrl;
import us.cnlist.objects.people.UserProfile;
import us.cnlist.objects.requests.AuthenticationRQ;
import us.cnlist.objects.responses.AuthenticationRS;

@Component
public class CustomServClient {

    @Autowired
    private RestTemplate customServRestTemplate;


    public AuthenticationRS doAuth(AuthenticationRQ rq) {
        return customServRestTemplate
                .postForObject(ServerUrl.AUTH, rq, AuthenticationRS.class);
    }

    public UserProfile getProfileByEmail(String email) {
        return customServRestTemplate.getForObject(ServerUrl.PROFILE.GET_BY_EMAIL + email, UserProfile.class);
    }


}
