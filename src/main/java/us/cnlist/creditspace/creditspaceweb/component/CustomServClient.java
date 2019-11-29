package us.cnlist.creditspace.creditspaceweb.component;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import us.cnlist.creditspace.creditspaceweb.util.ServerUrl;
import us.cnlist.objects.messages.rq.AuthenticateUserRQ;
import us.cnlist.objects.messages.rs.AuthenticateUserRs;
import us.cnlist.objects.people.UserProfile;

public class CustomServClient {

    private final RestTemplate restTemplate;

    public CustomServClient(String serverUri) {
        restTemplate = new RestTemplateBuilder()
                .rootUri(serverUri)
                .build();
    }


    public AuthenticateUserRs doAuth(AuthenticateUserRQ rq) {
        return restTemplate
                .postForObject(ServerUrl.AUTH, rq, AuthenticateUserRs.class);
    }

    public UserProfile getProfileByEmail(String email) {
        return restTemplate.getForObject(ServerUrl.PROFILE.GET_BY_EMAIL + email, UserProfile.class);
    }

}
