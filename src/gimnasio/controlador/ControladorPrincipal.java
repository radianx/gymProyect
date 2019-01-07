
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
    
    private ModeloPrincipal miModeloPrincipal;
    
    
//  <-----------------CONSTRUCTOR DEL CONTROLADOR PRINCIPAL------------------->

    public ControladorPrincipal(ModeloPrincipal ModeloPrincipal) throws Notificaciones{
        
        this.miModeloPrincipal = ModeloPrincipal;
        this.miPersistencia = new ControladorPersistencia();
        
        this.controladorUsuario = new ControladorUsuario(this.miPersistencia);
        this.controladorAlumno = new ControladorAlumno(this.miPersistencia);
        this.controladorObraSocial = new ControladorObraSocial(this.miPersistencia);
//        this.controladorProfesor = new ControladorProfesor(this.miPersistencia, this.miModeloPrincipal.getListaProfesores());
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
        String estado = "ACTIVO";
        Profesor unProfesor = controladorProfesor.buscarProfesor(profesor.getNombreprofesor(), profesor.getApellidoprofesor());
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



    public List<Alumno> bajaProfesor(int idProfesor) throws Notificaciones {
        Profesor unProfesor;
        List<Alumno> listaAlumnosSinClases = new ArrayList<>();
        String estado = "INACTIVO";
        List<Clase> listaClases = buscarClasesPorProfesor(idProfesor);
        if (listaClases != null) {
            for (Clase miClase : listaClases) {
                listaAlumnosSinClases = bajaClase(miClase);

            }
        }
        unProfesor = buscarProfesor(idProfesor);
        unProfesor.setEstado(estado);
        this.miPersistencia.persistirInstancia(unProfesor);
        if (listaAlumnosSinClases.isEmpty()) {
            listaAlumnosSinClases = null;
        }
        return listaAlumnosSinClases;
    }

//  <----------------------------------------------------ABM MODALIDADES----------------------------------------------------> 
    public void altaModalidad(Modalidad modalidad) throws Notificaciones {
        Modalidad unaModalidad = buscarModalidad(modalidad.getNombremodalidad());
        String estado = "ACTIVO";
        if (unaModalidad != null) {
            unaModalidad.setEstado(estado);
            unaModalidad.setNombremodalidad(modalidad.getNombremodalidad());
            unaModalidad.setDescripcionmodalidad(modalidad.getDescripcionmodalidad());
            this.miPersistencia.persistirInstancia(unaModalidad);
            this.listaModalidades = miPersistencia.getModalidades();
        }else {
            this.miPersistencia.persistirInstancia(modalidad);
            this.miPersistencia.getModalidades();
        }
    }

public void bajaModalidad(int idModalidad) throws Notificaciones{

} 

//  <----------------------------------------------------ABM PROFESORES POR MODALIDADES----------------------------------------------------> 
    public void altaProfesorPorModalidad(Modalidad unaModalidad, Profesor unProfesor, Double precioHora) throws Notificaciones {
        String estado = "ACTIVO";
        List<Modalidad> modalidadesDelProfesor = buscarModalidadDeProfesor(unProfesor.getIdprofesor());
        if (modalidadesDelProfesor != null) {
            for (Profesormodalidad miProfesorModalidad : this.listaProfesorModalidad) {
                if (miProfesorModalidad.getModalidad() == unaModalidad && miProfesorModalidad.getProfesor() == unProfesor) {
                    miProfesorModalidad.setEstado(estado);
                    miProfesorModalidad.setPreciohora(precioHora);
                    this.miPersistencia.persistirInstancia(miProfesorModalidad);
                    this.listaProfesorModalidad = miPersistencia.getProfesorModalidad();
                    break;
                }
            }

        } else {
            Profesormodalidad unProfesorModalidad = new Profesormodalidad(unaModalidad, unProfesor, precioHora, estado);
            this.miPersistencia.persistirInstancia(unProfesorModalidad);
            this.listaProfesorModalidad = miPersistencia.getProfesorModalidad();
        }
    }


public void bajaProfesorDeModalidad(Profesormodalidad unProfesorModalidad) throws Notificaciones{
    String estado = "INACTIVO";
    for (Profesormodalidad miProfesorModalidad : this.listaProfesorModalidad) {
       if(miProfesorModalidad.getProfesor()==unProfesorModalidad.getProfesor()&&miProfesorModalidad.getModalidad()==unProfesorModalidad.getModalidad()){
           miProfesorModalidad.setEstado(estado);
           break;
       }
    }
}

//  <----------------------------------------------------ABM CLASES ----------------------------------------------------> 

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
   




public List<Alumno> bajaClase(Clase unaClase) throws Notificaciones{
    List<Alumno> alumnosSinClasse = new ArrayList<>();
    
    return alumnosSinClasse;
}



//  <------------------------------------------------ABM CLASES ALUMNO------------------------------------------------> 


public void altaAlumnoClase(Alumno unAlumno, Clase unaClase, int cantidadClases) throws Notificaciones{
    String estado = "ACTIVO";
    if(buscarAlumnoClase(unAlumno)!=null){
        throw new Notificaciones("El alumno ya se encuentra inscripto en la clase");
    }else{
        ClaseAlumno unaClaseAlumno = new ClaseAlumno(unAlumno, unaClase, cantidadClases, estado);
    }
}

public void bajaClaseAlumno(ClaseAlumno unaClaseAlumno) throws Notificaciones{
    try {
       unaClaseAlumno.setCantidadclases(0);
       this.listaClaseAlumno.remove(unaClaseAlumno);
       this.miPersistencia.persistirInstancia(unaClaseAlumno);
        bajaCuota(unaClaseAlumno);
    } catch (Exception e) {
        throw new Notificaciones(e.getMessage());
    }
}


//  <----------------------------------------------------ABM CUOTAS ----------------------------------------------------> 
public List<Cuota> altaCuota(Alumno unAlumno, Clase unaClase, Double precio, Date altaCuota, Date vencimientoCuota) throws Notificaciones{
    List<Cuota> cuotasImpagas = new ArrayList<>();
    for(Cuota unaCuota : this.listaCuotas){
        if(buscarCuotasImpagas(unAlumno)!=null && buscarCuotasConSaldo(unAlumno)!=null){
            String estado = "GENERADA";
            Cuota nuevaCuota = new Cuota(unAlumno, unaClase, precio, estado, altaCuota, vencimientoCuota);
            this.listaCuotas.add(unaCuota);
            this.miPersistencia.persistirInstancia(nuevaCuota);
            break;
        }else{
            cuotasImpagas.add(unaCuota);
        }
    }
    if(cuotasImpagas.isEmpty()){
        cuotasImpagas = null;
    }
    return cuotasImpagas;
}


public void altaCuotaConDeuda(Alumno unAlumno, Clase unaClase, Double precio, Date altaCuota, Date vencimientoCuota) throws Notificaciones{
    String estado = "GENERADA";
    Cuota nuevaCuota = new Cuota(unAlumno, unaClase, precio, estado, altaCuota, vencimientoCuota);
    this.listaCuotas.add(nuevaCuota);
    this.miPersistencia.persistirInstancia(nuevaCuota);
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
        String estado = "ACTIVO";
        Cargo unCargo = buscarCargo(cargo.getNombrecargo());
        if (unCargo != null) {
            if (unCargo.getEstado().equalsIgnoreCase(estado)) {
                unCargo.setDescripcioncargo(cargo.getDescripcioncargo());
                this.miPersistencia.persistirInstancia(unCargo);
                this.listaCargos = miPersistencia.getCargos();
            }
            } else {
                Cargo miCargo = new Cargo(cargo.getNombrecargo(),cargo.getDescripcioncargo(),estado);
                this.listaCargos.add(miCargo);
                this.miPersistencia.persistirInstancia(miCargo);
            }
        }


public void bajaCargo(Cargo unCargo){
    
}
//  <----------------------------------------------------ABM SECTORES----------------------------------------------------> 
    
public void altaSector(Sector sector) throws Notificaciones{
    String estado = "ACTIVO";
    for(Sector miSector : this.listaSectores){
        if(miSector.getNombresector().equalsIgnoreCase(sector.getNombresector())){
            if(miSector.getEstado().equalsIgnoreCase(estado)){
                this.miPersistencia.persistirInstancia(sector);
            }
        }
    }
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
        return this.miModeloPrincipal.getListaClases();
    }

    public List<Cargo> getListaCargos() {
        return this.miModeloPrincipal.getListaCargos();
    }

    public List<Modalidad> getListaModalidades() {
        return this.miModeloPrincipal.getListaModalidades();
    }


    public List<Profesor> getListaProfesores() {
        return this.miModeloPrincipal.getListaProfesores();
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

    public List<ClaseProfesor> getListaClaseProfesor(){
        return this.miModeloPrincipal.getListaClaseProfesor();
    }
    
    public List<AperturaCajaDiaria> getListaAperturaCajaDiaria(){
        return this.miModeloPrincipal.getListaAperturaCajaDiaria();
    }
    
    public List<SectorClase> getListaSectorClase(){
        return this.miModeloPrincipal.getListaSectorClase();
    }
    
    
}
