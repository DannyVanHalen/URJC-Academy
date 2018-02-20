package com.dad.urjcacademy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.repository.AlumnoRepository;
import com.dad.urjcacademy.repository.AsignaturaRepository;
import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.repository.TitulacionRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/asignatura")
public class AsignaturaController {

	@Autowired
	private AsignaturaRepository asignaturas;
	
	@Autowired
	private TitulacionRepository titulaciones;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private AlumnoRepository alumnos;
	
	@Autowired
	private ProfesorRepository profesores;
	
	private long id = -1;
	
	
	/** Parte usada por el Root o Administrador **/
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String asignaturas_titulacion(Model model, 
			@PathVariable long id) {
		
		Asignatura asignatura = asignaturas.findOne(id);
		
		if(asignatura != null) {
			
			model.addAttribute("asignatura", asignatura.getNombre());
			model.addAttribute("titulacion", asignatura.getTitulacion().getNombre());
			model.addAttribute("profesor", asignatura.getProfesor().getNombre()+ " " + asignatura.getProfesor().getApellido());
			model.addAttribute("alumnos",asignatura.getAlumnos());
			return "asignatura";
		}
		
		return "404";
	}
	
	@RequestMapping(value="/alta-asignatura", method=RequestMethod.GET)
	public String alta_asignatura(Model model) {
		model.addAttribute("titulaciones", titulaciones.findAll());
		model.addAttribute("profesores", usuarios.findByRol("profesor"));
		return "alta-asignatura";
	}
	
	@RequestMapping(value="/alta-asignatura/nueva-asignatura", method=RequestMethod.POST)
	public String guardar_asignatura(Model model, 
			@RequestParam String nombre, @RequestParam String titulacion, @RequestParam String profesor) {
		
		Titulacion titulacionEncontrada = titulaciones.findByNombre(titulacion);
		Profesor p = (Profesor) usuarios.findOne(Long.valueOf(profesor));
		
		if(titulacionEncontrada != null && p != null) {
			
			Asignatura asignatura = asignaturas.save(new Asignatura(nombre,titulacionEncontrada,p,new ArrayList<Alumno>(),new ArrayList<Apuntes>()));
			if(asignatura != null) {
				p.asignarAsignatura(asignatura);
				usuarios.save(p);
				titulacionEncontrada.agregarAsignatura(asignatura);
				titulaciones.save(titulacionEncontrada);
				model.addAttribute("nombre", asignatura.getNombre());
				model.addAttribute("alumnos", asignatura.getAlumnos());
				model.addAttribute("titulacion", asignatura.getTitulacion().getNombre());
				model.addAttribute("profesor", asignatura.getProfesor().getNombre()+ " " + asignatura.getProfesor().getApellido());
				return "nueva-asignatura";
			
			}
			
		}
		return "500";
		
	}
	
	
	@RequestMapping(value="/borrar/{id}",method=RequestMethod.GET)
	public String borra_asignatura(Model model,
			@PathVariable long id) {
			
			Asignatura asignatura = asignaturas.findOne(id);
			if(asignatura != null) {
				
				Titulacion titulacion = asignatura.getTitulacion();
				Profesor p = asignatura.getProfesor();
				if(titulacion != null && p != null) {
					p.quitarAsignatura(asignatura);
					titulacion.quitarAsignatura(asignatura);
					asignaturas.delete(asignatura);
					usuarios.save(p);
					titulaciones.save(titulacion);
					model.addAttribute("nombre", asignatura.getNombre());
					return "eliminado";
				
				}
				
			}
		
		
		return "404";	
		
	}
	
	
	/** Matricular Alumnos en una asignatura
	 * 
	 *  Este método del controlador asignatura sólo será usado por el Administrador Root
	 *  
	 *  **/
	@RequestMapping(value="/{id}/matricular-alumno", method=RequestMethod.GET)
	public String matricula_alumno(Model model, @PathVariable long id) {
		
		Asignatura asignatura = asignaturas.findOne(id);
		
		if(asignatura != null) {
			this.id = id;
			model.addAttribute("asignatura", asignatura.getNombre());
			model.addAttribute("titulacion", asignatura.getTitulacion().getNombre());
			model.addAttribute("profesor", asignatura.getProfesor().getNombre() + " "
					+ asignatura.getProfesor().getApellido());
			model.addAttribute("alumnos", alumnos.findAll());
			
			return "matricular-alumno";
			
		} else {
			return "404";
		}
		
	}
	
	/**
	@RequestMapping(value="/alumnos/matricular", method=RequestMethod.POST)
	public String alumnos_matriculados(Model model,
			@RequestParam List<Alumno> alumnos) {
		
		if(id != -1) {
			Asignatura asignatura = asignaturas.findOne(id);
		
			if(asignatura != null) {
				if(alumnos != null) {
					if(asignatura.apuntarAlumnos(alumnos)) {
						if(asignaturas.save(asignatura) != null) {
							model.addAttribute("asignatura", asignatura.getNombre());
							model.addAttribute("titulacion", asignatura.getTitulacion().getNombre());
							model.addAttribute("tieneProfesor", asignatura.tieneProfesor());
							model.addAttribute("profesor", asignatura.getProfesor().getNombre() + " " + asignatura.getProfesor().getApellido());
							model.addAttribute("alumnos", asignatura.getAlumnos());
							return "matricular";
						}
					}
				} 
				return "500";
			}
		}
		
		return "404";
	
	}**/
	
	@RequestMapping(value="/alumnos/matricular", method=RequestMethod.POST)
	public String matricular(Model model, @RequestParam Usuario usuario) {
		
		if(id != -1) {
			Asignatura asignatura = asignaturas.findOne(id);
			Alumno alumno = alumnos.findByLogin(usuario.getLogin());
			if(asignatura != null && alumno != null) {
				if(asignatura.apuntarAlumno(alumno)) {
					alumno.matricularAsignatura(asignatura);
					alumnos.save(alumno);
					usuarios.save(alumno);
					asignaturas.save(asignatura);
					model.addAttribute("asignatura", asignatura.getNombre());
					model.addAttribute("titulacion", asignatura.getTitulacion().getNombre());
					model.addAttribute("tieneProfesor", asignatura.tieneProfesor());
					model.addAttribute("profesor", asignatura.getProfesor().getNombre() + " " + asignatura.getProfesor().getApellido());
					model.addAttribute("alumnos", asignatura.getAlumnos());
					return "matricular";
				}
				return "500";
			}
		} 
		
		return "404";
	}
	
	
	
	


	
}
