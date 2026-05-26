           package com.Clinica.demo;

import com.Clinica.demo.model.*;
import com.Clinica.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PacienteRepository pacienteRepo, 
								 MedicoRepository medicoRepo,
								 AdministradorRepository adminRepo,
								 ConsultorioRepository consultorioRepo,
								 CuotaRepository cuotaRepo,
								 CitasRepository citasRepo) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean running = true;

			while (running) {
				System.out.println("\n===== CLÍNICA - MENÚ PRINCIPAL =====");
				System.out.println("1. Gestionar Pacientes");
				System.out.println("2. Gestionar Médicos");
				System.out.println("3. Gestionar Administradores");
				System.out.println("4. Gestionar Consultorios");
				System.out.println("5. Gestionar Cuotas");
				System.out.println("6. Gestionar Citas");
				System.out.println("0. Salir");
				System.out.print("Seleccione una opción: ");

				int opcion = scanner.nextInt();
				scanner.nextLine();

				switch (opcion) {
					case 1:
						menuPacientes(scanner, pacienteRepo);
						break;
					case 2:
						menuMedicos(scanner, medicoRepo, consultorioRepo);
						break;
					case 3:
						menuAdministradores(scanner, adminRepo);
						break;
					case 4:
						menuConsultorios(scanner, consultorioRepo);
						break;
					case 5:
						menuCuotas(scanner, cuotaRepo, pacienteRepo);
						break;
					case 6:
						menuCitas(scanner, citasRepo, pacienteRepo, medicoRepo);
						break;
					case 0:
						running = false;
						System.out.println("¡Hasta luego!");
						break;
					default:
						System.out.println("Opción inválida");
				}
			}
			scanner.close();
		};
	}

	private void menuPacientes(Scanner scanner, PacienteRepository repo) {
		System.out.println("\n--- GESTIÓN DE PACIENTES ---");
		System.out.println("1. Crear Paciente");
		System.out.println("2. Ver todos los Pacientes");
		System.out.println("3. Buscar Paciente por ID");
		System.out.println("4. Actualizar Paciente");
		System.out.println("5. Eliminar Paciente");
		System.out.print("Seleccione: ");

		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
			case 1:
				System.out.print("Nombre: ");
				String nombre = scanner.nextLine();
				System.out.print("Edad: ");
				int edad = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Cédula: ");
				String cedula = scanner.nextLine();
				System.out.print("Teléfono: ");
				String telefono = scanner.nextLine();
				System.out.print("EPS: ");
				String eps = scanner.nextLine();

				Paciente p = new Paciente(nombre, edad, cedula, telefono, eps);
				repo.save(p);
				System.out.println("✓ Paciente creado con ID: " + p.getId());
				break;

			case 2:
				List<Paciente> pacientes = repo.findAll();
				if (pacientes.isEmpty()) {
					System.out.println("No hay pacientes registrados");
				} else {
					for (Paciente pac : pacientes) {
						System.out.println("ID: " + pac.getId() + " | Nombre: " + pac.getNombreCompleto() + 
										   " | EPS: " + pac.getEPS());
					}
				}
				break;

			case 3:
				System.out.print("ID del paciente: ");
				Long id = scanner.nextLong();
				var pac = repo.findById(id);
				if (pac.isPresent()) {
					Paciente paciente = pac.get();
					System.out.println("Nombre: " + paciente.getNombreCompleto() + " | EPS: " + paciente.getEPS());
				} else {
					System.out.println("Paciente no encontrado");
				}
				break;

			case 4:
				System.out.print("ID del paciente a actualizar: ");
				Long idUpdate = scanner.nextLong();
				scanner.nextLine();
				var pacUpdate = repo.findById(idUpdate);
				if (pacUpdate.isPresent()) {
					Paciente paciente = pacUpdate.get();
					System.out.print("Nuevo EPS (actual: " + paciente.getEPS() + "): ");
					paciente.setEPS(scanner.nextLine());
					repo.save(paciente);
					System.out.println("✓ Paciente actualizado");
				} else {
					System.out.println("Paciente no encontrado");
				}
				break;

			case 5:
				System.out.print("ID del paciente a eliminar: ");
				Long idDelete = scanner.nextLong();
				if (repo.existsById(idDelete)) {
					repo.deleteById(idDelete);
					System.out.println("✓ Paciente eliminado");
				} else {
					System.out.println("Paciente no encontrado");
				}
				break;
		}
	}

	private void menuMedicos(Scanner scanner, MedicoRepository repo, ConsultorioRepository consultorioRepo) {
		System.out.println("\n--- GESTIÓN DE MÉDICOS ---");
		System.out.println("1. Crear Médico");
		System.out.println("2. Ver todos los Médicos");
		System.out.println("3. Buscar Médico por ID");
		System.out.println("4. Actualizar Médico");
		System.out.println("5. Eliminar Médico");
		System.out.print("Seleccione: ");

		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
			case 1:
				System.out.print("Nombre: ");
				String nombre = scanner.nextLine();
				System.out.print("Edad: ");
				int edad = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Cédula: ");
				String cedula = scanner.nextLine();
				System.out.print("Teléfono: ");
				String telefono = scanner.nextLine();
				System.out.print("Especialidad: ");
				String especialidad = scanner.nextLine();

				Medico m = new Medico(nombre, edad, cedula, telefono, especialidad);
				repo.save(m);
				System.out.println("✓ Médico creado con ID: " + m.getId());
				break;

			case 2:
				List<Medico> medicos = repo.findAll();
				if (medicos.isEmpty()) {
					System.out.println("No hay médicos registrados");
				} else {
					for (Medico med : medicos) {
						System.out.println("ID: " + med.getId() + " | Nombre: " + med.getNombreCompleto() + 
										   " | Especialidad: " + med.getEspecialidad());
					}
				}
				break;

			case 3:
				System.out.print("ID del médico: ");
				Long id = scanner.nextLong();
				var med = repo.findById(id);
				if (med.isPresent()) {
					Medico medico = med.get();
					System.out.println("Nombre: " + medico.getNombreCompleto() + 
									   " | Especialidad: " + medico.getEspecialidad());
				} else {
					System.out.println("Médico no encontrado");
				}
				break;

			case 4:
				System.out.print("ID del médico a actualizar: ");
				Long idUpdate = scanner.nextLong();
				scanner.nextLine();
				var medUpdate = repo.findById(idUpdate);
				if (medUpdate.isPresent()) {
					Medico medico = medUpdate.get();
					System.out.print("Nueva especialidad (actual: " + medico.getEspecialidad() + "): ");
					medico.setEspecialidad(scanner.nextLine());
					repo.save(medico);
					System.out.println("✓ Médico actualizado");
				} else {
					System.out.println("Médico no encontrado");
				}
				break;

			case 5:
				System.out.print("ID del médico a eliminar: ");
				Long idDelete = scanner.nextLong();
				if (repo.existsById(idDelete)) {
					repo.deleteById(idDelete);
					System.out.println("✓ Médico eliminado");
				} else {
					System.out.println("Médico no encontrado");
				}
				break;
		}
	}

	private void menuAdministradores(Scanner scanner, AdministradorRepository repo) {
		System.out.println("\n--- GESTIÓN DE ADMINISTRADORES ---");
		System.out.println("1. Crear Administrador");
		System.out.println("2. Ver todos los Administradores");
		System.out.println("3. Buscar Administrador por ID");
		System.out.println("4. Actualizar Administrador");
		System.out.println("5. Eliminar Administrador");
		System.out.print("Seleccione: ");

		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
			case 1:
				System.out.print("Nombre: ");
				String nombre = scanner.nextLine();
				System.out.print("Edad: ");
				int edad = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Cédula: ");
				String cedula = scanner.nextLine();
				System.out.print("Teléfono: ");
				String telefono = scanner.nextLine();
				System.out.print("Área Encargada: ");
				String area = scanner.nextLine();
				System.out.print("Nivel de Acceso: ");
				int nivel = scanner.nextInt();

				Administrador a = new Administrador(nombre, edad, cedula, telefono, area, nivel);
				repo.save(a);
				System.out.println("✓ Administrador creado con ID: " + a.getId());
				break;

			case 2:
				List<Administrador> admins = repo.findAll();
				if (admins.isEmpty()) {
					System.out.println("No hay administradores registrados");
				} else {
					for (Administrador admin : admins) {
						System.out.println("ID: " + admin.getId() + " | Nombre: " + admin.getNombreCompleto() + 
										   " | Área: " + admin.getAreaEncargada());
					}
				}
				break;

			case 3:
				System.out.print("ID del administrador: ");
				Long id = scanner.nextLong();
				var admin = repo.findById(id);
				if (admin.isPresent()) {
					Administrador administrador = admin.get();
					System.out.println("Nombre: " + administrador.getNombreCompleto() + 
									   " | Área: " + administrador.getAreaEncargada() +
									   " | Nivel: " + administrador.getNivelAcceso());
				} else {
					System.out.println("Administrador no encontrado");
				}
				break;

			case 4:
				System.out.print("ID del administrador a actualizar: ");
				Long idUpdate = scanner.nextLong();
				scanner.nextLine();
				var adminUpdate = repo.findById(idUpdate);
				if (adminUpdate.isPresent()) {
					Administrador admin1 = adminUpdate.get();
					System.out.print("Nueva área (actual: " + admin1.getAreaEncargada() + "): ");
					admin1.setAreaEncargada(scanner.nextLine());
					repo.save(admin1);
					System.out.println("✓ Administrador actualizado");
				} else {
					System.out.println("Administrador no encontrado");
				}
				break;

			case 5:
				System.out.print("ID del administrador a eliminar: ");
				Long idDelete = scanner.nextLong();
				if (repo.existsById(idDelete)) {
					repo.deleteById(idDelete);
					System.out.println("✓ Administrador eliminado");
				} else {
					System.out.println("Administrador no encontrado");
				}
				break;
		}
	}

	private void menuConsultorios(Scanner scanner, ConsultorioRepository repo) {
		System.out.println("\n--- GESTIÓN DE CONSULTORIOS ---");
		System.out.println("1. Crear Consultorio");
		System.out.println("2. Ver todos los Consultorios");
		System.out.println("3. Buscar Consultorio por ID");
		System.out.println("4. Actualizar Consultorio");
		System.out.println("5. Eliminar Consultorio");
		System.out.print("Seleccione: ");

		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
			case 1:
				System.out.print("Número Consultorio: ");
				int num = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Piso: ");
				int piso = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Estado: ");
				String estado = scanner.nextLine();

				Consultorio c = new Consultorio(num, piso, estado);
				repo.save(c);
				System.out.println("✓ Consultorio creado con ID: " + c.getId());
				break;

			case 2:
				List<Consultorio> consultorios = repo.findAll();
				if (consultorios.isEmpty()) {
					System.out.println("No hay consultorios registrados");
				} else {
					for (Consultorio cons : consultorios) {
						System.out.println("ID: " + cons.getId() + " | Número: " + cons.getNumConsultorio() + 
										   " | Piso: " + cons.getPisoConsultorio() + " | Estado: " + cons.getEstado());
					}
				}
				break;

			case 3:
				System.out.print("ID del consultorio: ");
				Long id = scanner.nextLong();
				var cons = repo.findById(id);
				if (cons.isPresent()) {
					Consultorio consultorio = cons.get();
					System.out.println("Número: " + consultorio.getNumConsultorio() + 
									   " | Piso: " + consultorio.getPisoConsultorio() +
									   " | Estado: " + consultorio.getEstado());
				} else {
					System.out.println("Consultorio no encontrado");
				}
				break;

			case 4:
				System.out.print("ID del consultorio a actualizar: ");
				Long idUpdate = scanner.nextLong();
				scanner.nextLine();
				var consUpdate = repo.findById(idUpdate);
				if (consUpdate.isPresent()) {
					Consultorio consultorio = consUpdate.get();
					System.out.print("Nuevo estado (actual: " + consultorio.getEstado() + "): ");
					consultorio.setEstado(scanner.nextLine());
					repo.save(consultorio);
					System.out.println("✓ Consultorio actualizado");
				} else {
					System.out.println("Consultorio no encontrado");
				}
				break;

			case 5:
				System.out.print("ID del consultorio a eliminar: ");
				Long idDelete = scanner.nextLong();
				if (repo.existsById(idDelete)) {
					repo.deleteById(idDelete);
					System.out.println("✓ Consultorio eliminado");
				} else {
					System.out.println("Consultorio no encontrado");
				}
				break;
		}
	}

	private void menuCuotas(Scanner scanner, CuotaRepository repo, PacienteRepository pacienteRepo) {
		System.out.println("\n--- GESTIÓN DE CUOTAS ---");
		System.out.println("1. Crear Cuota");
		System.out.println("2. Ver todas las Cuotas");
		System.out.println("3. Buscar Cuota por ID");
		System.out.println("4. Actualizar Cuota");
		System.out.println("5. Eliminar Cuota");
		System.out.print("Seleccione: ");

		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
			case 1:
				System.out.print("ID del Paciente: ");
				Long idPac = scanner.nextLong();
				scanner.nextLine();
				var paciente = pacienteRepo.findById(idPac);
				if (paciente.isPresent()) {
					System.out.print("Afiliación: ");
					String afiliacion = scanner.nextLine();
					System.out.print("Monto a Pagar: ");
					double monto = scanner.nextDouble();

					Cuota cuota = new Cuota();
					cuota.setPaciente(paciente.get());
					cuota.setNombrePaciente(paciente.get().getNombreCompleto());
					cuota.setCedulaPaciente(paciente.get().getCedula());
					cuota.setAfiliacion(afiliacion);
					cuota.setTotalPagar(monto);
					repo.save(cuota);
					System.out.println("✓ Cuota creada con ID: " + cuota.getId());
				} else {
					System.out.println("Paciente no encontrado");
				}
				break;

			case 2:
				List<Cuota> cuotas = repo.findAll();
				if (cuotas.isEmpty()) {
					System.out.println("No hay cuotas registradas");
				} else {
					for (Cuota cuota : cuotas) {
						System.out.println("ID: " + cuota.getId() + " | Paciente: " + cuota.getNombrePaciente() + 
										   " | Monto: " + cuota.getTotalPagar());
					}
				}
				break;

			case 3:
				System.out.print("ID de la cuota: ");
				Long id = scanner.nextLong();
				var cuota = repo.findById(id);
				if (cuota.isPresent()) {
					Cuota c = cuota.get();
					System.out.println("Paciente: " + c.getNombrePaciente() + 
									   " | Afiliación: " + c.getAfiliacion() +
									   " | Monto: " + c.getTotalPagar());
				} else {
					System.out.println("Cuota no encontrada");
				}
				break;

			case 4:
				System.out.print("ID de la cuota a actualizar: ");
				Long idUpdate = scanner.nextLong();
				scanner.nextLine();
				var cuotaUpdate = repo.findById(idUpdate);
				if (cuotaUpdate.isPresent()) {
					Cuota c = cuotaUpdate.get();
					System.out.print("Nuevo monto (actual: " + c.getTotalPagar() + "): ");
					c.setTotalPagar(scanner.nextDouble());
					repo.save(c);
					System.out.println("✓ Cuota actualizada");
				} else {
					System.out.println("Cuota no encontrada");
				}
				break;

			case 5:
				System.out.print("ID de la cuota a eliminar: ");
				Long idDelete = scanner.nextLong();
				if (repo.existsById(idDelete)) {
					repo.deleteById(idDelete);
					System.out.println("✓ Cuota eliminada");
				} else {
					System.out.println("Cuota no encontrada");
				}
				break;
		}
	}

	private void menuCitas(Scanner scanner, CitasRepository repo, PacienteRepository pacienteRepo, MedicoRepository medicoRepo) {
		System.out.println("\n--- GESTIÓN DE CITAS ---");
		System.out.println("1. Crear Cita");
		System.out.println("2. Ver todas las Citas");
		System.out.println("3. Buscar Cita por ID");
		System.out.println("4. Actualizar Cita");
		System.out.println("5. Eliminar Cita");
		System.out.print("Seleccione: ");

		int opcion = scanner.nextInt();
		scanner.nextLine();

		switch (opcion) {
			case 1:
				System.out.print("ID del Paciente: ");
				Long idPac = scanner.nextLong();
				System.out.print("ID del Médico: ");
				Long idMed = scanner.nextLong();
				scanner.nextLine();

				var paciente = pacienteRepo.findById(idPac);
				var medico = medicoRepo.findById(idMed);

				if (paciente.isPresent() && medico.isPresent()) {
					System.out.print("Fecha y Hora (ej: 2024-05-20 10:30): ");
					String fechaHora = scanner.nextLine();
					System.out.print("Estado: ");
					String estado = scanner.nextLine();

					Citas cita = new Citas(paciente.get(), medico.get(), fechaHora, estado);
					repo.save(cita);
					System.out.println("✓ Cita creada con ID: " + cita.getId());
				} else {
					System.out.println("Paciente o Médico no encontrados");
				}
				break;

			case 2:
				List<Citas> citas = repo.findAll();
				if (citas.isEmpty()) {
					System.out.println("No hay citas registradas");
				} else {
					for (Citas cita : citas) {
						System.out.println("ID: " + cita.getId() + " | Paciente: " + cita.getPaciente().getNombreCompleto() + 
										   " | Médico: " + cita.getMedico().getNombreCompleto() +
										   " | Fecha: " + cita.getFechaHora() + " | Estado: " + cita.getEstado());
					}
				}
				break;

			case 3:
				System.out.print("ID de la cita: ");
				Long id = scanner.nextLong();
				var cita = repo.findById(id);
				if (cita.isPresent()) {
					Citas c = cita.get();
					System.out.println("Paciente: " + c.getPaciente().getNombreCompleto() + 
									   " | Médico: " + c.getMedico().getNombreCompleto() +
									   " | Fecha: " + c.getFechaHora() + " | Estado: " + c.getEstado());
				} else {
					System.out.println("Cita no encontrada");
				}
				break;

			case 4:
				System.out.print("ID de la cita a actualizar: ");
				Long idUpdate = scanner.nextLong();
				scanner.nextLine();
				var citaUpdate = repo.findById(idUpdate);
				if (citaUpdate.isPresent()) {
					Citas c = citaUpdate.get();
					System.out.print("Nuevo estado (actual: " + c.getEstado() + "): ");
					c.setEstado(scanner.nextLine());
					repo.save(c);
					System.out.println("✓ Cita actualizada");
				} else {
					System.out.println("Cita no encontrada");
				}
				break;

			case 5:
				System.out.print("ID de la cita a eliminar: ");
				Long idDelete = scanner.nextLong();
				if (repo.existsById(idDelete)) {
					repo.deleteById(idDelete);
					System.out.println("✓ Cita eliminada");
				} else {
					System.out.println("Cita no encontrada");
				}
				break;
		}
	}
}
