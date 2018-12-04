
package gimnasio.controlador;

import gimnasio.modelo.Alumno;
import gimnasio.modelo.AsistenciaAlumno;
import gimnasio.modelo.AsistenciaProfesor;
import gimnasio.modelo.Cargo;
import gimnasio.modelo.Clase;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Modulo;
import gimnasio.modelo.PagoProfesor;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import gimnasio.modelo.SaldoCuota;
import gimnasio.modelo.SaldoPagoProfesor;
import gimnasio.modelo.Sector;
import gimnasio.modelo.Usuario;
import herramientas.excepciones.Notificaciones;
import java.time.LocalDate;
import java.util.ArrayList;
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
            this.miLector = miPersistencia.getLector();
        } catch (Notificaciones ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
        Usuario unUsuario = null;
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
    
    public List<Cuota> buscarCuotasConSaldo(){
        List<Cuota> cuotasConSaldo = new ArrayList<>();
        for(Cuota miCuota :this.listaCuotas){
            if(miCuota.getEstado().equalsIgnoreCase("SALDO")){
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
     public void agregarUsuario(String nombreUsuario, String contrasenia, byte[] planillahuellas, byte[] foto) throws Notificaciones{
         if(buscarUsuarioAlta(nombreUsuario)!=null){
             throw new Notificaciones("El usuario ya existe");
         } else{
             Usuario unUsuario = new Usuario(0, nombreUsuario, contrasenia, planillahuellas, foto, listaModulos, listaCargos, listaProfesores, listaAlumnos);
             this.listaUsuarios.add(unUsuario);
             this.miPersistencia.persistirInstancia(unUsuario);
         }
     }
     
   
//  <----------------------------------------------------ABM ALUMNOS----------------------------------------------------> 
    
    public void agregarAlumno(int idUsuario, String nombreAlu, String apellido, int edad, float peso, float altura, LocalDate fechaCumpleanios) throws Notificaciones{
        if(buscarAlumnoAlta(nombreAlu,apellido)!=null){//FALTA VERIFICAR SI EL ESTADO ESTA EN BAJA
            throw new Notificaciones("El Alumno ya existe");
        }else{
            Alumno unAlumno = new Alumno(nombreAlu,apellido);
            this.listaAlumnos.add(unAlumno);
            this.miPersistencia.persistirInstancia(unAlumno);
        }
    }
    
    public void bajaAlumno(int idAlumno) throws Notificaciones{
        Alumno miAlumno = buscarAlumnoBaja(idAlumno);
        if(miAlumno!=null){
            try {
                this.listaAlumnos.remove(miAlumno);
                miAlumno.setEstado("BAJA");
                this.miPersistencia.persistirInstancia(miAlumno);
            } catch (Notificaciones ex) {
                throw new Notificaciones(ex.getMessage()); 
            }
        }
    }
    
 //  <----------------------------------------------------ABM PROFESORES----------------------------------------------------> 
public void agregarProfesor(String nombreProfesor, String apellidoProfesor)
    
    
    


    

//  <----------------------------------------------------ABM MODALIDADES----------------------------------------------------> 

//  <----------------------------------------------------ABM PROFESORES POR MODALIDADES----------------------------------------------------> 

//  <----------------------------------------------------ABM CLASES ----------------------------------------------------> 

//  <----------------------------------------------------ABM CUOTAS ----------------------------------------------------> 

//  <----------------------------------------------------ABM COBROS----------------------------------------------------> 

//  <----------------------------------------------------ABM PAGOS----------------------------------------------------> 


//  <----------------------------------------------------ABM ASISTENCIAS----------------------------------------------------> 

//  <----------------------------------------------------ABM CARGOS----------------------------------------------------> 

//  <----------------------------------------------------ABM SECTORES----------------------------------------------------> 
    

//  <----------------------------------------------------ABM CONTACTO----------------------------------------------------> 

//  <----------------------------------------------------ABM ASISTENCIA PROFESOR----------------------------------------------------> 

//  <----------------------------------------------------ABM ASISTENCIA PERSONAL----------------------------------------------------> 

//  <----------------------------------------------------ABM CAJA DIARIA----------------------------------------------------> 
    

//  <----------------------------------------------------ABM----------------------------------------------------> 

//  <----------------------------------------------------ABM----------------------------------------------------> 
    
    
    
    
//  <-----------------LISTA DE GETTERS Y SETTERS------------------->

    public ControladorRele getMiRele() {
        return miRele;
    }

    public void setMiRele(ControladorRele miRele) {
        this.miRele = miRele;
    }

    public Set<Alumno> getListaAlumnos() {
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

    public Set<Usuario> getListaUsuarios() {
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
