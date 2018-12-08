
package gimnasio.controlador;

import com.sun.org.apache.xpath.internal.operations.Minus;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.AsistenciaAlumno;
import gimnasio.modelo.AsistenciaProfesor;
import gimnasio.modelo.Cargo;
import gimnasio.modelo.Clase;
import gimnasio.modelo.Contacto;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Modulo;
import gimnasio.modelo.PagoProfesor;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import gimnasio.modelo.SaldoCuota;
import gimnasio.modelo.Saldopagoprofesor;
import gimnasio.modelo.Sector;
import gimnasio.modelo.Usuario;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.ClaseAlumno;
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
    private Set<SaldoPagoProfesor> listaSaldoPagoProfesores = new HashSet<>();
    private Set<Usuario> listaUsuarios = new HashSet<>();
    private Set<Modulo> listaModulos = new HashSet<>();
    private Set<Cargo> listaCargos = new HashSet<>();
    
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
    
    
    public List<Clase> buscarClasesPorModalidad(int idModalidad){
        List<Clase> clasesModalidad = new ArrayList<>();
        for(Clase miClase : this.listaClases){
            if(miClase.getProfesormodalidad().getModalidad().getIdmodalidad() == idModalidad){
                clasesModalidad.add(miClase);
            }
        }
        return clasesModalidad;
    }
    
    
    public List<Clase> buscarClasesPorProfesor(int idProfesor){
        List<Clase> clasesProfesor = new ArrayList<>();
        for(Clase miClase: this.listaClases){
            if(miClase.getProfesormodalidad().getProfesor().getIdprofesor() == idProfesor){
                clasesProfesor.add(miClase);
            }
        }
        return clasesProfesor;
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
    public void agregarUsuario(String nombreUsuario, String contrasenia, byte[] planillahuellas, byte[] foto) throws Notificaciones {
        Usuario unUsuario = buscarUsuarioAlta(nombreUsuario);
        String estado = "ACTIVO";
        if (unUsuario == null) {
            unUsuario = new Usuario(nombreUsuario, contrasenia, planillahuellas, foto, estado);
            this.listaUsuarios.add(unUsuario);
            this.miPersistencia.persistirInstancia(unUsuario);
        } else {
            if (unUsuario.getEstado().equalsIgnoreCase("ACTIVO")) {
                throw new Notificaciones("El usuario ya existe");
            } else {
                unUsuario.setEstado(estado);
                unUsuario.setContrasenia(contrasenia);
                unUsuario.setPlanillahuellas(planillahuellas);
                unUsuario.setFoto(foto);
                this.listaUsuarios.add(unUsuario);
                this.miPersistencia.persistirInstancia(unUsuario);
            }
        }
    }
     
     
    public void agregarUsuario(String nombreUsuario, String contrasenia) throws Notificaciones {
        String estado = "ACTIVO";
        Usuario unUsuario = buscarUsuarioAlta(nombreUsuario);
        if (unUsuario == null) {
            unUsuario = new Usuario(nombreUsuario, contrasenia, estado);
            this.listaUsuarios.add(unUsuario);
            this.miPersistencia.persistirInstancia(unUsuario);
        }else{
            if (unUsuario.getEstado().equalsIgnoreCase("ACTIVO")) {
                throw new Notificaciones("El usuario ya existe");
            } else {
                unUsuario.setEstado(estado);
                unUsuario.setContrasenia(contrasenia);
                this.listaUsuarios.add(unUsuario);
                this.miPersistencia.persistirInstancia(unUsuario);
            }
        }
    }
     
     
     public void bajaUsuario(int idUsuario) throws Notificaciones{
         Usuario unUsuario = buscarUsuarioBaja(idUsuario);
         String estado = "INACTIVO";
         unUsuario.setEstado(estado);
         this.miPersistencia.persistirInstancia(unUsuario);
     }
   
//  <----------------------------------------------------ABM ALUMNOS----------------------------------------------------> 
    
    public void agregarAlumno(Usuario unUsuario, String nombreAlu, String apellido, Double peso, Double altura, Date fechaNacimiento, Contacto contacto) throws Notificaciones{
        Alumno unAlumno = buscarAlumnoAlta(nombreAlu,apellido);
        String estado = "ACTIVO";
        if(unAlumno != null){
            if(unAlumno.getEstado().equalsIgnoreCase(estado)){
                throw new Notificaciones("El Alumno ya existe");
            }else{
                unAlumno.setEstado(estado);
                unAlumno.setNombrealumno(nombreAlu);
                unAlumno.setApellidoalumno(apellido);
                unAlumno.setPeso(peso);
                unAlumno.setAltura(altura);
                unAlumno.setFechanacimiento(fechaNacimiento);
                this.listaAlumnos.add(unAlumno);
                this.miPersistencia.persistirInstancia(unAlumno);
            }
        }else{
            unAlumno = new Alumno(unUsuario, nombreAlu, apellido, peso, altura,estado, fechaNacimiento, contacto);
            this.listaAlumnos.add(unAlumno);
            this.miPersistencia.persistirInstancia(unAlumno);
        }
    }
    
    public void agregarAlumno(Usuario unUsuario, String nombreAlu, String apellido, Contacto contacto) throws Notificaciones{
        Alumno unAlumno = buscarAlumnoAlta(nombreAlu,apellido);
        String estado = "ACTIVO";
        if(unAlumno != null){
            if(unAlumno.getEstado().equalsIgnoreCase(estado)){
                throw new Notificaciones("El Alumno ya existe");
            }else{
                unAlumno.setEstado(estado);
                unAlumno.setNombrealumno(nombreAlu);
                unAlumno.setApellidoalumno(apellido);
                this.listaAlumnos.add(unAlumno);
                this.miPersistencia.persistirInstancia(unAlumno);
            }
        }else{
            unAlumno = new Alumno(unUsuario, nombreAlu, apellido,contacto, estado);
            this.listaAlumnos.add(unAlumno);
            this.miPersistencia.persistirInstancia(unAlumno);
        }
    }
    
    
    public void bajaAlumno(int idAlumno) throws Notificaciones{
        Alumno miAlumno = buscarAlumnoBaja(idAlumno);
        String estado = "INACTIVO";
        miAlumno.setEstado(estado);
        miPersistencia.persistirInstancia(miAlumno);
    }
    
    
    
    
 //  <----------------------------------------------------ABM PROFESORES----------------------------------------------------> 
public void agregarProfesor(Usuario usuario, int idContacto, Integer idObraSocial, String nombreProfesor, String apellidoProfesor, Double peso, Double altura, Date fechaNacimiento) throws Notificaciones {
        String estado = "ACTIVO";
        Profesor unProfesor = buscarProfesor(nombreProfesor, apellidoProfesor);
        if (unProfesor != null) {
            if (unProfesor.getEstado().equalsIgnoreCase(estado)) {
                throw new Notificaciones("El profesor ya existe");
            } else {
                unProfesor.setNombreprofesor(nombreProfesor);
                unProfesor.setIdcontacto(idContacto);
                unProfesor.setIdobrasocial(idObraSocial);
                unProfesor.setApellidoprofesor(apellidoProfesor);
                unProfesor.setPeso(peso);
                unProfesor.setAltura(altura);
                unProfesor.setFechanacimiento(fechaNacimiento);
                this.listaProfesores.add(unProfesor);
                this.miPersistencia.persistirInstancia(unProfesor);
            }
        } else {
            unProfesor = new Profesor(usuario, idContacto, idObraSocial, nombreProfesor, apellidoProfesor, peso, altura, estado, fechaNacimiento);
            this.listaProfesores.add(unProfesor);
            this.miPersistencia.persistirInstancia(unProfesor);
        }
    }

public void agregarProfesor(Usuario unUsuario, String nombreProfesor, String apellidoProfesor) throws Notificaciones{
        String estado = "ACTIVO";
        Profesor unProfesor = buscarProfesor(nombreProfesor, apellidoProfesor);
        if (unProfesor != null) {
            if (unProfesor.getEstado().equalsIgnoreCase(estado)) {
                throw new Notificaciones("El profesor ya existe");
            } else {
                unProfesor.setNombreprofesor(nombreProfesor);
                unProfesor.setApellidoprofesor(apellidoProfesor);
                this.listaProfesores.add(unProfesor);
                this.miPersistencia.persistirInstancia(unProfesor);
            }
        } else {
            unProfesor = new Profesor(unUsuario, nombreProfesor, apellidoProfesor, estado);
            this.listaProfesores.add(unProfesor);
            this.miPersistencia.persistirInstancia(unProfesor);
        }
    }



public List<Alumno> bajaProfesor(int idProfesor) throws Notificaciones{
    Profesor unProfesor;
    List<Alumno> listaAlumnosSinClases = new ArrayList<>();
    String estado = "INACTIVO";
    List<Clase> listaClases = buscarClasesPorProfesor(idProfesor);
    if(listaClases != null){
        for(Clase miClase : listaClases){
                listaAlumnosSinClases = bajaClase(miClase);
                
        }
    }
    unProfesor = buscarProfesor(idProfesor);
    unProfesor.setEstado(estado);
    this.miPersistencia.persistirInstancia(unProfesor);
    if(listaAlumnosSinClases.isEmpty()){
        listaAlumnosSinClases = null;
    }
    return listaAlumnosSinClases;
}

//  <----------------------------------------------------ABM MODALIDADES----------------------------------------------------> 
    public void agregarModalidad(String nombreModalidad) throws Notificaciones {
        Modalidad unaModalidad = buscarModalidad(nombreModalidad);
        String estado = "ACTIVO";
        if (unaModalidad != null) {
            if (unaModalidad.getEstado().equalsIgnoreCase(estado)) {
                throw new Notificaciones("La modalidad ya existe");
            } else {
                   unaModalidad.setEstado(estado);
                   this.miPersistencia.persistirInstancia(unaModalidad);
            }
        } else {
            unaModalidad = new Modalidad(nombreModalidad, estado);
            this.listaModalidades.add(unaModalidad);
            this.miPersistencia.persistirInstancia(unaModalidad);
        }
    }

    public void agregarModalidad(String nombreModalidad, String descripcionModalidad) throws Notificaciones {
        Modalidad unaModalidad = buscarModalidad(nombreModalidad);
        String estado = "ACTIVO";
        if (unaModalidad != null) {
            if (unaModalidad.getEstado().equalsIgnoreCase(estado)) {
                throw new Notificaciones("La modalidad ya existe");
            } else {
                   unaModalidad.setEstado(estado);
                   unaModalidad.setNombremodalidad(nombreModalidad);
                   unaModalidad.setDescripcionmodalidad(descripcionModalidad);
                   this.miPersistencia.persistirInstancia(unaModalidad);
            }
        } else {
            unaModalidad = new Modalidad(nombreModalidad, descripcionModalidad, estado);
            this.listaModalidades.add(unaModalidad);
            this.miPersistencia.persistirInstancia(unaModalidad);
        }
    }

public void bajaModalidad(int idModalidad) throws Notificaciones{

} 

//  <----------------------------------------------------ABM PROFESORES POR MODALIDADES----------------------------------------------------> 

public void agregarProfesorPorModalidad(Modalidad unModalidad, Profesor unProfesor) throws Notificaciones{
    String estado = "ACTIVO";
    List<Modalidad> modalidadesDelProfesor = buscarModalidadDeProfesor(unProfesor.getIdprofesor());
    for(Profesormodalidad miProfesorModalidad : this.listaProfesorModalidad){
        if (miProfesorModalidad.getModalidad()==unModalidad){
            throw new Notificaciones("El profesor ya tiene asignada la modalidad");
        }else{
            Profesormodalidad unProfesorModalidad = new Profesormodalidad(unModalidad, unProfesor,estado);
            this.listaProfesorModalidad.add(unProfesorModalidad);
            this.miPersistencia.persistirInstancia(unProfesorModalidad);
        }
    }
}

public void quitarProfesorDeModalidad(Profesormodalidad unProfesorModalidad) throws Notificaciones{
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

public void agregarClase(Profesormodalidad unProfesorModalidad, Sector unSector) throws Notificaciones{
    for(Clase miClase : this.listaClases){
        if(miClase.getProfesormodalidad()==unProfesorModalidad && miClase.getSector()== unSector){
            throw new Notificaciones("La clase ya existe");
        }else{
            Clase unaClase = new Clase(unProfesorModalidad, unSector);
            this.listaClases.add(miClase);
            this.miPersistencia.persistirInstancia(unaClase);
        }
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


public void agregarAlumnoClase(Alumno unAlumno, Clase unaClase, int cantidadClases) throws Notificaciones{
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

public void altaCargo(String nombreCargo) throws Notificaciones{
    if(buscarCargo(nombreCargo)!=null){
        throw new Notificaciones("El cargo ya existe");
    }else{
        Cargo unCargo = new Cargo(nombreCargo);
        this.listaCargos.add(unCargo);
        this.miPersistencia.persistirInstancia(unCargo);
    }
}


public void bajaCargo(Cargo unCargo){
    
}
//  <----------------------------------------------------ABM SECTORES----------------------------------------------------> 
    

//  <----------------------------------------------------ABM CONTACTO----------------------------------------------------> 

public void altaContacto (Contacto unContacto){
    this.miPersistencia.persistirInstancia(unContacto);
    this.listaContacto()
}
//  <----------------------------------------------------ABM ASISTENCIA PROFESOR----------------------------------------------------> 

//  <----------------------------------------------------ABM ASISTENCIA PERSONAL----------------------------------------------------> 

//  <----------------------------------------------------ABM CAJA DIARIA----------------------------------------------------> 
    

//  <----------------------------------------------------ABM PERSONAL----------------------------------------------------> 
public void bajaPersonal(){
    
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
