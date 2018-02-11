package com.dad.urjcacademy.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Admin;
import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.repository.AlumnoRepository;
//import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.AsignaturaRepository;
import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.repository.TitulacionRepository;
import com.dad.urjcacademy.repository.TutoriaRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Component
public class URJCAcademyDBLoader {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private AsignaturaRepository asignaturas;
	
	@Autowired
	private TitulacionRepository titulaciones;
	
	
	@Autowired
	private TutoriaRepository tutorias;
	
	@Autowired
	private ProfesorRepository profesores;
	
	@Autowired
	private AlumnoRepository alumnos;
	
	@PostConstruct
	public void initdDB() {
		
		
		if(usuarios.findByLogin("root") == null) 
			usuarios.save(new Admin("root","urjc.academy.root@gmail.com","sudosu12345@","administrador"));
		
		if(titulaciones.findAll().size() == 0) {
			Titulacion titulacion = titulaciones.save(new Titulacion("Ingeniería de Computadores", "Informática",new ArrayList<Asignatura>()));
			Alumno alumno = alumnos.save(new Alumno("dvanhalen","danny.van.halen.87@gmail.com","12345","alumno","Daniel","Van Halen","51501984316", new ArrayList<Tutoria>(),new ArrayList<Asignatura>()));
			Profesor profesor = (Profesor) usuarios.findByLogin("acasado47");
			Asignatura asignatura = new Asignatura("DAD",10,profesor,titulacion,new ArrayList<Alumno>(),new ArrayList<Apuntes>(),new ArrayList<Tutoria>());
			if(asignatura.matricularAlumno(alumno)) {
				asignaturas.save(asignatura);
			}
			Tutoria tutoria = tutorias.save(new Tutoria(LocalDateTime.now(),asignatura));
			if(asignatura.agregarTutoria(tutoria)) {
				profesor.setTutorias(new ArrayList<Tutoria>());
				profesor.asignarTutoria(tutoria);
				alumno.setTutorias(new ArrayList<Tutoria>());
				alumno.asignarTutoria(tutoria);
				usuarios.save(profesor);
				usuarios.save(alumno);
				asignaturas.save(asignatura);
			}
			
		}
		
	
	}
	
}
