package us.cnlist.creditspace.creditspaceweb.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.cnlist.creditspace.creditspaceweb.util.ServerUrl;
import us.cnlist.objects.messages.rq.AuthenticateUserRQ;
import us.cnlist.objects.messages.rs.AuthenticateUserRs;
import us.cnlist.objects.people.UserProfile;

@Component
public class CustomServClient {

    private final RestTemplate customServRestTemplate;

    public CustomServClient(RestTemplate customServRestTemplate) {
        this.customServRestTemplate = customServRestTemplate;
    }

    AuthenticateUserRs doAuth(AuthenticateUserRQ rq) {
        return customServRestTemplate
                .postForObject(ServerUrl.AUTH, rq, AuthenticateUserRs.class);
    }

    public UserProfile getProfileByEmail(String email) {
        return customServRestTemplate.getForObject(ServerUrl.PROFILE.GET_BY_EMAIL + email, UserProfile.class);
    }


}
