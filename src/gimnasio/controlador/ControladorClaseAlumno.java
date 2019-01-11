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
        int i = listaClaseAlumno.lastIndexOf(claseAlumno);
        if(i>=0){
            throw new Notificaciones("Ya existe un registro de alumno en esa clase");
        }else{
            miPersistencia.persistirInstancia(claseAlumno);
            this.listaClaseAlumno.add(claseAlumno);
        }
    }
    
    public void bajaClaseAlumno(ClaseAlumno claseAlumno) throws Notificaciones{
        listaClaseAlumno.remove(claseAlumno);
        miPersistencia.persistirInstancia(claseAlumno);
    }
    
    public List<ClaseAlumno> getListaClaseAlumno() throws Notificaciones{
        listaClaseAlumno = miPersistencia.getClasesAlumno();
        return this.listaClaseAlumno;
    }
    
}
