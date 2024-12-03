package com.app.farmacia;

import com.app.farmacia.entity.*;
import com.app.farmacia.enums.RoleName;
import com.app.farmacia.repository.*;
import com.app.farmacia.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class FarmaciaApplication /*implements CommandLineRunner*/ {
	/*@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	NotificationService notificationService;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	LocalRepository localRepository;
	@Autowired
	LoteRepository loteRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(FarmaciaApplication.class, args);
	}

/*	@Override
	public void run(String... args) throws Exception {
//		Role adminRole = Role.builder().role(RoleName.ROLE_ADMIN).build();
//		Role userRole = Role.builder().role(RoleName.ROLE_USER).build();
//		Role employeeRole = Role.builder().role(RoleName.ROLE_EMPLOYEE).build();
//
//		roleRepository.save(adminRole);
//		roleRepository.save(userRole);
//		roleRepository.save(employeeRole);

//		User user = User.builder()
//				.username("chomy.rojas@farmacia.com")
//				.password(passwordEncoder.encode("chomy02"))
//				.nombre("Chomy")
//				.apellido("Rojas")
//				.dni("45632178")
//				.telefono("963258741")
//				.direccion("Av. Cualquiera")
//				.fechaRegistro(LocalDateTime.now())
//				.build();
//		List<String> listRoles = new ArrayList<>();
//		listRoles.add("ROLE_ADMIN");
//		listRoles.add("ROLE_USER");
//		listRoles.add("ROLE_EMPLOYEE");
//
//		Set<Role> roles = new HashSet<>();
//		listRoles.forEach(r -> {
//			Role role = roleRepository.findByRole(RoleName.valueOf(r))
//					.orElseThrow(() -> new RuntimeException("Role not found"));
//			roles.add(role);
//		});
//
//		user.setRoles(roles);
//		userRepository.save(user);

//		User user2 = User.builder()
//				.username("shamir.segarra@farmacia.com")
//				.password(passwordEncoder.encode("shamir123"))
//				.nombre("Shamir")
//				.apellido("Segarra")
//				.dni("45632178")
//				.telefono("963258741")
//				.direccion("Av. Cualquiera")
//				.fechaRegistro(LocalDateTime.now())
//				.build();
//		List<String> listRoles = new ArrayList<>();
//		listRoles.add("ROLE_USER");
//
//		Set<Role> roles = new HashSet<>();
//		listRoles.forEach(r -> {
//			Role role = roleRepository.findByRole(RoleName.valueOf(r))
//					.orElseThrow(() -> new RuntimeException("Role not found"));
//			roles.add(role);
//		});
//
//		user2.setRoles(roles);
//		userRepository.save(user2);
//
//		notificationService.notifyTest();

//		Categoria categoria = new Categoria();
//		categoria.setNombre("MEDICAMENTOS");
//		categoriaRepository.save(categoria);

//		Local local = Local.builder()
//				.local("LOS OLIVOS")
//				.ciudad("LIMA")
//				.direccion("Av. Cualquiera")
//				.build();
//		localRepository.save(local);
//
//		Local local2 = Local.builder()
//				.local("LOS COMAS")
//				.ciudad("LIMA")
//				.direccion("Av. Cualquiera")
//				.build();
//		localRepository.save(local2);

//		Lote lote = Lote.builder()
//				.lote("LOTE 01")
//				.fechaVencimiento(LocalDate.parse("2025-05-01"))
//				.build();
//
//		loteRepository.save(lote);
//
//		Lote lote2 = Lote.builder()
//				.lote("LOTE 02")
//				.fechaVencimiento(LocalDate.parse("2025-11-01"))
//				.build();
//
//		loteRepository.save(lote2);
	}*/
}
