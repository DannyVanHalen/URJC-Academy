package com.dad.urjcacademy.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Apuntes;
<<<<<<< HEAD
//import com.dad.urjcacademy.entity.Asignatura;


public interface ApuntesRepository extends JpaRepository<Apuntes, Long>{

	Apuntes findByTema(String tema);
=======
import com.dad.urjcacademy.entity.Asignatura;
//import com.dad.urjcacademy.entity.Asignatura;


public interface ApuntesRepository extends JpaRepository<Apuntes, Long>{

	Apuntes findByTema(String tema);
	Apuntes findByAsignaturaAndTema(Asignatura asignatura, String tema);
>>>>>>> refs/remotes/origin/develop
	
}
