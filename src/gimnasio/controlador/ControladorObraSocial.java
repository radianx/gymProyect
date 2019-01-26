/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Obrasocial;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorObraSocial {

    private ControladorPersistencia miPersistencia;
    private List<Obrasocial> listaObraSociales;
    
    public ControladorObraSocial(ControladorPersistencia persistencia) throws Notificaciones{
        this.miPersistencia = persistencia;
        this.listaObraSociales = miPersistencia.getObraSociales();
    }
    
    public void altaObraSocial(Obrasocial obrasocial) throws Notificaciones {
        String estado = "ACTIVO";
        Obrasocial unaObrasocial = buscarObraSocial(obrasocial.getNombreobrasocial());
        if (unaObrasocial != null) {
            unaObrasocial.setNombreobrasocial(obrasocial.getNombreobrasocial());
            unaObrasocial.setContacto(obrasocial.getContacto());
            unaObrasocial.setEstado(estado);
            this.miPersistencia.persistirInstancia(unaObrasocial);
            this.listaObraSociales = miPersistencia.getObraSociales();
        } else {
            obrasocial.setEstado(estado);
            this.miPersistencia.persistirInstancia(obrasocial);
            this.listaObraSociales.add(obrasocial);
        }
    }

    public Obrasocial buscarObraSocial(String nombreObrasocial){
        Obrasocial unaObraSocial = null;
        for(Obrasocial miObraSocial : this.listaObraSociales){
            if(miObraSocial.getNombreobrasocial().equalsIgnoreCase(nombreObrasocial)){
                unaObraSocial = miObraSocial;
                break;
            }
        }
        return unaObraSocial;
    }

    public List<Obrasocial> getObrasSociales() throws Notificaciones {
        listaObraSociales = miPersistencia.getObraSociales();
        return this.listaObraSociales;
    }

    public void bajaObraSocial(String nombreObra) throws Notificaciones {
        Obrasocial miObraSocial = this.buscarObraSocial(nombreObra);
        String estado = "INACTIVO";
        miObraSocial.setEstado(estado);
        int i = this.listaObraSociales.lastIndexOf(miObraSocial);
        if(i != -1){
            this.listaObraSociales.set(i, miObraSocial);
        }else{
            throw new Notificaciones("Lista de Obras Sociales no actualizada");
        }

        miPersistencia.persistirInstancia(miObraSocial);
    }
}
