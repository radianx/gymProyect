/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorAlumno {

    private ControladorPersistencia miPersistencia;
    private List<Alumno> listaAlumnos;

    public ControladorAlumno(ControladorPersistencia persistencia) throws Notificaciones {
        this.miPersistencia = persistencia;
        this.listaAlumnos = miPersistencia.getAlumnos();
    }

    public void altaAlumno(Alumno alumno) throws Notificaciones {
        this.miPersistencia.persistirInstancia(alumno);
        listaAlumnos = miPersistencia.getAlumnos();
    }

    public void bajaAlumno(Alumno miAlumno) throws Notificaciones {
        String estado = "INACTIVO";
        miAlumno.setEstado(estado);

        miPersistencia.persistirInstancia(miAlumno);
    }

    public Alumno buscarAlumnoAlta(String nombrealu, String apellido) {
        Alumno unAlumno = null;
        for (Alumno miAlumno : this.listaAlumnos) {
            if (miAlumno.getNombrealumno().equalsIgnoreCase(nombrealu) && miAlumno.getApellidoalumno().equalsIgnoreCase(apellido)) {
                unAlumno = miAlumno;
                break;
            }
        }
        return unAlumno;
    }

    public Alumno buscarAlumnoBaja(int idAlumno) {
        Alumno unAlumno = null;
        for (Alumno miAlumno : this.listaAlumnos) {
            if (miAlumno.getIdalumno() == idAlumno) {
                unAlumno = miAlumno;
                break;
            }
        }
        return unAlumno;
    }

    public List<Alumno> getListaAlumnos() throws Notificaciones {
        miPersistencia.actualizarInstancias();
        listaAlumnos = miPersistencia.getAlumnos();
        miPersistencia.actualizarInstancias();
        return this.listaAlumnos;
    }

    public Alumno buscarAlumnoFromDB(Alumno unAlu) throws Notificaciones{
        Alumno retorno = null;
        listaAlumnos = miPersistencia.getAlumnos();
        int i = listaAlumnos.indexOf(unAlu);
        try{
            retorno = listaAlumnos.get(i);  
        }catch(ArrayIndexOutOfBoundsException ex){
            throw new Notificaciones("No hay alumno asociado a la huella");
        }
        return retorno;
    }

}
