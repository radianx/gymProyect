
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
    private Set<Alumno> listaAlumnos = new HashSet<>();
    private Set<ClaseAlumno> listaClaseAlumno = new HashSet<>();
    private Set<Profesor> listaProfesores = new HashSet<>();
    private Set<Modalidad> listaModalidades = new HashSet<>();
    private Set<Profesormodalidad> listaProfesorModalidad = new HashSet<>();
    private Set<Sector> listaSectores = new HashSet<>();
    private Set<Clase> listaClases = new HashSet<>();
    private Set<AsistenciaAlumno> listaAsistenciaAlumno = new HashSet<>();
    private Set<AsistenciaProfesor> listaAsistenciaProfesor = new HashSet<>();
    private Set<Cuota> listaCuotas = new HashSet<>();
    private Set<CobroCuota> listaCobroCuota = new HashSet<>();
    private Set<SaldoCuota> listaSaldoCuota = new HashSet<>();
    private Set<PagoProfesor> listaPagoProfesores = new HashSet<>();
    private Set<Saldopagoprofesor> listaSaldoPagoProfesores = new HashSet<>();
    private Set<Usuario> listaUsuarios = new HashSet<>();
    private Set<Modulo> listaModulos = new HashSet<>();
    private Set<Cargo> listaCargos = new HashSet<>();
    private Set<Obrasocial> listaObraSociales = new HashSet<>();
    private Set<Contacto> listaContactos = new HashSet<>();
    private Set<Personal> listaPersonal = new HashSet<>();
    private Set<CargoPersonal> listaCargoPersonal = new HashSet<>();
    private Set<ClaseProfesor> listaClaseProfesor = new HashSet<>();
    private Set<Saldopagoprofesor> listaSaldoPagoProfesor = new HashSet<>();
    private Set<SectorClase>  listaSectorClase = new HashSet<>();
    private Set<AperturaCajaDiaria> listaAperturaCajaDiaria = new HashSet<>();
    private Set<AsistenciaProfesor> listaAsistenciasProfesores = new HashSet<>();
    private Set<AsistenciaAlumno> listaAsistenciasAlumnos = new HashSet<>();
    private Set<Cajadiaria> listaCajasDiarias = new HashSet<>();
    private Set<Documentacion>
    
    
// private LectorHuella miLector = new LectorHuella();


//  <-----------------CONTROLADORES EXTRA-------------------------->
    private ControladorHuella miLector = new ControladorHuella();
    private ControladorRele miRele = new ControladorRele();
    private ControladorPersistencia miPersistencia = new ControladorPersistencia();

    
