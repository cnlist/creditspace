package us.cnlist.creditspace.creditspaceweb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import us.cnlist.objects.requests.AuthenticationRQ;
import us.cnlist.objects.responses.AuthenticationRS;
import us.cnlist.objects.responses.ResponseType;

import java.util.ArrayList;

@Component
public class CnAuthProvider implements AuthenticationProvider {
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private CustomServClient customerClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        AuthenticationRQ rq = new AuthenticationRQ();
        rq.setEmail(authentication.getName());
        rq.setPassword(authentication.getCredentials().toString());


        AuthenticationRS rs = customerClient.doAuth(rq);
        if (rs != null) {

            if (rs.getResponseType() == ResponseType.SUCCESS) {

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
