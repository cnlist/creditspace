package us.cnlist.creditspace.creditspaceweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class CreditspaceWebApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(CreditspaceWebApplication.class, args);
    }

}
