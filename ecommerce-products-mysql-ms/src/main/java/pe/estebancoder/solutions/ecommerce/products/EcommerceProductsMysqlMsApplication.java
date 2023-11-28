package pe.estebancoder.solutions.ecommerce.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EcommerceProductsMysqlMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductsMysqlMsApplication.class, args);
	}

}
