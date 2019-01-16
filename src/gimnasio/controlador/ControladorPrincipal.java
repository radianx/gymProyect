
package gimnasio.controlador;

import com.sun.org.apache.xpath.internal.operations.Minus;
import gimnasio.modelo.*;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Family
 */
public class ControladorPrincipal {
    
    
// private LectorHuella miLector = new LectorHuella();


//  <-----------------CONTROLADORES EXTRA-------------------------->
    private ControladorRele miRele = new ControladorRele();
    private ControladorPersistencia miPersistencia;    
    
    private ControladorAlumno controladorAlumno;
    private ControladorBusquedas controladorBusquedas;
    private ControladorCajaDiaria controladorCajaDiaria;
    private ControladorCargo controladorCargo;
    private ControladorCargoPersonal controladorCargoPersonal;
    private ControladorClase controladorClase;
    private ControladorClaseAlumno controladorClaseAlumno;
    private ControladorClaseProfesor controladorClaseProfesor;
    private ControladorCuota controladorCuota;
    private ControladorModalidad controladorModalidad;
    private ControladorModulo controladorModulo;
    private ControladorObraSocial controladorObraSocial;
    private ControladorPago controladorPago;
    private ControladorPersonal controladorPersonal;
    private ControladorProfesor controladorProfesor;
    private ControladorProfesorModalidad controladorProfesorModalidad;
    private ControladorSector controladorSector;
    private ControladorSectorClase controladorSectoClase;
    private ControladorUsuario controladorUsuario;
    private ControladorUsuarioModulo controladorUsuarioModulo;
    private ControladorHorarioProfesor controladorHorarioProfesor;
    private ControladorHorarioAlumno controladorHorarioAlumno;
    
    private ModeloPrincipal miModeloPrincipal;
    
    
//  <-----------------CONSTRUCTOR DEL CONTROLADOR PRINCIPAL------------------->

    public ControladorPrincipal(ModeloPrincipal ModeloPrincipal) throws Notificaciones{
        
        this.miModeloPrincipal = ModeloPrincipal;
        this.miPersistencia = new ControladorPersistencia();
        
        this.controladorUsuario = new ControladorUsuario(this.miPersistencia);
        this.controladorAlumno = new ControladorAlumno(this.miPersistencia);
        this.controladorObraSocial = new ControladorObraSocial(this.miPersistencia);
        this.controladorProfesor = new ControladorProfesor(this.miPersistencia);
        this.controladorClase = new ControladorClase(this.miPersistencia);
        this.controladorClaseProfesor = new ControladorClaseProfesor(this.miPersistencia);
        this.controladorModalidad = new ControladorModalidad(this.miPersistencia);
        this.controladorProfesorModalidad =  new ControladorProfesorModalidad(this.miPersistencia);
        this.controladorClaseAlumno = new ControladorClaseAlumno(this.miPersistencia);
        this.controladorCuota = new ControladorCuota(this.miPersistencia);
        this.controladorHorarioProfesor = new ControladorHorarioProfesor(this.miPersistencia);
        this.controladorHorarioAlumno = new ControladorHorarioAlumno(this.miPersistencia);
    }      
    
    public ControladorPersistencia getMiPersistencia() {
        return miPersistencia;
    }

