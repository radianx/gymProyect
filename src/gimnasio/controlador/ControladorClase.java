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
        if(listaClases.contains(clase)){
            int i = listaClases.indexOf(clase);
            unaClase = listaClases.get(i);
        }
        return unaClase;
    }

    public void altaClase(Clase clase) throws Notificaciones {
        clase.setEstado("ACTIVO");
        this.miPersistencia.persistirInstancia(clase);
        this.listaClases = miPersistencia.getClases();
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
   
    public List<Clase> getListaClases(){
        return this.listaClases;
    }

    public Clase dameClase(String valueOf) throws Notificaciones {
        listaClases = miPersistencia.getClases();
        Clase retorno = null;
        for(Clase unaClase:listaClases){
            if(unaClase.getDescripcionclase().equalsIgnoreCase(valueOf)){
                retorno = unaClase;
                break;
            }
        }
        if(retorno !=null){
            return retorno;
        }else{
            throw new Notificaciones("No se encontro la clase");
        }
    }
}
