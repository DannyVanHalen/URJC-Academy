package com.dad.urjcacademy.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.generator.PassGenerator;
import com.dad.urjcacademy.internal_service.Mail;
//import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.services.AlumnoService;
import com.dad.urjcacademy.services.ApuntesService;
import com.dad.urjcacademy.services.AsignaturaService;
import com.dad.urjcacademy.services.ProfesorService;
import com.dad.urjcacademy.services.SesionService;
import com.dad.urjcacademy.services.TitulacionService;
//import com.dad.urjcacademy.services.TutoriaService;
import com.dad.urjcacademy.services.UsuarioService;

@Controller
@RequestMapping("/root")
public class AdminController extends UsuarioController{
	
	/*Instancia para el servicio Interno*/
	private static final String RESTSERVICE = "http://127.0.0.1:8070/send"; // URL del controlador Rest 
	private static final String SUBJECT = "Alta URJC-Academy";
	
	
	@Autowired
	private UsuarioService usuarios;
	
	@Autowired
	private AsignaturaService asignaturas;
	
	@Autowired
	private ApuntesService apuntes;
	
	@Autowired
	private ProfesorService profesores;
	
	@Autowired
	private AlumnoService alumnos;
	
	@Autowired
	private TitulacionService titulaciones;
	
	
	/*Atributos Auxiliares*/
	private Titulacion titulacion;
	private Usuario usuario;
	private Profesor profesor;
	private Alumno alumno;
	private Asignatura asignatura;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	//@Cacheable(cacheNames="usuarios", key="#id")
	public String root(Model model ,HttpServletRequest request) {
		
		//Lectura de datos
		//titulación
			model.addAttribute("titulaciones", titulaciones.findAll());
			model.addAttribute("profesores", usuarios.findByRol("profesor"));
			model.addAttribute("alumnos", usuarios.findByRol("alumno"));
			
			return "root";
		
		
	}
	
	/*Administración de Titulaciones*/
	
	// Formulario de Alta
	@RequestMapping(value="/alta-titulacion", method=RequestMethod.GET)
	public String formularioAltaTitulacion(Model model) {
		return "alta-titulacion";
	}
	
	// Alta Titulacion
	@RequestMapping(value="/nueva-titulacion", method=RequestMethod.POST)
	@CacheEvict(cacheNames="asignaturas")
	public String nuevaTitulacion(Model model,
			@RequestParam String nombre, @RequestParam String rama) {
		
		if(!nombre.equals(null) && !rama.equals(null)) {
			Titulacion titulacion = titulaciones.save(new Titulacion(nombre,rama,new ArrayList<>()));
			if(titulacion != null) {
				model.addAttribute("nombre", titulacion.getNombre());
				model.addAttribute("rama", titulacion.getRama());
				return "nueva-titulacion";
			}
		}
		
		return "505";
	}
	