    public void setMiPersistencia(ControladorPersistencia miPersistencia) {
        this.miPersistencia = miPersistencia;
    }
  
    
    /*
        
        
        try {
            this.listaAlumnos = miPersistencia.getAlumnos();
            this.listaAsistenciaAlumno = miPersistencia.getAsistenciaAlumno();
            this.listaAsistenciaProfesor = miPersistencia.getAsistenciaProfesor();
            this.listaCargos = miPersistencia.getCargos();
            this.listaClases = miPersistencia.getClases();
//            this.listaClaseAlumno = miPersistencia.getClaseAlumno();
            this.listaCuotas = miPersistencia.getCuotas();
            this.listaCobroCuota = miPersistencia.getCobroCuota();
            this.listaModalidades = miPersistencia.getModalidades();
            this.listaModulos = miPersistencia.getModulos();
            this.listaPagoProfesores = miPersistencia.getPagoProfesores();
            this.listaProfesores = miPersistencia.getProfesores();
            this.listaProfesorModalidad = miPersistencia.getProfesorModalidad();
            this.listaSaldoCuota = miPersistencia.getSaldoCuota();
            this.listaSaldoPagoProfesores = miPersistencia.getSaldoPagoProfesores();
            this.listaSectores = miPersistencia.getSectores();
            
            this.listaObraSociales = miPersistencia.getObraSociales();
//            this.listaContactos = miPersistencia.getContactos();
            
            
        } catch (Notificaciones ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    
    public static Date parseDate (String date){
        try {
            return new SimpleDateFormat("dd-mm-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    

    
    
//  <-------------------------------------------------------------------------------------------------------------------------------------> 
//          <---------------------------------------------------------------ABMs----------------------------------------------------> 
//  <------------------------------------------------------------------------------------------------------------------------------------->
     
    
     
     
//  <----------------------------------------------------ABM USUARIOS----------------------------------------------------> 
    public List<Usuario> getListaUsuarios(){
        return this.controladorUsuario.getListaUsuarios();
    }    
    
    public void altaUsuario(Usuario unUsuario) throws Notificaciones{
        this.controladorUsuario.altaUsuario(unUsuario);
    }
    
    public void bajaUsuario(int idUsuario) throws Notificaciones{
        this.controladorUsuario.bajaUsuario(idUsuario);
    }
    
//  <----------------------------------------------------ABM ALUMNOS----------------------------------------------------> 
    
    public List<Alumno> getListaAlumnos(){
        return this.controladorAlumno.getListaAlumnos();
    }
    
    public void bajaAlumno(int idAlumno) throws Notificaciones{
        this.controladorAlumno.bajaAlumno(idAlumno);
    }
    
    public void altaAlumno(Alumno unAlumno) throws Notificaciones{
        this.controladorAlumno.altaAlumno(unAlumno);
    }
    
 //  <----------------------------------------------------ABM PROFESORES----------------------------------------------------> 
    public void altaProfesor(Profesor profesor) throws Notificaciones {
        this.controladorProfesor.altaProfesor(profesor);
    }

    /**
    * Retorna una lista de Alumnos que se quedaron sin clases
    * debido a que el profesor fue dado de Baja,
    * esta baja se especifica en el controlador Principal y 
    * no en el controlador de profesores porque necesita buscar
    * clases por profesor.
    * 
    * @param idProfesor el entero que es ID de profesor
    * @return listaAlumnosSinClases puede ser null
    * @throws Notificaciones
    */
//    public List<Alumno> bajaProfesor(int idProfesor) throws Notificaciones {
//        Profesor unProfesor;
//        List<Alumno> listaAlumnosSinClases = new ArrayList<>();
//        String estado = "INACTIVO";
//        List<Clase> listaClases = buscarClasesPorProfesor(idProfesor);
//        if (listaClases != null) {
//            for (Clase miClase : listaClases) {
//                listaAlumnosSinClases = bajaClase(miClase);
//
//            }
//        }
//        unProfesor = this.controladorProfesor.buscarProfesor(idProfesor);
//        unProfesor.setEstado(estado);
//        this.miPersistencia.persistirInstancia(unProfesor);
//        if (listaAlumnosSinClases.isEmpty()) {
//            listaAlumnosSinClases = null;
//        }
//        return listaAlumnosSinClases;
//    }
    
    public Profesor buscarProfesor(String nombreProfesor, String apellidoProfesor){
        return this.controladorProfesor.buscarProfesor(nombreProfesor, apellidoProfesor);
    }
    
    public List<Clase> buscarClasesPorProfesor(int idProfesor) throws Notificaciones{
        return this.controladorProfesor.buscarClasesPorProfesor(idProfesor);
    }
    
//  <----------------------------------------------------ABM MODALIDADES----------------------------------------------------> 
    public void altaModalidad(Modalidad modalidad) throws Notificaciones {
        this.controladorModalidad.altaModalidad(modalidad);
    }

    public void bajaModalidad(int idModalidad) throws Notificaciones {
        this.controladorModalidad.bajaModalidad(idModalidad);
    }

//  <----------------------------------------------------ABM PROFESORES POR MODALIDADES----------------------------------------------------> 
    public void altaProfesorPorModalidad(Profesormodalidad profesorModalidad) throws Notificaciones {
        this.controladorProfesorModalidad.altaProfesorModalidad(profesorModalidad);
    }

    public void bajaProfesorDeModalidad(Profesormodalidad unProfesorModalidad) throws Notificaciones {
        this.controladorProfesorModalidad.bajaProfesorModalidad(unProfesorModalidad);
    }

//  <----------------------------------------------------ABM CLASES ----------------------------------------------------> 

    public void altaClase(Clase clase) throws Notificaciones {
        this.controladorClase.altaClase(clase);
    }
   

