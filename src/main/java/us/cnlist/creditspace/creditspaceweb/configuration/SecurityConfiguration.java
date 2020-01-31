package us.cnlist.creditspace.creditspaceweb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;
import us.cnlist.creditspace.creditspaceweb.component.CnAuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CnAuthProvider authProvider;
    @Value("${us.cnlist.auth}")
    private String rootUri;

    public SecurityConfiguration(CnAuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()

                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("pass")
                .successForwardUrl("/upf/index.xhtml")
                .and()
                .authorizeRequests()
                .antMatchers("/upf/**", "/upf")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index.xhtml")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public RestTemplate authenticationRestTemplate() {
        return
                new RestTemplateBuilder()
                        .rootUri(rootUri)
                        .build();
    }
}
