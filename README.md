# URJC-Academy

# Fase 1
### Descripción de la web
La aplicación pretende agilizar las labores de administración, admisión, gestión y planificación de la actividad de una academia universitaria.
-  Parte Pública: Consistirá en una capa muy sencilla en donde los usuarios no registrados puedan obtener información de los cursos o asignaturas que se realizan en la academia. Si están interesados, podrán darse de alta como usuarios "Alumno", a través de un sencillo formulario de inscripción.
-  Parte Privada: 
    -   Alumno: El alumno podrá inscribirse en una o varias asignaturas, solicitar tutorias de refuerzo a los profesores, descargar apuntes de las clases, etc.
    -   Profesor: El profesor podrá validar solicitudes para tutorías, cambiar la fecha de una clase, subir apuntes, etc.
    -   Administrador: Se encargará de gestionar la página, dando de alta o baja a usuarios. Se encargará de habilitar enlaces de recuperación de contraseña, a aquellos usuarios registrados que no recuerden su contraseña de acceso, etc.
    
### Entidades
-   Alumno: Usuario final básico. Entre sus funciones estarán la de inscribirse a una o varias asignaturas, valorar la labor de los profesores, así como solicitar tutoriás a los profesores a través de la aplicación.
-   Profesor: Usuario final con ciertos privilegios, que le permitirán cerrar la fecha de las tutorías con los alumnos, cambiar la fecha de una clase, subir apuntes a la apliacación. Cada profesor tendrá una columna con la valoración media emitida por todos los alumnos a los que haya dado clase.
-   Administrador: Usuario con privilegios de root.
-   Titulación: Repositorio en donde los alumnos podrán consultar, e inscribirse a todas las asignaturas de su titulación.
-   Asignatura/Clase: Asignatura x de una titulación y. La clase la dara un profesor para un grupo de alumnos, en una fecha establecida.
-   Tutoria:  Clase personalizada, que previamente será solicitada por un alumno, y validada por un profesor.
-   Apuntes. 

### Servicio Interno
-   La apliación ha de ser capaz de mantener informados a los usuarios vía correo electrónico, o vía whatsapp (si podemos conseguir implementar este último), de cualquiera de los cambios que se produzcan en la página web, referidos al planning de la propia academía.

### Integrantes
-   Andrés Casado García: a.casadoga@alumnos.urjc.es
-   Daniel Fuerte Álvarez: d.fuerte@alumnos.urjc.es
-   Alvaro Muñoz Negreles: a.munozn@alumnos.urjc.es

# Fase 2
### Diagrama de Clases
Este es el diagrama de clases de nuestra aplicación, siempre sujeto a cambios si fuese necesario.

![](imagenes/Diagrama_de_clases_Fase2.png "Diagrama_de_clases_Fase2")

### Diagrama de transicion pantallas.
La transicion entre pantallas es sencilla ya que este es el esquema base de nuestra aplicación. A medida que añadamos funcionalidades el número de pantallas puede aumentar.

![](imagenes/Diagrama_de_pantallas_Fase2.png "Diagrama_de_pantallas_Fase2")

### Diagrama Entidad-Relación
A continuación se puede observar el diagrama Entidad-Relación de nuestro modelo de datos.

![](imagenes/E_R.jpg "E_R")

# Fase 3
### Despliegue de la aplicación
-   Para el despliegue de la aplicación se necesita un entorno virtualizado con red privada, acceso a internet y java instalado. En nuestro caso usamos Vagrant con ubuntu 14.04 y un repositorio de imagenes, VirtualBox.
-   Para instalar VirtualBox y Vagrant:
-   Instala Virtualbox descargando .deb
 -      https://www.virtualbox.org/wiki/Downloads
-   Instala Vagrant descargando .deb
 -      https://www.vagrantup.com/downloads.html
-   Crear una maquina virtual:
-   Ejecutar:
    -   mkdir -p ~/vagrant/spring
    -   cd ~/vagrant/spring
    -   vagrant init ubuntu/trusty32
-   Se crea una máquina virtual
-   Se genera un fichero Vagrantfile que describe la máquina virtual
-   Está basada en la “box” ubuntu/trusty32 que se descarga automáticame
-   Arranca la máquina:
    -   vagrant up
-   Acceso a la maquina virutal por red: descomentar # config.vm.network "private_network", ip: "192.168.33.10”
-   Conectar por ssh a la MV:
    -   vagrant ssh
-   Instalar Java desde dentro de la MV: 
    -   vagrant> Sudo apt-get update
    -   vagrant> Sudo apt-get install -y openjdk-8-jre
-   Instalar MySQL desde dentro de la MV:
    -   vagrant> sudo apt-get install mysql-server
-   Copiar el ejecutable .jar de nuestra aplicación en ~/vagrant/spring:
    -   vagrant ssh
    -   vagrant> cd /vagrant
    -   vagrant> java -jar <urjc-academy-0.0.1-SNAPSHOT.jar>
    -   En un navegador desde el host abrir la url:
          -  http://192.168.33.10:8080




    
