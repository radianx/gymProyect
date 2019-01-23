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
    }

    public List<HorarioAlumno> getListaHorariosAlumno(Alumno unAlumno) {
        List<HorarioAlumno> retorno = new ArrayList<>();
        for(ClaseAlumno claseAlu:unAlumno.getClaseAlumnos()){
            for(HorarioAlumno horario:claseAlu.getHorarios()){
                retorno.add(horario);
            }
        }
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
}
