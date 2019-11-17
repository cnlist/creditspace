package us.cnlist.creditspace.creditspaceweb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.cnlist.objects.messages.rq.AuthenticateUserRQ;
import us.cnlist.objects.messages.rs.AuthenticateUserRs;

import java.util.ArrayList;

@Component
public class CnAuthProvider implements AuthenticationProvider {
    @Autowired
    private RestTemplate authenticationRestTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        AuthenticateUserRQ rq = new AuthenticateUserRQ(
                authentication.getName(),
                authentication.getCredentials().toString()
        );

        AuthenticateUserRs rs = authenticationRestTemplate
                .postForObject("/auth", rq, AuthenticateUserRs.class);
        if (rs != null) {

            if (rs.isAuthenticated()) {

              UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.getName(),
                        authentication.getCredentials().toString(), new ArrayList<>());

              return token;
            }

        }
        return null;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return true;
    }
}