//  <-----------------CONSTRUCTOR DEL CONTROLADOR PRINCIPAL------------------->

    public ControladorPrincipal(){
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
            this.listaUsuarios = miPersistencia.getUsuarios();
            this.listaObraSociales = miPersistencia.getObraSociales();
//            this.listaContactos = miPersistencia.getContactos();
            
            
        } catch (Notificaciones ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static Date parseDate (String date){
        try {
            return new SimpleDateFormat("dd-mm-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    
    
//  <-----------------BUSQUEDAS-------------------> 
    
    public Usuario buscarUsuario(String nombreUsuario, String contrasenia){
        Usuario unUsuario = null;
        for(Usuario miUsuario : this.listaUsuarios){
            if(miUsuario.getNombreusuario().equalsIgnoreCase(nombreUsuario) && miUsuario.getContrasenia().equals(contrasenia)){
                unUsuario = miUsuario;
                break;
            }
        }
        return unUsuario;
    }
    
    public Usuario buscarUsuarioAlta(String nombreUsuario){
        Usuario unUsuario = new Usuario();
        unUsuario= null;
        for(Usuario miUsuario : this.listaUsuarios){
            if(miUsuario.getNombreusuario().equalsIgnoreCase(nombreUsuario)){
                unUsuario = miUsuario;
                break;
            }
        }
        return unUsuario;
    }
    
    
    public Usuario buscarUsuarioBaja(int idUsuario){
        Usuario unUsuario = null;
        for(Usuario miUsuario : this.listaUsuarios){
            if(miUsuario.getIdusuario()==idUsuario){
                unUsuario = miUsuario;
                break;
            }
        }
        return unUsuario;
    }
    
    
    public Alumno buscarAlumnoAlta(String nombrealu, String apellido) {
        Alumno unAlumno = null;
        for (Alumno miAlumno : this.listaAlumnos) {
            if (miAlumno.getNombrealumno().equalsIgnoreCase(nombrealu) && miAlumno.getApellidoalumno().equalsIgnoreCase(apellido)) {
                unAlumno = miAlumno;
                break;
            }
        }
        return unAlumno;
    }
    
    
    public Alumno buscarAlumnoBaja(int idAlumno) {
        Alumno unAlumno = null;
        for (Alumno miAlumno : this.listaAlumnos) {
            if (miAlumno.getIdalumno() == idAlumno) {
                unAlumno = miAlumno;
                break;
            }
        }
        return unAlumno;
    }
    
    
    
    public Clase buscarClase(Clase clase){
        Clase unaClase = null;
        for(Clase miClase : this.listaClases){
            if(miClase.getDescripcionclase().equalsIgnoreCase(clase.getDescripcionclase())){
                unaClase = miClase;
                break;
            }
        }
        return unaClase;
    }
    
    
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
    
    
    
    
    
//  <-------------------------------------------------------------------------------------------------------------------------------------> 
//          <---------------------------------------------------------------ABMs----------------------------------------------------> 
//  <------------------------------------------------------------------------------------------------------------------------------------->
     
    
     
     
//  <----------------------------------------------------ABM USUARIOS----------------------------------------------------> 
    public void altaUsuario(Usuario usuario) throws Notificaciones {
        Usuario unUsuario = buscarUsuarioAlta(usuario.getNombreusuario());
        String estado = "ACTIVO";
        unUsuario.setEstado(estado);
        if (unUsuario == null) {
            this.listaUsuarios.add(usuario);
            this.miPersistencia.persistirInstancia(usuario);
        } else {
            unUsuario.setEstado(estado);
            unUsuario.setContrasenia(usuario.getContrasenia());
            unUsuario.setPlanillahuellas(usuario.getPlanillahuellas());
            unUsuario.setFoto(usuario.getFoto());
            this.miPersistencia.persistirInstancia(unUsuario);
            this.listaUsuarios = miPersistencia.getUsuarios();
            
        }
    }
     
     
     public void bajaUsuario(int idUsuario) throws Notificaciones{
         Usuario unUsuario = buscarUsuarioBaja(idUsuario);
         String estado = "INACTIVO";
         unUsuario.setEstado(estado);
         this.miPersistencia.persistirInstancia(unUsuario);
     }
   
//  <----------------------------------------------------ABM ALUMNOS----------------------------------------------------> 
    
    public void altaAlumno(Alumno alumno) throws Notificaciones{
        Alumno unAlumno = buscarAlumnoAlta(alumno.getNombrealumno(),alumno.getApellidoalumno());
        String estado = "ACTIVO";
        if(unAlumno != null){
                unAlumno.setEstado(estado);
                unAlumno.setNombrealumno(alumno.getNombrealumno());
                unAlumno.setApellidoalumno(alumno.getApellidoalumno());
                unAlumno.setPeso(alumno.getPeso());
                unAlumno.setAltura(alumno.getAltura());
                unAlumno.setFechanacimiento(alumno.getFechanacimiento());
                this.miPersistencia.persistirInstancia(unAlumno);
                this.listaAlumnos = miPersistencia.getAlumnos();
        }else{
            this.miPersistencia.persistirInstancia(alumno);
            this.miPersistencia.getAlumnos();
        }
    }
    
    
    
    public void bajaAlumno(int idAlumno) throws Notificaciones{
        Alumno miAlumno = buscarAlumnoBaja(idAlumno);
        String estado = "INACTIVO";
        miAlumno.setEstado(estado);
        miPersistencia.persistirInstancia(miAlumno);
        this.miPersistencia.getAlumnos();
    }
    
    
    
    
 //  <----------------------------------------------------ABM PROFESORES----------------------------------------------------> 
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
                    
                }
            }

        } else {
            Profesormodalidad unProfesorModalidad = new Profesormodalidad(unaModalidad, unProfesor, precioHora, estado);
            this.listaProfesorModalidad.add(unProfesorModalidad);
            this.miPersistencia.persistirInstancia(unProfesorModalidad);
        }
    }


