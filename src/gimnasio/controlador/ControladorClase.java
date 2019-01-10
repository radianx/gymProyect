/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseProfesor;
import java.util.List;

/**
 *
 * @author Osoft
 */
public class ControladorClase {

    private ControladorPersistencia miPersistencia;
    private List<Clase> listaClases;
        
    public ControladorClase(ControladorPersistencia persistencia) throws Notificaciones{
        this.miPersistencia = persistencia;
        listaClases = miPersistencia.getClases();
    }
        
    public Clase buscarClase(Clase clase) {
        Clase unaClase = null;
        int i = listaClases.lastIndexOf(clase);
        listaClases.get(i);
        for (Clase miClase : this.listaClases) {
            if (miClase.getDescripcionclase().equalsIgnoreCase(clase.getDescripcionclase())) {
                unaClase = miClase;
                break;
            }
        }
        return unaClase;
    }

    public void altaClase(Clase clase) throws Notificaciones {
        String estado = "ACTIVO";
        Clase unaClase = buscarClase(clase);
        if (unaClase != null) {
            unaClase.setEstado(estado);
            unaClase.setAlumnosmaximo(clase.getAlumnosmaximo());
            unaClase.setTipoclase(clase.getTipoclase());
            this.miPersistencia.persistirInstancia(unaClase);
            this.listaClases = miPersistencia.getClases();
        } else {
            this.miPersistencia.persistirInstancia(clase);
            this.listaClases.add(clase);
        }
    }

    public void bajaClase(Clase unaClase) throws Notificaciones {
        String estado = "INACTIVO";
        Clase miClase = buscarClase(unaClase);
        if(unaClase != null){
            miClase.setEstado(estado);
            for(ClaseProfesor claseProfesor: miClase.getClaseProfesors()){
                claseProfesor.setEstado(estado);
                this.miPersistencia.persistirInstancia(claseProfesor);
            }
        }
        this.miPersistencia.persistirInstancia(miClase);
        int i = listaClases.lastIndexOf(unaClase);
        this.listaClases.remove(i);
    }
   
}
