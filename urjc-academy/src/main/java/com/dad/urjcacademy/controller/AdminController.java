package com.dad.urjcacademy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.generator.PassGenerator;
import com.dad.urjcacademy.services.AlumnoService;
import com.dad.urjcacademy.services.AsignaturaService;
import com.dad.urjcacademy.services.ProfesorService;
import com.dad.urjcacademy.services.TitulacionService;
import com.dad.urjcacademy.services.TutoriaService;
import com.dad.urjcacademy.services.UsuarioService;

@Controller
@RequestMapping("/root")
public class AdminController extends UsuarioController{
	
	@Autowired
	private UsuarioService usuarios;
	
	@Autowired
	private AsignaturaService asignaturas;
	
	@Autowired
	private TutoriaService tutorias;
	
	@Autowired
	private TitulacionService titulaciones;
	
	/*Atributos Auxiliares*/
	private Titulacion titulacion;
	private Usuario usuario;
	private Profesor profesor;
	private Alumno alumno;
	private Asignatura asignatura;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String root(Model model) {
		
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
	public String nuevaAsignatura(Model model, @PathVariable long id,
			@RequestParam String nombre) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
		
			Asignatura asignatura = asignaturas.save(new Asignatura(nombre,new ArrayList<>(),
					new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
		
			if(asignatura != null) {
				titulacion.agregarAsignatura(asignatura);
				titulaciones.save(titulacion);
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
					if(titulaciones.desasignarAsignaturaTitulacion(titulacion, asignatura)) {
						asignaturas.deleteId(id);
						model.addAttribute("nombre", asignatura.getNombre());
						return "eliminado";
					}
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
			this.usuario = usuarios.save(new Profesor(login,maiLogin,pass,rol,nombre,apellido,tlf,new ArrayList<>(),new ArrayList<>()));
			profesor = (Profesor) usuarios.findById(usuario.getId());
			if(profesor != null) {
				profesor.setLogin(login+String.valueOf(profesor.getId()));
				usuario = usuarios.save(profesor);
			} else {
				return "404";
			}
		} else {
			this.usuario = usuarios.save(new Alumno(login,maiLogin,pass,rol,nombre,apellido,tlf,new ArrayList<>(), new ArrayList<>()));
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
			model.addAttribute("pass", usuario.getPass());
			if(esProfesor) {
				model.addAttribute("nombre", profesor.getNombre());
				model.addAttribute("apellido", profesor.getApellido());
				model.addAttribute("tlf",profesor.getTlf());
			} else {
				model.addAttribute("nombre", alumno.getNombre());
				model.addAttribute("apellido", alumno.getApellido());
				model.addAttribute("tlf", alumno.getTlf());
			}
			
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
	
	// Profesor
	@RequestMapping(value="/profesor/{id}/borrar", method=RequestMethod.GET)
	public String borrarProfesor(Model model, @PathVariable long id) {
		
		if(usuarios.exists(id)) {
			
			profesor = (Profesor) usuarios.findById(id);
			usuarios.delete(id);
			if(!usuarios.exists(id)) {
				model.addAttribute("nombre", "Profesor: " + profesor.getNombre() + " " + profesor.getApellido());
				return "eliminado";
			}
			
		}
		
		return "404";
	}
	
	@RequestMapping(value="/alumno/{id}/borrar", method=RequestMethod.GET)
	public String borraAlumno(Model model, @PathVariable long id) {
		
		if(usuarios.exists(id)) {
			
			usuario = (Alumno) usuarios.findById(id);
			usuarios.delete(id);
			if(!usuarios.exists(id)) {
				model.addAttribute("nombre", "Alumno: " + alumno.getNombre() + " " + alumno.getApellido());
				return "eliminado";
			}
			
		}
		
		return "404";
	}
	
	/*Agregar Profesor a Asignatura*/
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
	
	@RequestMapping(value="/asignatura/{id}/agregar-profesor/nuevo", method=RequestMethod.POST)
	public String insertProfesorAsignatura(Model model, @PathVariable long id,
			@RequestParam Profesor usuario) {
		
		if(titulaciones.exists(titulacion.getId())) {
			if(asignaturas.exists(asignatura.getId())) {
				if(usuarios.exists(usuario.getId())) {
					return "200";
				}
			}
		}
		
		return "500";
	}
	
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
	@RequestMapping(value="/asignatura/{id}/matricular-alumno/nuevo", method=RequestMethod.POST)
	public String insertAlumnoAsignatura(Model model, @PathVariable long id,
			@RequestParam Alumno usuario) {
		
		if(titulaciones.exists(titulacion.getId())) {
			if(asignaturas.exists(asignatura.getId())) {
				if(usuarios.exists(usuario.getId())) {
					return "200";
				}
			}
		}
		
		return "500";
	}
	
}
