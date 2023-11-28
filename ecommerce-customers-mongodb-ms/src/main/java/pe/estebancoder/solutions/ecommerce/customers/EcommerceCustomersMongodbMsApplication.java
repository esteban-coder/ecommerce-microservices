package pe.estebancoder.solutions.ecommerce.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EcommerceCustomersMongodbMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceCustomersMongodbMsApplication.class, args);
	}

}
