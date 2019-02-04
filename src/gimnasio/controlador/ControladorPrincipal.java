package gimnasio.controlador;

import com.itextpdf.text.DocumentException;
import gimnasio.modelo.*;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Family
 */
public class ControladorPrincipal {

//  <-----------------CONTROLADORES EXTRA-------------------------->
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
    private ControladorAsistenciaAlumno controladorAsistenciaAlumno;
    private ControladorAsistenciaProfesor controladorAsistenciaProfesor;
    private ControladorSaldoCuota controladorSaldoCuota;
    private ControladorCobroCuota controladorCobroCuota;
    private ControladorCaja controladorCaja;
    private ControladorMovimiento controladorMovimientos;
    private ControladorReporte controladorReporte;
    private ControladorIngresosPuerta controladorIngresosPuerta;

    private ModeloPrincipal miModeloPrincipal;

//  <-----------------CONSTRUCTOR DEL CONTROLADOR PRINCIPAL------------------->
    public ControladorPrincipal(ModeloPrincipal ModeloPrincipal) throws Notificaciones {

        this.miModeloPrincipal = ModeloPrincipal;
        this.miPersistencia = new ControladorPersistencia();

        this.controladorUsuario = new ControladorUsuario(this.miPersistencia);
        this.controladorAlumno = new ControladorAlumno(this.miPersistencia);
        this.controladorObraSocial = new ControladorObraSocial(this.miPersistencia);
        this.controladorProfesor = new ControladorProfesor(this.miPersistencia);
        this.controladorClase = new ControladorClase(this.miPersistencia);
        this.controladorClaseProfesor = new ControladorClaseProfesor(this.miPersistencia);
        this.controladorModalidad = new ControladorModalidad(this.miPersistencia);
        this.controladorProfesorModalidad = new ControladorProfesorModalidad(this.miPersistencia);
        this.controladorClaseAlumno = new ControladorClaseAlumno(this.miPersistencia);
        this.controladorCuota = new ControladorCuota(this.miPersistencia);
        this.controladorHorarioProfesor = new ControladorHorarioProfesor(this.miPersistencia);
        this.controladorHorarioAlumno = new ControladorHorarioAlumno(this.miPersistencia);
        this.controladorAsistenciaAlumno = new ControladorAsistenciaAlumno(this.miPersistencia);
        this.controladorAsistenciaProfesor = new ControladorAsistenciaProfesor(this.miPersistencia);
        this.controladorSaldoCuota = new ControladorSaldoCuota(this.miPersistencia);
        this.controladorCobroCuota = new ControladorCobroCuota(this.miPersistencia);
        this.controladorPersonal = new ControladorPersonal(this.miPersistencia);
        this.controladorCaja = new ControladorCaja(this.miPersistencia);
        this.controladorMovimientos = new ControladorMovimiento(this.miPersistencia);
        this.controladorReporte = new ControladorReporte(controladorMovimientos, controladorCaja);
        this.controladorIngresosPuerta = new ControladorIngresosPuerta(this.miPersistencia);
    }

    public ControladorPersistencia getMiPersistencia() {
        return miPersistencia;
    }

    public void setMiPersistencia(ControladorPersistencia miPersistencia) {
        this.miPersistencia = miPersistencia;
    }

