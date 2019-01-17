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
import gimnasio.modelo.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorProfesor {

    private ControladorPersistencia miPersistencia;
    private List<Profesor> listaProfesores;
    
    public ControladorProfesor(ControladorPersistencia persistencia) throws Notificaciones{
        miPersistencia = persistencia;
        this.listaProfesores = miPersistencia.getProfesores();
    }
    
    public void altaProfesor(Profesor profesor) throws Notificaciones {
        String estado = "ACTIVO";
        Profesor unProfesor = buscarProfesor(profesor.getNombreprofesor(), profesor.getApellidoprofesor());
        if (unProfesor != null) {
            unProfesor.setEstado(estado);
            unProfesor.setNombreprofesor(profesor.getNombreprofesor());
            unProfesor.setApellidoprofesor(profesor.getApellidoprofesor());
            unProfesor.setPeso(profesor.getPeso());
            unProfesor.setAltura(profesor.getAltura());
            unProfesor.setFechanacimiento(profesor.getFechanacimiento());
            this.miPersistencia.persistirInstancia(unProfesor);
            this.listaProfesores = miPersistencia.getProfesores();
        } else {
            this.miPersistencia.persistirInstancia(profesor);
            this.miPersistencia.getProfesores();
        }
    }

    public Profesor buscarProfesor(String nombreProfesor, String apellidoProfesor){
        Profesor unProfesor = null;
        for(Profesor miProfesor : this.listaProfesores){
            if(miProfesor.getNombreprofesor().equalsIgnoreCase(nombreProfesor)){
                unProfesor = miProfesor;
                break;
            }
        }
        return unProfesor;
    }

    public Profesor buscarProfesor(int idProfesor) {
        Profesor unProfesor = null;
        for(Profesor miProfesor : this.listaProfesores){
            if(miProfesor.getIdprofesor()==idProfesor){
                unProfesor = miProfesor;
                break;
            }
        }
        return unProfesor;
    }

    public List<Clase> buscarClasesPorProfesor(int idProfesor) {
        Profesor unProfesor = buscarProfesor(idProfesor);
        List<Clase> clasesDeProfesor = new ArrayList<>();
        for(ClaseProfesor claseProfesor: unProfesor.getClaseProfesors()){
            clasesDeProfesor.add(claseProfesor.getClase());
        }
        return clasesDeProfesor;
    }

    public List<Profesor> getListaProfesores() throws Notificaciones {
        listaProfesores = miPersistencia.getProfesores();
        return this.listaProfesores;
    }

    public void bajaProfesor(int idProfesor) throws Notificaciones {
        Profesor unProfesor = buscarProfesor(idProfesor);
        unProfesor.setEstado("INACTIVO");
        miPersistencia.persistirInstancia(unProfesor);
    }
}