    public void bajaClase(Clase unaClase) throws Notificaciones {
        this.controladorClase.bajaClase(unaClase);
    }



//  <------------------------------------------------ABM CLASES ALUMNO------------------------------------------------> 


public void altaClaseAlumno(ClaseAlumno claseAlumno) throws Notificaciones{
    this.controladorClaseAlumno.altaClaseAlumno(claseAlumno);
}

public void bajaClaseAlumno(ClaseAlumno unaClaseAlumno) throws Notificaciones{
//    try {
//       unaClaseAlumno.setCantidadclases(0);
//       this.listaClaseAlumno.remove(unaClaseAlumno);
//       this.miPersistencia.persistirInstancia(unaClaseAlumno);
//        bajaCuota(unaClaseAlumno);
//    } catch (Exception e) {
//        throw new Notificaciones(e.getMessage());
//    }
}


//  <----------------------------------------------------ABM CUOTAS ----------------------------------------------------> 
public void altaCuota(Cuota unaCuota) throws Notificaciones{
    this.controladorCuota.altaCuota(unaCuota);
}


public void altaCuotaConDeuda(Alumno unAlumno, Clase unaClase, Double precio, Date altaCuota, Date vencimientoCuota) throws Notificaciones{
//    String estado = "GENERADA";
//    Cuota nuevaCuota = new Cuota(unAlumno, unaClase, precio, estado, altaCuota, vencimientoCuota);
//    this.listaCuotas.add(nuevaCuota);
//    this.miPersistencia.persistirInstancia(nuevaCuota);
}

public void bajaCuota(ClaseAlumno unaClaseAlumno, Date altaCuota){
    String estado = "BAJA";
    Date vencimiento = parseDate("11/09/2001");
}




//  <----------------------------------------------------ABM COBROS----------------------------------------------------> 

//  <----------------------------------------------------ABM PAGOS----------------------------------------------------> 


//  <----------------------------------------------------ABM ASISTENCIAS----------------------------------------------------> 

//  <----------------------------------------------------ABM CARGOS----------------------------------------------------> 
    public void altaCargo(Cargo cargo) throws Notificaciones {
//        String estado = "ACTIVO";
//        Cargo unCargo = buscarCargo(cargo.getNombrecargo());
//        if (unCargo != null) {
//            if (unCargo.getEstado().equalsIgnoreCase(estado)) {
//                unCargo.setDescripcioncargo(cargo.getDescripcioncargo());
//                this.miPersistencia.persistirInstancia(unCargo);
//                this.listaCargos = miPersistencia.getCargos();
//            }
//            } else {
//                Cargo miCargo = new Cargo(cargo.getNombrecargo(),cargo.getDescripcioncargo(),estado);
//                this.listaCargos.add(miCargo);
//                this.miPersistencia.persistirInstancia(miCargo);
//            }
        }


public void bajaCargo(Cargo unCargo){
    
}
//  <----------------------------------------------------ABM SECTORES----------------------------------------------------> 
    
public void altaSector(Sector sector) throws Notificaciones{
//    String estado = "ACTIVO";
//    for(Sector miSector : this.listaSectores){
//        if(miSector.getNombresector().equalsIgnoreCase(sector.getNombresector())){
//            if(miSector.getEstado().equalsIgnoreCase(estado)){
//                this.miPersistencia.persistirInstancia(sector);
//            }
//        }
//    }
}
//  <----------------------------------------------------ABM CONTACTO----------------------------------------------------> 

public void altaContacto (Contacto unContacto) throws Notificaciones{
    unContacto.setEstado("ACTIVO");
    this.miPersistencia.persistirInstancia(unContacto);
//    this.listaContactos.add(unContacto);
//    this.miPersistencia.persistirInstancia(unContacto);
}
//  <----------------------------------------------------ABM ASISTENCIA PROFESOR----------------------------------------------------> 

//  <----------------------------------------------------ABM ASISTENCIA PERSONAL----------------------------------------------------> 

//  <----------------------------------------------------ABM CAJA DIARIA----------------------------------------------------> 
    

//  <----------------------------------------------------ABM PERSONAL----------------------------------------------------> 
public void bajaPersonal(){
    
}

//  <----------------------------------------------------ABM OBRASOCIAL----------------------------------------------------> 
public void altaObraSocial(Obrasocial obrasocial) throws Notificaciones{
    this.controladorObraSocial.altaObraSocial(obrasocial);
}

public List<Obrasocial> getListaObrasSociales(){
    return this.controladorObraSocial.getObrasSociales();
}

public void bajaObraSocial(String nombreObra) throws Notificaciones{
    this.controladorObraSocial.bajaObraSocial(nombreObra);
}

//  <------GETTERS DE LISTAS---------------------------------------------------> 

