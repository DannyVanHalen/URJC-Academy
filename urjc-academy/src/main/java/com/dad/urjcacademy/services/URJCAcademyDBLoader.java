package com.dad.urjcacademy.services;

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
//import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.AsignaturaRepository;
import com.dad.urjcacademy.repository.TitulacionRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Component
public class URJCAcademyDBLoader {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private AsignaturaRepository asignaturas;
	
	@Autowired
	private TitulacionRepository titulaciones;
	
	@PostConstruct
	public void initdDB() {
		
		Profesor profesor = null;
		
		if(usuarios.findByLogin("root") == null) 
			usuarios.save(new Admin("root","urjc.academy.root@gmail.com","sudosu12345@","administrador"));
		
		if(usuarios.findAll().size() <= 1) {
			profesor = usuarios.save(new Profesor("acasado","andres190294@gmail.com","12345","profesor","Andrés","Casado","111 111 111",new ArrayList<Tutoria>(),new ArrayList<Asignatura>()));
			usuarios.save(new Alumno("dvanhalen","danny.van.halen.87@gmail.com","12345","alumno","Danny","Van Halen","316 316 316",new ArrayList<Tutoria>(),new ArrayList<Asignatura>()));
		}
		
		if(titulaciones.findAll().size() == 0) {
			Titulacion titulacion = titulaciones.save(new Titulacion("Ingeniería de Computadores","Informática",new ArrayList<Asignatura>()));
			Asignatura pc = asignaturas.save(new Asignatura("Programación Concurrente",20,profesor,titulacion,new ArrayList<Alumno>(),new ArrayList<Apuntes>(),new ArrayList<Tutoria>()));
			titulacion.getAsignaturas().add(pc);
			titulaciones.save(titulacion);
		}
	
	}
	
}
