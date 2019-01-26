package gimnasio.controlador;

import com.itextpdf.text.DocumentException;
import gimnasio.modelo.*;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
        this.controladorClaseAlumno.bajaClaseAlumno(unaClaseAlumno);
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

    public List<Cargo> getListaCargos() {
        return this.miModeloPrincipal.getListaCargos();
    }

    public List<Modalidad> getListaModalidades() throws Notificaciones {
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

    public List<Modulo> getListaModulos() {
        return this.miModeloPrincipal.getListaModulos();
    }

    public List<Personal> getListaPersonal() {
        return this.miModeloPrincipal.getListaPersonal();
    }

    public List<CargoPersonal> getListaCargoPersonal() {
        return this.miModeloPrincipal.getListaCargoPersonal();
    }

    public List<ClaseProfesor> getListaClaseProfesor() throws Notificaciones {
        return this.controladorClaseProfesor.getListaClaseProfesor();
    }

    public List<AperturaCajaDiaria> getListaAperturaCajaDiaria() {
        return this.miModeloPrincipal.getListaAperturaCajaDiaria();
    }

    public List<SectorClase> getListaSectorClase() {
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

    public void altaHorarioProfesor(HorarioProfesor unHorario) throws Notificaciones {
        controladorHorarioProfesor.altaHorarioProfesor(unHorario);
    }

    public List<HorarioProfesor> getListaHorarios() throws Notificaciones {
        return controladorHorarioProfesor.getListaHorarios();
    }

    public void bajaHorarioProfesor(HorarioProfesor horarioProfesor) throws Notificaciones {
        controladorHorarioProfesor.bajaHorarioProfesor(horarioProfesor);
    }

    public void altaHorarioAlumno(HorarioAlumno unHorario) throws Notificaciones {
        controladorHorarioAlumno.altaHorarioAlumno(unHorario);
    }

    public List<HorarioAlumno> getListaHorariosAlumno(Alumno unAlumno) throws Notificaciones {
        return controladorHorarioAlumno.getListaHorariosAlumno(unAlumno);
    }

    public void altaAsistenciaAlumno(AsistenciaAlumno asistencia) throws Notificaciones {
        controladorAsistenciaAlumno.altaAsistenciaAlumno(asistencia);
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

    public Cajadiaria dameCajaActual() throws Notificaciones{
        return controladorCaja.dameCajaActual();
    }

    public void nuevoIngresoPuerta(Date ahora, Usuario miUsuario, DefaultTableModel modeloTabla, JTable tabla) throws Notificaciones {
        controladorIngresosPuerta.nuevoIngresoPuerta(ahora, miUsuario, modeloTabla, tabla);
    }

    public void generarReporteIngresosEgresosHoy() {
        controladorReporte.generarReporteAsistencias(controladorIngresosPuerta.getListaIngresosPuerta(LocalDate.now(),LocalDate.now()));
    }
    
    public void generarReporteIngresosPorDia(LocalDate desde, LocalDate hasta){
        controladorReporte.generarReporteAsistencias(controladorIngresosPuerta.getListaIngresosPuerta(desde, hasta));
    }
}
