/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.ClaseAlumno;
import java.util.List;

/**
 *
 * @author Osoft
 */
public class ControladorClaseAlumno {

    private ControladorPersistencia miPersistencia;
    private List<ClaseAlumno>listaClaseAlumno;
    
    public ControladorClaseAlumno(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.miPersistencia = miPersistencia;
        listaClaseAlumno = this.miPersistencia.getClasesAlumno();
    }

    public void altaClaseAlumno(ClaseAlumno claseAlumno) throws Notificaciones {
        listaClaseAlumno = miPersistencia.getClasesAlumno();
        for (ClaseAlumno claseAlu : listaClaseAlumno) {
            if (claseAlu.getAlumno().getIdalumno() == claseAlumno.getAlumno().getIdalumno()
                    && claseAlu.getEstado().equalsIgnoreCase("ACTIVO")) {
                throw new Notificaciones("Ya existe un registro de alumno en esa clase");
            } else {
                miPersistencia.persistirInstancia(claseAlumno);
            }
        }
    }
    
    public void bajaClaseAlumno(ClaseAlumno claseAlumno) throws Notificaciones{
        claseAlumno.setEstado("INACTIVO");
        miPersistencia.persistirInstancia(claseAlumno);
        this.listaClaseAlumno = miPersistencia.getClasesAlumno();
    }
    
    public List<ClaseAlumno> getListaClaseAlumno() throws Notificaciones{
        listaClaseAlumno = miPersistencia.getClasesAlumno();
        return this.listaClaseAlumno;
    }
    
}
