package us.cnlist.creditspace.creditspaceweb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.cnlist.creditspace.creditspaceweb.component.CustomServClient;

@Configuration
public class CustomServConfig {
    @Value("${us.cnlist.customserv.uri}")
    private String customServRootUri;

    private CustomServClient customServClient;

    @Bean
    public CustomServClient customerClient() {
        if (customServClient == null) {
            customServClient = new CustomServClient(customServRootUri);

        }
        return customServClient;
    }


}
