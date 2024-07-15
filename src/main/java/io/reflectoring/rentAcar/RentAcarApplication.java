package io.reflectoring.rentAcar;


import io.reflectoring.rentAcar.auth.role.Role;
import io.reflectoring.rentAcar.auth.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RentAcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentAcarApplication.class, args);
	}


	@Bean
	public CommandLineRunner runner_SUPER_USER(RoleRepository roleRepository){
		return args -> {
			if(roleRepository.findByName("SUPER_USER").isEmpty()){
				roleRepository.save(
						Role.builder().name("SUPER_USER").build()
				);
			}
		};
	}


	@Bean
	public CommandLineRunner runner_ADMIN(RoleRepository roleRepository){
		return args -> {
			if(roleRepository.findByName("ADMIN").isEmpty()){
				roleRepository.save(
						Role.builder().name("ADMIN").build()
				);
			}
		};
	}


	@Bean
	public CommandLineRunner runner_USER(RoleRepository roleRepository){
		return args -> {
			if(roleRepository.findByName("USER").isEmpty()){
				roleRepository.save(
						Role.builder().name("USER").build()
				);
			}
		};
	}


}
