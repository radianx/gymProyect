/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.HorarioAlumno;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Family
 */
public class ControladorHorarioAlumno {

    ControladorPersistencia miPersistencia;
    List<HorarioAlumno> listaHorariosAlumnos;
    
    public ControladorHorarioAlumno(ControladorPersistencia miPersistencia) throws Notificaciones{
        this.miPersistencia = miPersistencia;
        listaHorariosAlumnos = this.miPersistencia.getHorariosAlumnos();
    }
    
    public void altaHorarioAlumno(HorarioAlumno unHorario) throws Notificaciones{
        this.miPersistencia.persistirInstancia(unHorario);
        this.listaHorariosAlumnos = miPersistencia.getHorariosAlumnos();
        this.miPersistencia.actualizarInstancias();
    }

    public List<HorarioAlumno> getListaHorariosAlumno(ClaseAlumno clase) throws Notificaciones {
        listaHorariosAlumnos = miPersistencia.getHorariosAlumnos();
        List<HorarioAlumno> retorno = new ArrayList<>();
        System.out.println("ControladorHorarioAlumno getListaHorariosAlumno");
        System.out.println("For claseAlumno: " + clase);
        for (HorarioAlumno horario : this.listaHorariosAlumnos) {
            System.out.println("For horarioAlumno: " + horario);
            if (horario.getEstado().equalsIgnoreCase("ACTIVO")
                    && horario.getClaseAlumno().getIdclasealumno() == clase.getIdclasealumno()) {
                System.out.println("Agregando...");
                retorno.add(horario);
            }
        }

        miPersistencia.actualizarInstancias();
        return retorno;
    }
    
    public List<ClaseAlumno> getListaClasesSinHorario(Alumno unAlumno) {
        List<ClaseAlumno> retorno = new ArrayList<>();
        for(ClaseAlumno claseAlu:unAlumno.getClaseAlumnos()){
            if(claseAlu.getHorarios().isEmpty()){
                retorno.add(claseAlu);
            }
        }
        return retorno;
    }

    public void bajaHorario(HorarioAlumno horario) throws Notificaciones {
        horario.setEstado("BAJA");
        miPersistencia.persistirInstancia(horario);
    }
}
