/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.Alumno;
import gimnasio.modelo.Cargo;
import gimnasio.modelo.Clase;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Obrasocial;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import gimnasio.modelo.Sector;
import gimnasio.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorBusquedas {
    
    
    
    
    
    public Alumno buscarAlumnoClase(Alumno elAlumno){
        Alumno unAlumno = null;
        for(Alumno miAlumno : this.listaAlumnos){
            if (miAlumno.getIdalumno() == elAlumno.getIdalumno()){
                unAlumno = miAlumno;
                break;
            }
        }
        return unAlumno;
    }
    
    public Profesor buscarProfesor(String nombreProfesor){
        Profesor unProfesor = null;
        for(Profesor miProfesor : this.listaProfesores){
            if(miProfesor.getNombreprofesor().equalsIgnoreCase(nombreProfesor)){
                unProfesor = miProfesor;
            }
            break;
        }
        return unProfesor;
    }
    
    public Profesor buscarProfesor(int idProfesor){
        Profesor unProfesor = null;
        for(Profesor miProfesor : this.listaProfesores){
            if(miProfesor.getIdprofesor()==idProfesor){
                unProfesor = miProfesor;
            }
            break;
        }
        return unProfesor;
    }
    
    public Profesor buscarProfesor(String nombreProfesor, String apellidoProfesor){
        Profesor unProfesor = null;
        for(Profesor miProfesor : this.listaProfesores){
            if(miProfesor.getNombreprofesor().equalsIgnoreCase(nombreProfesor)){
                unProfesor = miProfesor;
            }
            break;
        }
        return unProfesor;
    }
    
    
    public Sector buscarSector(String nombreSector){
        Sector unSector = null;
        for(Sector miSector : this.listaSectores){
            if(miSector.getNombresector().equalsIgnoreCase(nombreSector)){
                unSector = miSector;
            }
            break;
        }
        return unSector;
    }
    
    public Cargo buscarCargo(String nombreCargo){
        Cargo unCargo = null;
        for(Cargo miCargo : this.listaCargos){
            if(miCargo.getNombrecargo().equalsIgnoreCase(nombreCargo)){
                unCargo = miCargo;
                break;
            }
        }
        return unCargo;
    }
    
    

    public Obrasocial buscarObraSocial(String nombreObrasocial){
        Obrasocial unaObraSocial = null;
        for(Obrasocial miObraSocial : this.listaObraSociales){
            if(miObraSocial.getNombreobrasocial()==nombreObrasocial){
                unaObraSocial = miObraSocial;
                break;
            }
        }
        return unaObraSocial;
    }

    public List<Cuota> buscarCuotasImpagas(){
        List<Cuota> cuotasImpagas = new ArrayList<>();
        for(Cuota miCuota :this.listaCuotas){
            if(miCuota.getEstado().equalsIgnoreCase("ADEUDA")){
                cuotasImpagas.add(miCuota);
            }
        }
        return cuotasImpagas;
    }
    
    public List<Cuota> buscarCuotasImpagas(Alumno unAlumno){
        List<Cuota> cuotasImpagas = new ArrayList<>();
        for(Cuota miCuota :this.listaCuotas){
            if(miCuota.getEstado().equalsIgnoreCase("GENERADA")&&miCuota.getAlumno().getIdalumno()==unAlumno.getIdalumno()){
                cuotasImpagas.add(miCuota);
            }
        }
        return cuotasImpagas;
    }
    
    
    public List<Cuota> buscarCuotasConSaldo(){
        List<Cuota> cuotasConSaldo = new ArrayList<>();
        for(Cuota miCuota :this.listaCuotas){
            if(miCuota.getEstado().equalsIgnoreCase("SALDO")){
                cuotasConSaldo.add(miCuota);
            }
        }
        return cuotasConSaldo;
    }
    
    public List<Cuota> buscarCuotasConSaldo(Alumno unAlumno){
        List<Cuota> cuotasConSaldo = new ArrayList<>();
        for(Cuota miCuota :this.listaCuotas){
            if(miCuota.getEstado().equalsIgnoreCase("SALDO")&& miCuota.getAlumno().getIdalumno()== unAlumno.getIdalumno()){
                cuotasConSaldo.add(miCuota);
            }
        }
        return cuotasConSaldo;
    }
    
    
    
    public Modalidad buscarModalidad (String nombreModalidad){
        Modalidad unaModalidad = null;
        for(Modalidad miModalidad : this.listaModalidades){
            if(miModalidad.getNombremodalidad().equalsIgnoreCase(nombreModalidad)){
                unaModalidad = miModalidad;
                break;
            }
        }
        return unaModalidad;
    }
    
    public Modalidad buscarModalidad(int idModalidad){
        Modalidad unaModalidad = null;
        for(Modalidad miModalidad: this.listaModalidades){
            if(miModalidad.getIdmodalidad()== idModalidad){
                unaModalidad = miModalidad;
                break;
            }
        }
        return unaModalidad;
    }
    
    public List<Modalidad> buscarModalidadDeProfesor(int idProfesor){
        List<Modalidad> modalidadesDelProfesor = new ArrayList<>();
        for(Profesormodalidad miProfesorModalidad : this.listaProfesorModalidad){
            if(miProfesorModalidad.getProfesor().getIdprofesor() == idProfesor){
                modalidadesDelProfesor.add(miProfesorModalidad.getModalidad());
            }
        }
        return modalidadesDelProfesor;
    }
    
     public ControladorHuella getMiLector() {
        return miLector;
    }

    public void setMiLector(ControladorHuella miLector) {
        this.miLector = miLector;
    }
    
}
