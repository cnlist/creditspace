package us.cnlist.creditspace.creditspaceweb.component;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import us.cnlist.objects.messages.rq.AuthenticateUserRQ;
import us.cnlist.objects.messages.rs.AuthenticateUserRs;

import java.util.ArrayList;

@Component
public class CnAuthProvider implements AuthenticationProvider {

    private final CustomServClient customerClient;

    public CnAuthProvider(CustomServClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        AuthenticateUserRQ rq = new AuthenticateUserRQ(
                authentication.getName(),
                authentication.getCredentials().toString()
        );

        AuthenticateUserRs rs = customerClient.doAuth(rq);
        if (rs != null && rs.isAuthenticated()) {

            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials().toString(), new ArrayList<>());

        }
        return null;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return true;
    }


}