    public List<Clase> getListaClases() {
        return this.controladorClase.getListaClases();
    }

    public List<Cargo> getListaCargos() {
        return this.miModeloPrincipal.getListaCargos();
    }

    public List<Modalidad> getListaModalidades() throws Notificaciones{
        return this.controladorModalidad.getListaModalidades();
    }


    public List<Profesor> getListaProfesores() throws Notificaciones {
        return this.controladorProfesor.getListaProfesores();
    }

    public List<Sector> getListaSectores() {
        return this.miModeloPrincipal.getListaSectores();
    }

    public List<ClaseAlumno> getListaClasesAlumnos() {
        return this.miModeloPrincipal.getListaClaseAlumno();
    }

    public List<Profesormodalidad> getListaProfesorModalidades() {
        return this.miModeloPrincipal.getListaProfesorModalidad();
    }

    public List<AsistenciaAlumno> getListaAsistenciaAlumno() {
        return this.miModeloPrincipal.getListaAsistenciaAlumno();
    }

    public List<Cuota> getListaCuotas() {
        return this.miModeloPrincipal.getListaCuotas();
    }

    public List<CobroCuota> getListaCobroCuota() {
        return this.miModeloPrincipal.getListaCobroCuota();
    }

    public List<SaldoCuota> getListaSaldoCuota() {
        return this.miModeloPrincipal.getListaSaldoCuota();
    }

    public List<PagoProfesor> getListaPagoProfesores() {
        return this.miModeloPrincipal.getListaPagoProfesores();
        
    }
    
    public List<Saldopagoprofesor> getListaSaldoPagoProfesores() {
        return this.miModeloPrincipal.getListaSaldoPagoProfesores();
    }
    
    public List<Modulo> getListaModulos(){
        return this.miModeloPrincipal.getListaModulos();
    }
    
    public List<Personal> getListaPersonal(){
        return this.miModeloPrincipal.getListaPersonal();
    }
    
    public List<CargoPersonal> getListaCargoPersonal(){
        return this.miModeloPrincipal.getListaCargoPersonal();
    }

    public List<ClaseProfesor> getListaClaseProfesor() throws Notificaciones{
        return this.controladorClaseProfesor.getListaClaseProfesor();
    }
    
    public List<AperturaCajaDiaria> getListaAperturaCajaDiaria(){
        return this.miModeloPrincipal.getListaAperturaCajaDiaria();
    }
    
    public List<SectorClase> getListaSectorClase(){
        return this.miModeloPrincipal.getListaSectorClase();
    }

    public void altaClaseProfesor(ClaseProfesor unaClaseProfesor) throws Notificaciones {
        controladorClaseProfesor.altaClaseProfesor(unaClaseProfesor);
    }

    public void bajaClaseProfesor(ClaseProfesor unaClase) throws Notificaciones {
        controladorClaseProfesor.bajaClaseProfesor(unaClase);
    }

    public List<Profesormodalidad> getListaProfeModalidades() {
        return controladorProfesorModalidad.getListaProfesorModalidad();
    }

    public void actualizarClaseProfesor(ClaseProfesor claseSeleccionada) throws Notificaciones {
        controladorClaseProfesor.actualizarClaseProfesor(claseSeleccionada);
    }

    public List<Cuota> getListaCuotasAVencer() {
        return this.controladorCuota.getListaCuotasAVencer();
    }

    public List<Cuota> getListaCuotasVencidas() {
        return this.controladorCuota.getListaCuotasVencidas();
    }

    public void altaHorarioProfesor(HorarioProfesor unHorario)throws Notificaciones {
        controladorHorarioProfesor.altaHorarioProfesor(unHorario);
    }

    public List<HorarioProfesor> getListaHorarios() throws Notificaciones {
        return controladorHorarioProfesor.getListaHorarios();
    }

    public void bajaHorarioProfesor(HorarioProfesor horarioProfesor) throws Notificaciones {
        controladorHorarioProfesor.bajaHorarioProfesor(horarioProfesor);
    }

    public void altaHorarioAlumno(HorarioAlumno unHorario) throws Notificaciones{
        controladorHorarioAlumno.altaHorarioAlumno(unHorario);
    }
    
    
}
