package us.cnlist.creditspace.creditspaceweb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class CustomServConfig {
    @Value("${us.cnlist.customserv.uri}")
    private String customServRootUri;
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate customServRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplateBuilder()
                    .rootUri(customServRootUri)
                    .build();
        }
        return restTemplate;
    }


}