public void bajaProfesorDeModalidad(Profesormodalidad unProfesorModalidad) throws Notificaciones{
    String estado = "INACTIVO";
    for (Profesormodalidad miProfesorModalidad : this.listaProfesorModalidad) {
        if (miProfesorModalidad == unProfesorModalidad) {
            try {
                this.miPersistencia.eliminarInstancia(miProfesorModalidad);
            } catch (Exception e) {
                    throw new Notificaciones(e.getMessage());
            }
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
    List<Alumno> alumnosSinClase = new ArrayList<>();
    try {
        for(ClaseAlumno miClaseAlumno : this.listaClaseAlumno){
            if(miClaseAlumno.getClase()==unaClase){
                alumnosSinClase.add(miClaseAlumno.getAlumno());
                bajaClaseAlumno(miClaseAlumno);
            }
        }
        this.listaClases.remove(unaClase);
        this.miPersistencia.eliminarInstancia(unaClase);
    } catch (Notificaciones e) {
        throw new Notificaciones(e.getMessage());
    }finally{
        if(alumnosSinClase.isEmpty()){
            alumnosSinClase = null;
        }
        return alumnosSinClase;
    }
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
    this.miPersistencia.persistirInstancia(unContacto);
    this.listaContactos.add(unContacto);
    this.miPersistencia.persistirInstancia(unContacto);
}
//  <----------------------------------------------------ABM ASISTENCIA PROFESOR----------------------------------------------------> 

//  <----------------------------------------------------ABM ASISTENCIA PERSONAL----------------------------------------------------> 

//  <----------------------------------------------------ABM CAJA DIARIA----------------------------------------------------> 
    

//  <----------------------------------------------------ABM PERSONAL----------------------------------------------------> 
public void bajaPersonal(){
    
}

//  <----------------------------------------------------ABM OBRASOCIAL----------------------------------------------------> 
public void altaObraSocial(Obrasocial obrasocial) throws Notificaciones{
    String estado = "ACTIVO";
    Obrasocial unaObrasocial = buscarObraSocial(obrasocial.getNombreobrasocial());
    if(unaObrasocial != null){
        unaObrasocial.setNombreobrasocial(obrasocial.getNombreobrasocial());
        unaObrasocial.setContacto(obrasocial.getContacto());
        unaObrasocial.setEstado(estado);
        this.miPersistencia.persistirInstancia(unaObrasocial);
        this.listaObraSociales = miPersistencia.getObraSociales();
    }else{
        obrasocial.setEstado(estado);
        this.miPersistencia.persistirInstancia(obrasocial);
        this.listaObraSociales.add(obrasocial);
    }
        
}

//  <----------------------------------------------------ABM----------------------------------------------------> 
    
    
    
    


//  <-----------------LISTA DE GETTERS Y SETTERS------------------->

    public ControladorRele getMiRele() {
        return miRele;
    }

    public void setMiRele(ControladorRele miRele) {
        this.miRele = miRele;
    }

    public Set<Alumno> getListaAlumnos() throws Notificaciones {
        this.listaAlumnos = miPersistencia.getAlumnos();
        return listaAlumnos;
    }

    public void setListaAlumnos(Set<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public Set<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(Set<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public Set<Modalidad> getListaModalidades() {
        return listaModalidades;
    }

    public void setListaModalidades(Set<Modalidad> listaModalidades) {
        this.listaModalidades = listaModalidades;
    }

    public Set<Sector> getListaSectores() {
        return listaSectores;
    }

    public void setListaSectores(Set<Sector> listaSectores) {
        this.listaSectores = listaSectores;
    }

    public Set<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(Set<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    public Set<AsistenciaAlumno> getListaAsistenciaAlumno() {
        return listaAsistenciaAlumno;
    }

    public void setListaAsistenciaAlumno(Set<AsistenciaAlumno> listaAsistenciaAlumno) {
        this.listaAsistenciaAlumno = listaAsistenciaAlumno;
    }

    public Set<AsistenciaProfesor> getListaAsistenciaProfesor() {
        return listaAsistenciaProfesor;
    }

    public void setListaAsistenciaProfesor(Set<AsistenciaProfesor> listaAsistenciaProfesor) {
        this.listaAsistenciaProfesor = listaAsistenciaProfesor;
    }

    public Set<Cuota> getListaCuotas() {
        return listaCuotas;
    }

    public void setListaCuotas(Set<Cuota> listaCuotas) {
        this.listaCuotas = listaCuotas;
    }

    public Set<CobroCuota> getListaCobroCuota() {
        return listaCobroCuota;
    }

    public void setListaCobroCuota(Set<CobroCuota> listaCobroCuota) {
        this.listaCobroCuota = listaCobroCuota;
    }

    public Set<SaldoCuota> getListaSaldoCuota() {
        return listaSaldoCuota;
    }

    public void setListaSaldoCuota(Set<SaldoCuota> listaSaldoCuota) {
        this.listaSaldoCuota = listaSaldoCuota;
    }

    public Set<PagoProfesor> getListaPagoProfesores() {
        return listaPagoProfesores;
    }

    public void setListaPagoProfesores(Set<PagoProfesor> listaPagoProfesores) {
        this.listaPagoProfesores = listaPagoProfesores;
    }

    public Set<SaldoPagoProfesor> getListaSaldoPagoProfesores() {
        return listaSaldoPagoProfesores;
    }

    public void setListaSaldoPagoProfesores(Set<SaldoPagoProfesor> listaSaldoPagoProfesores) {
        this.listaSaldoPagoProfesores = listaSaldoPagoProfesores;
    }

    public Set<Usuario> getListaUsuarios() throws Notificaciones {
        this.listaUsuarios = miPersistencia.getUsuarios();
        return listaUsuarios;
    }

    public void setListaUsuarios(Set<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Set<Modulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(Set<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public Set<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(Set<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public ControladorHuella getMiLector() {
        return miLector;
    }

    public void setMiLector(ControladorHuella miLector) {
        this.miLector = miLector;
    }
    
    
    
}
