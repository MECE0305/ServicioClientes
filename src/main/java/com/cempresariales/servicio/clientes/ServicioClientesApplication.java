package com.cempresariales.servicio.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.cempresariales.servicio.commons.model.entity"})
//public class ServicioClientesApplication extends SpringBootServletInitializer{
public class ServicioClientesApplication {

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ServicioClientesApplication.class);
	}*/
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ServicioClientesApplication.class, args);
	}

}