	//Select Titulacion
	@RequestMapping(value="/titulacion/{id}", method=RequestMethod.GET)
	@Cacheable(cacheNames="asignaturas")
	public String selectTitulacion(Model model, @PathVariable long id) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
			model.addAttribute("nombre", titulacion.getNombre());
			model.addAttribute("rama", titulacion.getRama());
			model.addAttribute("asignaturas", titulacion.getAsignaturas());
			this.titulacion = titulacion;
			return "titulacion-root";
		}
		
		return "404";
	}
	
	//Borado Titulacion
	
	@RequestMapping(value="/titulacion/borrar/{id}", method=RequestMethod.GET)
	public String borrarTitulacion(Model model, @PathVariable long id) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
			//Borramos todas las asignaturas que tenga relacionadas esta titulacion
			for(Asignatura asignatura: titulacion.getAsignaturas()) {
				this.asignaturas.desasociarTodosProfesores(asignatura);
				this.asignaturas.desmatricularTodosAlumnos(asignatura);
				this.apuntes.quitarApuntesAsignatura(asignatura);
			}
			titulaciones.borrarAsignaturasTitulacion(titulacion);
			//Borramos la titulación
			titulaciones.delete(id);
			if(!titulaciones.exists(id)) {
				model.addAttribute("nombre", titulacion.getNombre());
				return "eliminado";
			}
		}else {
			return "404";
		}
		
		return "505";
		
	}
	
	/*Administracion de Asignaturas*/
	
	//Select
	@RequestMapping(value="/titulacion/asignatura/{id}", method=RequestMethod.GET)
	@Cacheable(cacheNames="asignaturas")
	public String selectAsignatura(Model model, @PathVariable long id) {
		
		Asignatura asignatura = asignaturas.findById(id);
		
		if(asignatura != null) {
			if(this.titulacion != null) {
				model.addAttribute("titulacion", titulacion.getNombre());
				model.addAttribute("asignatura", asignatura.getNombre());
				model.addAttribute("profesores", asignatura.getProfesores());
				model.addAttribute("alumnos", asignatura.getAlumnos());
				this.asignatura = asignatura;
				return "asignatura-root";
			}	
		}
		
		return "404";
	
	}
	
	// Agregar Asignatura
	// formulario
	@RequestMapping(value="/titulacion/{id}/agregar-asignatura", method=RequestMethod.GET)
	public String formularioAgregarAsignatura(Model model, @PathVariable long id) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
			model.addAttribute("titulacion", titulacion.getNombre());
			model.addAttribute("asignaturas",asignaturas.findAll());
			return "agregar-asignatura";
		}
		
		return "404";
	}
	
	// Agregar Asignatura existente
	@RequestMapping(value="/titulacion/{id}/agregar-asignatura/nueva-asignatura", method=RequestMethod.POST)
	@CacheEvict(cacheNames="asignaturas")
	public String agregarAsignatura(Model model, @PathVariable long id,
			@RequestParam Asignatura asignatura) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
			if(asignaturas.exists(asignatura.getId())) {
				titulaciones.asignarAsignaturaTitulacion(titulacion, asignatura);
				model.addAttribute("verbo", "agregado");
				model.addAttribute("nombre",asignatura.getNombre());
				model.addAttribute("titulacion", titulacion.getNombre());
				return "nueva-asignatura";
			}
		}
		
		return "500";
	}
	
	//Alta Asignatura
	@RequestMapping(value="/titulacion/{id}/alta-asignatura", method=RequestMethod.GET)
	public String formularioAltaAsignatura(Model model, @PathVariable long id) {
		Titulacion titulacion = titulaciones.findById(id);
		if(titulacion != null) {
			model.addAttribute("nombre", titulacion.getNombre());
			return "alta-asignatura";
		}
		return "404";
	}
	
	@RequestMapping(value="/titulacion/{id}/alta-asignatura/nueva-asignatura", method=RequestMethod.POST)
	@CacheEvict(cacheNames="asignaturas")
	public String nuevaAsignatura(Model model, @PathVariable long id,
			@RequestParam String nombre) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
		
			Asignatura asignatura = asignaturas.save(new Asignatura(nombre,new ArrayList<>(),
					new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
		
			if(asignatura != null) {
				titulacion.agregarAsignatura(asignatura);
				titulaciones.save(titulacion);
				model.addAttribute("verbo", "creado");
				model.addAttribute("nombre",asignatura.getNombre());
				model.addAttribute("titulacion", titulacion.getNombre());
				return "nueva-asignatura";
			}
		
		} else {
			return "404";
		}
		
		return "505";
		
	}
	
	//Borrado Asignatura
	@RequestMapping(value="/titulacion/asignatura/borrar/{id}", method=RequestMethod.GET)
	public String borrarAsignatura(Model model, @PathVariable long id) {
		
		Asignatura asignatura = asignaturas.findById(id);
		
		if(asignatura != null) {
			if(this.titulacion != null) {
				Titulacion titulacion = titulaciones.findById(this.titulacion.getId());
				if(titulacion.getAsignaturas().contains(asignatura)) {
						if(!asignatura.getApuntesAsignatura().isEmpty()) {
							this.apuntes.quitarApuntesAsignatura(asignatura);
							asignatura.borrarTodosApuntes();
						}
							
						asignaturas.desasociarTodosProfesores(asignatura);
						asignaturas.desmatricularTodosAlumnos(asignatura);
						profesores.eliminarProfesoresAsignatura(asignatura);
						alumnos.desmatricularAlumnosAsignatura(asignatura);
						asignaturas.deleteId(asignatura.getId());
						titulaciones.desasignarAsignaturaTitulacion(titulacion, asignatura);
						model.addAttribute("nombre", asignatura.getNombre());
						return "eliminado";
				}
			}
		} else {
			return "404";
		}
		
		return "505";
		
	}
	
	/*Usuario*/
	//Formulario AltaUsuario
	@RequestMapping(value="/alta-usuario", method=RequestMethod.GET)
	public String formularioAltaUsuario(Model model) {
		return "alta-usuario";
	}
	
	@RequestMapping(value="/nuevo-usuario", method=RequestMethod.POST)
	public String nuevoUsuario(Model model,
			@RequestParam String nombre, @RequestParam String apellido, 
			@RequestParam String tlf,@RequestParam String maiLogin, 
			@RequestParam String rol) {
		
		/*Vamos a generar el login de usuario de forma automatica
		 * 
		 * Para ello vamos a concatenar la primera inicial en minusculas 
		 * del nombre del usuario, seguido de su apellido, más el id de usuario,
		 * para obtener un login único para cada usuario.
		 *
		 * */
		
		boolean esProfesor = rol.contains("profesor");
		
		char initName = Character.toLowerCase(nombre.charAt(0)); // inicial nombre a minisculas
		char initLastName = Character.toLowerCase(apellido.charAt(0)); // inicial apellido a minisculas
		String lastName = apellido.substring(1);
		String login = String.valueOf(initName)+String.valueOf(initLastName)+lastName;
		
		/* Generamos la contraseña aleatoria*/
		
		String pass = new PassGenerator().getPassword();
		
		if(esProfesor) {
			String [] roles = {"ROLE_USER","ROLE_TEACHER"}; // Fase 3 seguridad
			this.usuario = usuarios.save(new Profesor(login,maiLogin,pass,"profesor",nombre,apellido,tlf,new ArrayList<>(),new ArrayList<>(),roles));
			profesor = (Profesor) usuarios.findById(usuario.getId());
			if(profesor != null) {
				profesor.setLogin(login+String.valueOf(profesor.getId()));
				usuario = usuarios.save(profesor);
			} else {
				return "404";
			}
		} else {
			String [] roles = {"ROLE_USER","ROLE_STUDENT"}; // Fase 3 seguridad
			this.usuario = usuarios.save(new Alumno(login,maiLogin,pass,"alumno",nombre,apellido,tlf,new ArrayList<>(), new ArrayList<>(),roles));
			alumno = (Alumno) usuarios.findById(usuario.getId());
			if(alumno != null) {
				alumno.setLogin(login+String.valueOf(alumno.getId()));
				usuario = usuarios.save(alumno);
			} else {
				return "404";
			}
		}
		
		
		if(usuario != null) {
			model.addAttribute("rol", usuario.getRol());
			model.addAttribute("login", usuario.getLogin());
			model.addAttribute("maiLogin", usuario.getMaiLogin());
			model.addAttribute("pass", pass);
			String nombreAux, apellidoAux;
			if(esProfesor) {
				nombreAux = profesor.getNombre();
				apellidoAux = profesor.getApellido();
				model.addAttribute("nombre", nombreAux);
				model.addAttribute("apellido", apellidoAux);
				model.addAttribute("tlf",profesor.getTlf());
			} else {
				nombreAux = alumno.getNombre();
				apellidoAux = alumno.getApellido();
				model.addAttribute("nombre", nombreAux);
				model.addAttribute("apellido", apellidoAux);
				model.addAttribute("tlf", alumno.getTlf());
			}
			
			/* FASE 3 -> Implementación del servicio Interno*/
			RestTemplate internalService = new RestTemplate();
			
			
			String from = usuarios.findById(1).getMaiLogin(); // dirección de correo remitente -> usuario Administrador
			String to = usuario.getMaiLogin(); // dirección del correo de destino -> nuevo usuario
			String body = "Hola " + nombreAux + ", has sido registrado como " + usuario.getRol() + " en URJC-Academy con las siguientes credenciales: \nLogin : " + usuario.getLogin() +"\nContraseña: " +pass+"\nTe recomendamos que cambies tu contraseña de acceso a la mayor brevedad posible.\nUn saludo de URJC-Academy.";
			
			// AL INVOCAR ESTE MÉTODO LE PASO COMO PARÁMETROS LA URL DEL CONTROLADOR REST Y EL CORREO QUE QUIERO ENVIAR AL DESTINATARIO
			internalService.postForLocation(RESTSERVICE, new Mail(from,to,SUBJECT,body));
			
			return "nuevo-usuario";
			
		}
		
		
		return "505";
	}
	
	//Select 
	
	//Profesor
	@RequestMapping(value="/profesor/{id}", method=RequestMethod.GET)
	public String selectProfesor(Model model, @PathVariable long id) {
		profesor = (Profesor) usuarios.findById(id);
		if(profesor != null) {
			model.addAttribute("nombre", profesor.getNombre() + " " + profesor.getApellido());
			model.addAttribute("asignaturas", profesor.getAsignaturas());
			model.addAttribute("tutorias",profesor.getTutoriasProfesor());
			
			return "profesor-root";
		}
		return "404";
	}
	
	

	
	
	@RequestMapping(value="/alumno/{id}", method=RequestMethod.GET)
	public String selectAlumno(Model model, @PathVariable long id) {
		alumno = (Alumno) usuarios.findById(id);
		if(alumno != null) {
			model.addAttribute("nombre", alumno.getNombre() + " " + alumno.getApellido());
			model.addAttribute("asignaturas", alumno.getAsignaturasAlumno());
			model.addAttribute("tutorias", alumno.getTutoriasAlumno());
			return "alumno-root";
		}
		return "404";
	}
	
	// Borrado Usuarios
	
	// baja profesor
	@RequestMapping(value="/profesor/{id}/borrar", method=RequestMethod.GET)
	public String bajaProfesor(Model model, @PathVariable long id) {
		
		if(usuarios.exists(id)) {
			Profesor profesor = (Profesor)usuarios.findById(id);
			
			profesores.quitarTodasAsignaturas(profesor);
			profesores.eliminarTodasTutoriasProfesor(profesor);
			usuarios.delete(id);
			return "200";
		}
		
		return "500";
	}
	
	
	
	@RequestMapping(value="/alumno/{id}/borrar", method=RequestMethod.GET)
	public String borraAlumno(Model model, @PathVariable long id) {
		
		if(usuarios.exists(id)) {
			Alumno alumno = (Alumno) usuarios.findById(id);
			alumnos.desmatricularAlumnosTodasAsignaturas(alumno);
			alumnos.eliminarTodasTutoriasAlumno(alumno);
			usuarios.delete(id);
			return "200";
		}
		
		return "404";
	}
	
	/*Agregar Profesor a Asignatura*/
	// formulario
	@RequestMapping(value="/asignatura/{id}/agregar-profesor", method=RequestMethod.GET)
	public String fomularioProfesorAsignatura(Model model, @PathVariable long id) {
		
		if(titulaciones.exists(titulacion.getId())) {
			if(asignaturas.exists(id)) {
				asignatura = asignaturas.findById(id);
				model.addAttribute("asignatura", asignatura.getNombre());
				model.addAttribute("titulacion", titulacion.getNombre());
				model.addAttribute("profesores", usuarios.findByRol("profesor"));
				return "agregar-profesor";
			}
		}
		
		return "404";
	}
	
	// insert
	@RequestMapping(value="/asignatura/{id}/agregar-profesor/nuevo", method=RequestMethod.POST)
	@CacheEvict(cacheNames="asignaturas")
	public String insertProfesorAsignatura(Model model, @PathVariable long id,
			@RequestParam Profesor usuario) {
		
		if(titulaciones.exists(titulacion.getId())) {
			if(asignaturas.exists(asignatura.getId())) {
				if(usuarios.exists(usuario.getId())) {
						Asignatura auxAsignatura = asignaturas.findById(asignatura.getId());
						usuario.asignarAsignatura(auxAsignatura);
						usuarios.save(usuario);
						asignaturas.asociarProfesorAsignatura(auxAsignatura, usuario);
						return "200";
				}
			}
		}
		
		return "500";
	}
	
	

	
	
	/*Matricular Alumno Asignatura*/
	
	// formulario
	@RequestMapping(value="/asignatura/{id}/matricular-alumno", method=RequestMethod.GET)
	public String formAlumnoAsignatura(Model model, @PathVariable long id) {
		
		if(titulaciones.exists(titulacion.getId())) {
			if(asignaturas.exists(id)) {
				asignatura = asignaturas.findById(id);
				model.addAttribute("asignatura", asignatura.getNombre());
				model.addAttribute("titulacion", titulacion.getNombre());
				model.addAttribute("alumnos", usuarios.findByRol("alumno"));
				return "matricular-alumno";
			}
		}
		
		return "404";
	}
	
	// insert
	@RequestMapping(value="/asignatura/{id}/matricular-alumno/nuevo", method=RequestMethod.POST)
	@CacheEvict(cacheNames="asignaturas")
	public String insertAlumnoAsignatura(Model model, @PathVariable long id,
			@RequestParam Alumno usuario) {
		
		if(titulaciones.exists(titulacion.getId())) {
			if(asignaturas.exists(asignatura.getId())) {
				if(usuarios.exists(usuario.getId())) {
					Asignatura auxAsignatura = asignaturas.findById(asignatura.getId());
					usuario.matricularAsignatura(auxAsignatura);
					usuarios.save(usuario);
					asignaturas.matircularAlumnoAsignatura(auxAsignatura, usuario);
					return "200";
				}
			}
		}
		
		return "500";
	}
	
}