    public static Date parseDate(String date) {
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
    public List<Usuario> getListaUsuarios() throws Notificaciones {
        return this.controladorUsuario.getListaUsuarios();
    }

    public void altaUsuario(Usuario unUsuario) throws Notificaciones {
        this.controladorUsuario.altaUsuario(unUsuario);
    }

    public void bajaUsuario(int idUsuario) throws Notificaciones {
        this.controladorUsuario.bajaUsuario(idUsuario);
    }

//  <----------------------------------------------------ABM ALUMNOS---------------------------------------------------->
    public List<Alumno> getListaAlumnos() throws Notificaciones {
        return this.controladorAlumno.getListaAlumnos();
    }

    public void bajaAlumno(Alumno miAlumno) throws Notificaciones {
        this.controladorAlumno.bajaAlumno(miAlumno);
    }

    public void altaAlumno(Alumno unAlumno) throws Notificaciones {
        this.controladorAlumno.altaAlumno(unAlumno);
    }

    //  <----------------------------------------------------ABM PROFESORES---------------------------------------------------->
    public void altaProfesor(Profesor profesor) throws Notificaciones {
        this.controladorProfesor.altaProfesor(profesor);
    }

    /**
     * Retorna una lista de Alumnos que se quedaron sin clases debido a que el
     * profesor fue dado de Baja, esta baja se especifica en el controlador
     * Principal y no en el controlador de profesores porque necesita buscar
     * clases por profesor.
     *
     * @param idProfesor el entero que es ID de profesor
     * @return listaAlumnosSinClases puede ser null
     * @throws Notificaciones
     */
    public void bajaProfesor(int idProfesor) throws Notificaciones {
        controladorProfesor.bajaProfesor(idProfesor);
    }

    public Profesor buscarProfesor(String nombreProfesor, String apellidoProfesor) {
        return this.controladorProfesor.buscarProfesor(nombreProfesor, apellidoProfesor);
    }

    public List<Clase> buscarClasesPorProfesor(int idProfesor) throws Notificaciones {
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
    public void altaClaseAlumno(ClaseAlumno claseAlumno) throws Notificaciones {
        this.controladorClaseAlumno.altaClaseAlumno(claseAlumno);
    }

    public void bajaClaseAlumno(ClaseAlumno unaClaseAlumno) throws Notificaciones {
        System.out.println("ControladorPrincipal bajaClaseAlumno");
        Alumno unAlumno = unaClaseAlumno.getAlumno();
        System.out.println("Alumno detectado: " + unAlumno);

        for (HorarioAlumno horario : controladorHorarioAlumno.getListaHorariosAlumno(unaClaseAlumno)) {
            System.out.println("For HorarioAlumno");
            if (horario.getClaseAlumno().toString().equalsIgnoreCase(unaClaseAlumno.toString())) {
                controladorHorarioAlumno.bajaHorario(horario);
                System.out.println("Dando de baja horario: " + horario);
            }
        }
        this.controladorClaseAlumno.bajaClaseAlumno(unaClaseAlumno);
        this.miPersistencia.actualizarInstancias();
    }

//  <----------------------------------------------------ABM CUOTAS ---------------------------------------------------->
    public void altaCuota(Cuota unaCuota) throws Notificaciones {
        this.controladorCuota.altaCuota(unaCuota);
    }

    public void altaCuotaConDeuda(Alumno unAlumno, Clase unaClase, Double precio, Date altaCuota, Date vencimientoCuota) throws Notificaciones {
//    String estado = "GENERADA";
//    Cuota nuevaCuota = new Cuota(unAlumno, unaClase, precio, estado, altaCuota, vencimientoCuota);
//    this.listaCuotas.add(nuevaCuota);
//    this.miPersistencia.persistirInstancia(nuevaCuota);
    }

    public void bajaCuota(ClaseAlumno unaClaseAlumno, Date altaCuota) {
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

    public void bajaCargo(Cargo unCargo) {

    }
//  <----------------------------------------------------ABM SECTORES---------------------------------------------------->

    public void altaSector(Sector sector) throws Notificaciones {
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

    public void altaContacto(Contacto unContacto) throws Notificaciones {
        unContacto.setEstado("ACTIVO");
        this.miPersistencia.persistirInstancia(unContacto);
//    this.listaContactos.add(unContacto);
//    this.miPersistencia.persistirInstancia(unContacto);
    }
//  <----------------------------------------------------ABM ASISTENCIA PROFESOR---------------------------------------------------->

//  <----------------------------------------------------ABM ASISTENCIA PERSONAL---------------------------------------------------->
//  <----------------------------------------------------ABM CAJA DIARIA---------------------------------------------------->
//  <----------------------------------------------------ABM PERSONAL---------------------------------------------------->
    public void bajaPersonal() {

    }

//  <----------------------------------------------------ABM OBRASOCIAL---------------------------------------------------->
    public void altaObraSocial(Obrasocial obrasocial) throws Notificaciones {
        this.controladorObraSocial.altaObraSocial(obrasocial);
    }

    public List<Obrasocial> getListaObrasSociales() throws Notificaciones {
        return this.controladorObraSocial.getObrasSociales();
    }

    public void bajaObraSocial(String nombreObra) throws Notificaciones {
        this.controladorObraSocial.bajaObraSocial(nombreObra);
    }

//  <------GETTERS DE LISTAS--------------------------------------------------->
    public List<Clase> getListaClases() {
        return this.controladorClase.getListaClases();
    }

    public List<Modalidad> getListaModalidades() throws Notificaciones {
        return this.controladorModalidad.getListaModalidades();
    }

    public List<Profesor> getListaProfesores() throws Notificaciones {
        return this.controladorProfesor.getListaProfesores();
    }

    public List<ClaseAlumno> getListaClasesAlumnos() throws Notificaciones {
        return this.controladorClaseAlumno.getListaClaseAlumno();
    }

    public List<Profesormodalidad> getListaProfesorModalidades() {
        return this.controladorProfesorModalidad.getListaProfesorModalidad();
    }

    public List<Cuota> getListaCuotas() throws Notificaciones {
        return this.controladorCuota.getListaCuotas();
    }

    public List<ClaseProfesor> getListaClaseProfesor() throws Notificaciones {
        return this.controladorClaseProfesor.getListaClaseProfesor();
    }

    public void altaClaseProfesor(ClaseProfesor unaClaseProfesor) throws Notificaciones {
        controladorClaseProfesor.altaClaseProfesor(unaClaseProfesor);
    }

    public List<ClaseAlumno> bajaClaseProfesor(ClaseProfesor unaClase) throws Notificaciones {
        List<ClaseAlumno> alumnosSinClase = new ArrayList<>();
        controladorClaseProfesor.bajaClaseProfesor(unaClase);
        for (ClaseAlumno claseAlu : this.controladorClaseAlumno.getListaClaseAlumno()) {
            System.out.println(claseAlu);
            if (claseAlu.getClaseProfesor().getIdclaseprofesor() == unaClase.getIdclaseprofesor()) {
                claseAlu.setEstado("BAJA");
                alumnosSinClase.add(claseAlu);
                miPersistencia.persistirInstancia(claseAlu);
                System.out.println("Agregado ClaseAlumno(lista sin clases): " + claseAlu);
            }
        }
        return alumnosSinClase;
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

    public void altaHorarioProfesor(HorarioProfesor unHorario) throws Notificaciones {
        controladorHorarioProfesor.altaHorarioProfesor(unHorario);
    }

    public List<HorarioProfesor> getListaHorarios() throws Notificaciones {
        return controladorHorarioProfesor.getListaHorarios();
    }

    public void bajaHorarioProfesor(HorarioProfesor horarioProfesor) throws Notificaciones {
        //Obtengo la claseProfesor correspondiente
        for (ClaseProfesor claseProfesor : controladorClaseProfesor.getListaClaseProfesor()) {
            if (claseProfesor.getIdclaseprofesor() == horarioProfesor.getClaseProfesor().getIdclaseprofesor()) {
                //Obtengo todas las clasesAlumno de esa claseProfesor
                for (ClaseAlumno claseAlumno : controladorClaseAlumno.getListaClaseAlumno()) {
                    for (ClaseAlumno claseAlu : claseProfesor.getClaseAlumnos()) {
                        if (claseAlumno.getIdclasealumno() == claseAlu.getIdclasealumno()) {
                            //Obtengo los horarios de los alumnos
                            for (HorarioAlumno horario : claseAlumno.getHorarios()) {
                                if (horarioProfesor.getInicio().compareTo(horario.getInicio()) == 0) {
                                    //si es el mismo horario, asumo que
                                    //es el horarioAlumno correspondiente al horarioProfesor y lo pongo inactivo
                                    horario.setEstado("INACTIVO");
                                    this.miPersistencia.persistirInstancia(horario);
                                }
                            }
                        }
                    }
                }
            }
        }
        controladorHorarioProfesor.bajaHorarioProfesor(horarioProfesor);
    }

    public void altaHorarioAlumno(HorarioAlumno unHorario) throws Notificaciones {
        controladorHorarioAlumno.altaHorarioAlumno(unHorario);
    }

    public List<HorarioAlumno> getListaHorariosAlumno(ClaseAlumno clase) throws Notificaciones {
        List<HorarioAlumno> refrescar = controladorHorarioAlumno.getListaHorariosAlumno(clase);
        List<HorarioAlumno> retorno = new ArrayList<>();
        for (HorarioAlumno horario : refrescar) {
            miPersistencia.refrescar(horario);
            retorno.add(horario);
        }
        return retorno;
    }

    public void altaAsistenciaAlumno(ClaseAlumno asistencia, Date fecha) throws Notificaciones {

        controladorAsistenciaAlumno.altaAsistenciaAlumno(asistencia, fecha);
    }

    public void altaAsistenciaProfesor(AsistenciaProfesor asistencia) throws Notificaciones {
        controladorAsistenciaProfesor.altaAsistenciaProfesor(asistencia);
    }

    public void guardarSaldoAnterior(SaldoCuota saldoAnterior) throws Notificaciones {
        controladorSaldoCuota.guardarSaldo(saldoAnterior);
    }

    public CobroCuota generarCobroCuota(Cuota cuota, Double abono, LocalDate fecha) throws Notificaciones {
        controladorCuota.actualizarEstadoCuota(cuota);
        CobroCuota cobroCuota = controladorCobroCuota.generarCobroCuota(cuota, abono, fecha);
        return cobroCuota;
    }

    public void generarNuevoSaldo(CobroCuota cobroCuota, Double saldo) throws Notificaciones {
        SaldoCuota saldoCuota = new SaldoCuota(cobroCuota, saldo, "GENERADO");
        controladorSaldoCuota.guardarSaldo(saldoCuota);
    }

    public List<Cuota> getCuotasDeAlumno(Alumno unAlumno) throws Notificaciones {
        return controladorCuota.getCuotasDeAlumno(unAlumno);
    }

    public List<AsistenciaAlumno> getAsistenciasAlumnoDeHoy() throws Notificaciones {
        return controladorAsistenciaAlumno.getAsistenciasDeHoy();
    }

    public List<AsistenciaProfesor> getAsistenciasProfesorDeHoy() throws Notificaciones {
        return controladorAsistenciaProfesor.getAsistenciasDeHoy();
    }

    public Usuario buscarUsuario(String text1, String text2) {
        return controladorUsuario.buscarUsuario(text1, text2);
    }

    public void altaPersonal(Personal unPersonal) throws Notificaciones {
        controladorPersonal.altaPersonal(unPersonal);
    }

    public void altaCaja(Cajadiaria caja) throws Notificaciones {
        controladorCaja.altaCaja(caja);
    }

    public boolean hayCajaAbiertaHoy() throws Notificaciones {
        return controladorCaja.hayCajaAbiertaHoy();
    }

    public void cerrarCaja() throws Notificaciones {
        controladorCaja.cerrarCaja();
    }

    public List<Movimiento> getListaMovimientos() throws Notificaciones {
        return controladorMovimientos.getListaMovimientos();
    }

    public List<Cajadiaria> dameCaja() throws Notificaciones {
        return controladorCaja.dameCaja();
    }

    public void altaMovimiento(Movimiento unMovimiento) throws Notificaciones {
        controladorMovimientos.altaMomiviento(unMovimiento);
    }

    public void generarReporteDiario() throws Notificaciones, DocumentException {
        controladorReporte.generarReporte(false, controladorCaja.dameCaja(), controladorMovimientos.getListaMovimientosDeHoy());
    }

    public void generarReporteDias(LocalDate desde, LocalDate hasta) throws Notificaciones {
        controladorReporte.generarReporte(true, controladorCaja.dameCajas(desde, hasta), controladorMovimientos.getListaMovimientosDe(desde, hasta));
    }

    public List<ClaseAlumno> getListaClasesSinHorario(Alumno miAlumno) {
        return controladorHorarioAlumno.getListaClasesSinHorario(miAlumno);
    }

    public Alumno buscarAlumnoFromDB(Alumno miAlumno) throws Notificaciones {
        return controladorAlumno.buscarAlumnoFromDB(miAlumno);
    }

    public void refrescarInstancia(Usuario usuario) {
        try {
            miPersistencia.refrescar(usuario);
        } catch (Notificaciones ex) {
            ex.printStackTrace();
        }
    }

    public void cerrarPersistencia() {
        miPersistencia.cerrarSesion();
    }

    public Cajadiaria dameCajaActual() throws Notificaciones {
        return controladorCaja.dameCajaActual();
    }

    public void nuevoIngresoPuerta(Date ahora, Usuario miUsuario, DefaultTableModel modeloTabla, JTable tabla) throws Notificaciones {
        controladorIngresosPuerta.nuevoIngresoPuerta(ahora, miUsuario, modeloTabla, tabla);
    }

    public void generarReporteIngresosEgresosHoy() throws Notificaciones {
        controladorReporte.generarReporteAsistencias(LocalDate.now(), LocalDate.now(), controladorIngresosPuerta.getListaIngresosPuerta(LocalDate.now(), LocalDate.now()));
    }

    public void generarReporteIngresosPorDia(LocalDate desde, LocalDate hasta) throws Notificaciones {
        controladorReporte.generarReporteAsistencias(desde, hasta, controladorIngresosPuerta.getListaIngresosPuerta(desde, hasta));
    }

    public void cerrarTodasLasCajas() throws Notificaciones {
        controladorCaja.cerrarTodasLasCajas();
    }

    public List<Cajadiaria> dameCajasSinCerrar() throws Notificaciones {
        return controladorCaja.dameCajasSinCerrar();
    }

    public List<ClaseProfesor> dameClaseProfesor(String text) throws Notificaciones {
        return controladorClaseProfesor.dameClaseProfesor(text);
    }

    public void actualizarInstancia(Object o) throws Notificaciones {

        miPersistencia.actualizarInstancias(o);
    }

    public void generarReporteMorosos() {
        this.controladorReporte.generarReporteMorosos();
    }

    public List<AsistenciaAlumno> getListaAsistenciaAlumno() throws Notificaciones {
        return this.controladorAsistenciaAlumno.getListaAsistenciaAlumno();
    }

    public void bajaCuotaAutomatica(Set<Cuota> cuotas, ClaseAlumno claseAlumno) throws Notificaciones {
        List<Cuota> listaCuotas = new ArrayList<>();
        listaCuotas.addAll(cuotas);
        for (Cuota unaCuota : listaCuotas) {
            if (unaCuota.getClaseProfesor().getIdclaseprofesor() == claseAlumno.getClaseProfesor().getIdclaseprofesor()) {
                unaCuota.setEstado("BAJA");
                miPersistencia.persistirInstancia(unaCuota);
            }
        }
    }

    public void actualizarHorariosDeAlumnos(ClaseProfesor claseProfesor) throws Notificaciones {
        for (ClaseAlumno claseAlumno : claseProfesor.getClaseAlumnos()) {
            List<ClaseAlumno> listaClasesAlumnos = controladorClaseAlumno.getListaClaseAlumno();
            for (ClaseAlumno claseAlu : listaClasesAlumnos) {
                if (claseAlu.getIdclasealumno() == claseAlumno.getIdclasealumno()) {
                    System.out.println("se encontro la claseAlumno en la base de datos");
                    claseAlumno = claseAlu;
                    break;
                }
            }

            claseAlumno.getHorarios().clear();
            Set<HorarioAlumno> listaHorarios = new HashSet<>();

            for (HorarioProfesor horarioProfe : claseProfesor.getHorarios()) {
                if (horarioProfe.getEstado().equalsIgnoreCase("ACTIVO")) {
                    HorarioAlumno horario = new HorarioAlumno(claseAlumno, horarioProfe.getInicio(), horarioProfe.getFin());
                    horario.setEstado("ACTIVO");
                    System.out.println("Agregando nuevo horario y persistiendio....");
                    miPersistencia.persistirInstancia(horario);
                    listaHorarios.add(horario);
                }
            }
            claseAlumno.getHorarios().addAll(listaHorarios);
            miPersistencia.persistirInstancia(claseAlumno);
        }
        miPersistencia.persistirInstancia(claseProfesor);
    }

    public List<IngresosPuerta> getListaIngresosPuertaHoy() throws Notificaciones {
        return controladorIngresosPuerta.getListaIngresosPuerta(LocalDate.now(), LocalDate.now());
    }

}
