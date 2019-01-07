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

    public ControladorAlumno(ControladorPersistencia persistencia, List<Alumno> alumnos) throws Notificaciones {
        this.miPersistencia = persistencia;
        this.listaAlumnos = miPersistencia.getAlumnos();
    }

    public void altaAlumno(Alumno alumno) throws Notificaciones {
        Alumno unAlumno = buscarAlumnoAlta(alumno.getNombrealumno(), alumno.getApellidoalumno());
        String estado = "ACTIVO";
        if (unAlumno != null) {
            unAlumno.setEstado(estado);
            unAlumno.setNombrealumno(alumno.getNombrealumno());
            unAlumno.setApellidoalumno(alumno.getApellidoalumno());
            unAlumno.setPeso(alumno.getPeso());
            unAlumno.setAltura(alumno.getAltura());
            unAlumno.setFechanacimiento(alumno.getFechanacimiento());
            this.miPersistencia.persistirInstancia(unAlumno);
            this.listaAlumnos = miPersistencia.getAlumnos();
        } else {
            this.miPersistencia.persistirInstancia(alumno);
            this.miPersistencia.getAlumnos();
        }
    }

    public void bajaAlumno(int idAlumno) throws Notificaciones {
        Alumno miAlumno = buscarAlumnoBaja(idAlumno);
        String estado = "INACTIVO";
        miAlumno.setEstado(estado);
        int i = this.listaAlumnos.lastIndexOf(miAlumno);
        if(i != -1){
            this.listaAlumnos.set(i, miAlumno);
        }else{
            throw new Notificaciones("Lista de Alumnos no actualizada");
        }

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

    public List<Alumno> getListaAlumnos() {
        return this.listaAlumnos;
    }

}
