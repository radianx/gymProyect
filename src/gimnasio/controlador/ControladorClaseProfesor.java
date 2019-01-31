/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.ClaseProfesor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorClaseProfesor {

    ControladorPersistencia persistencia;
    List<ClaseProfesor> listaClaseProfesor;
    
    public ControladorClaseProfesor(ControladorPersistencia miPersistencia) throws Notificaciones {
        this.persistencia = miPersistencia;
        listaClaseProfesor = persistencia.getClasesProfesores();
    }

    public void altaClaseProfesor(ClaseProfesor unaClaseProfesor) throws Notificaciones {
//        int i = listaClaseProfesor.lastIndexOf(unaClaseProfesor);
//        if(i>=0){
//            throw new Notificaciones("La clase " + unaClaseProfesor.getClase() + " ya se encuentra asignada al profesor " + unaClaseProfesor.getProfesor());
//        }else{
            persistencia.persistirInstancia(unaClaseProfesor);
            persistencia.actualizarInstancias();
            this.listaClaseProfesor.add(unaClaseProfesor);
//        }        
    }
    
    public void bajaClaseProfesor(ClaseProfesor unaClase) throws Notificaciones {
        listaClaseProfesor.remove(unaClase);
        unaClase.setEstado("INACTIVO");
        persistencia.persistirInstancia(unaClase);
    }

    public void actualizarClaseProfesor(ClaseProfesor claseSeleccionada) throws Notificaciones {
        persistencia.persistirInstancia(claseSeleccionada);   
        listaClaseProfesor = persistencia.getClasesProfesores();
    }
    
    public List<ClaseProfesor> getListaClaseProfesor() throws Notificaciones{
        listaClaseProfesor = persistencia.getClasesProfesores();
        List<ClaseProfesor> retorno = new ArrayList<>();
        listaClaseProfesor.stream().filter((claseProfe) -> (claseProfe.getEstado().equalsIgnoreCase("ACTIVO"))).forEachOrdered((claseProfe) -> {
            retorno.add(claseProfe);
        });
        return retorno;
    }

    public List<ClaseProfesor> dameClaseProfesor(String text) throws Notificaciones {
        List<ClaseProfesor> clases = buscarClaseProfe(text);
        return clases;
    }

    private List<ClaseProfesor> buscarClaseProfe(String text) throws Notificaciones {
        List<ClaseProfesor> retorno = new ArrayList<>();
        listaClaseProfesor = persistencia.getClasesProfesores();
        persistencia.actualizarInstancias();
        System.out.println("\nControladorClaseProfesor buscarClaseProfe");
        for(ClaseProfesor unaClase:listaClaseProfesor){
            System.out.println("for unaClase: "+unaClase);
            if(unaClase.toString().equalsIgnoreCase(text) && unaClase.getEstado().equalsIgnoreCase("ACTIVO")){
                retorno.add(unaClase);
                System.out.println("Agregando.");
            }
        }
        return retorno;
    }

}
