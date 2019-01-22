
package gimnasio.controlador;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xpath.internal.operations.Minus;
import gimnasio.modelo.*;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    private ControladorAsistenciaAlumno controladorAsistenciaAlumno;
    private ControladorAsistenciaProfesor controladorAsistenciaProfesor;
    private ControladorSaldoCuota controladorSaldoCuota;
    private ControladorCobroCuota controladorCobroCuota;
    private ControladorCaja controladorCaja;
    private ControladorMovimiento controladorMovimientos;
    
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
        this.controladorAsistenciaAlumno = new ControladorAsistenciaAlumno(this.miPersistencia);
        this.controladorAsistenciaProfesor = new ControladorAsistenciaProfesor(this.miPersistencia);
        this.controladorSaldoCuota = new ControladorSaldoCuota(this.miPersistencia);
        this.controladorCobroCuota = new ControladorCobroCuota(this.miPersistencia);
        this.controladorPersonal = new ControladorPersonal(this.miPersistencia);
        this.controladorCaja = new ControladorCaja(this.miPersistencia);
        this.controladorMovimientos = new ControladorMovimiento(this.miPersistencia);
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
    
    public List<Alumno> getListaAlumnos() throws Notificaciones{
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
    public void bajaProfesor(int idProfesor) throws Notificaciones {
        controladorProfesor.bajaProfesor(idProfesor);
    }
    
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

    public List<HorarioAlumno> getListaHorariosAlumno() throws Notificaciones{
        return controladorHorarioAlumno.getListaHorariosAlumno();
    }

    public void altaAsistenciaAlumno(AsistenciaAlumno asistencia) throws Notificaciones{
        controladorAsistenciaAlumno.altaAsistenciaAlumno(asistencia);
    }
    
    public void altaAsistenciaProfesor(AsistenciaProfesor asistencia) throws Notificaciones{
        controladorAsistenciaProfesor.altaAsistenciaProfesor(asistencia);
    }

    public void guardarSaldoAnterior(SaldoCuota saldoAnterior) throws Notificaciones{
        controladorSaldoCuota.guardarSaldo(saldoAnterior);
    }

    public CobroCuota generarCobroCuota(Cuota cuota, Double abono, LocalDate fecha) throws Notificaciones{
       controladorCuota.actualizarEstadoCuota(cuota);
       CobroCuota cobroCuota = controladorCobroCuota.generarCobroCuota(cuota, abono, fecha);
       return cobroCuota; 
    }
    
    public void generarNuevoSaldo(CobroCuota cobroCuota, Double saldo) throws Notificaciones{
        SaldoCuota saldoCuota = new SaldoCuota(cobroCuota, saldo, "GENERADO");
        controladorSaldoCuota.guardarSaldo(saldoCuota);
    }

    public List<Cuota> getCuotasDeAlumno(Alumno unAlumno) throws Notificaciones{
        return controladorCuota.getCuotasDeAlumno(unAlumno);
    }

    public List<AsistenciaAlumno> getAsistenciasAlumnoDeHoy() throws Notificaciones {
        return controladorAsistenciaAlumno.getAsistenciasDeHoy();
    }
    
    public List<AsistenciaProfesor> getAsistenciasProfesorDeHoy() throws Notificaciones{
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

    public void cerrarCaja() throws Notificaciones{
        controladorCaja.cerrarCaja();
    }

    public List<Movimiento> getListaMovimientos()throws Notificaciones {
        return controladorMovimientos.getListaMovimientos();
    }

    public Cajadiaria dameCaja() throws Notificaciones {
        return controladorCaja.dameCaja();
    }

    public void altaMovimiento(Movimiento unMovimiento) throws Notificaciones {
        controladorMovimientos.altaMomiviento(unMovimiento);
    }

    public void generarReporte() throws Notificaciones{
        Document document = new Document();
        try{
            PdfWriter escritor = PdfWriter.getInstance(document, new FileOutputStream("src/gimnasio/imagenes/reporte.pdf"));
            document.open();
            
            try {
                Image logo = Image.getInstance("src/gimnasio/imagenes/CountryGymLogo.jpg");
                logo.setAbsolutePosition(450f,800f);
                logo.scaleAbsolute(100,30);
                document.add(logo);
            } catch (BadElementException | IOException ex) {
                ex.printStackTrace();
            }
            document.addTitle("Resumen de Movimientos");
            
            Font fuenteGrande = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new CMYKColor(255, 255, 255, 255));
            Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(255, 255, 255, 255));

            
            Paragraph titulo = new Paragraph("Resumen de Movimientos", fuenteGrande);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setExtraParagraphSpace(20);
            document.add(titulo);
            
            //En el constructor la cantidad de columnas
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);
            
            //Configura ancho de columnas
            float[] columnWidths = {0.7f, 1f, 3f, 0.55f, 0.5f};
            tabla.setWidths(columnWidths);
            
            PdfPCell celda1 = new PdfPCell(new Paragraph("N° Mov.", fuenteNegrita));
            celda1.setMinimumHeight(20);
            celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell celda2 = new PdfPCell(new Paragraph("Fecha",fuenteNegrita));
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell celda3 = new PdfPCell(new Paragraph("Detalle",fuenteNegrita));
            celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell celda4 = new PdfPCell(new Paragraph("Ingreso",fuenteNegrita));
            celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  
            PdfPCell celda5 = new PdfPCell(new Paragraph("Egreso",fuenteNegrita));
            celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            
            Double totalIngresos = 0.0;
            Double totalEgresos = 0.0;
            Double totalGanancia = 0.0;
            
            
            celda1 = new PdfPCell(new Paragraph("-----"));
            LocalDate hoy = LocalDate.now();
            celda2 = new PdfPCell(new Paragraph(hoy.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
            celda3 = new PdfPCell(new Paragraph("APERTURA DE CAJA"));
            celda4 = new PdfPCell(new Paragraph(String.valueOf(controladorCaja.dameCaja().getMontoactual())));
            totalIngresos += controladorCaja.dameCaja().getMontoactual();
            celda5 = new PdfPCell(new Paragraph(""));
            
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            
            for(Movimiento unMov:controladorMovimientos.getListaMovimientosDeHoy()){
                celda1 = new PdfPCell(new Paragraph(unMov.getIdmovimiento().toString()));
                LocalDate fecha = unMov.getHora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                celda2 = new PdfPCell(new Paragraph(fecha.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
                if(unMov.getMonto()>0){
                    celda3 = new PdfPCell(new Paragraph(unMov.getDetalle()));
                    celda4 = new PdfPCell(new Paragraph(unMov.getMonto().toString()));
                    totalIngresos += unMov.getMonto();
                    celda5 = new PdfPCell(new Paragraph(""));
                }else if(unMov.getMonto()<0){
                    Paragraph detalleIdent = new Paragraph(unMov.getDetalle());
                    celda3 = new PdfPCell(detalleIdent);
                    celda3.setIndent(50);
                    celda4 = new PdfPCell(new Paragraph(""));
                    celda5 = new PdfPCell(new Paragraph(String.valueOf(unMov.getMonto()*-1)));
                    totalEgresos += unMov.getMonto()*-1;
                }
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);
            }
            totalGanancia = totalIngresos - totalEgresos;
            
            PdfPTable tabla2 = new PdfPTable(3);
            tabla2.setWidthPercentage(100);
            tabla2.setSpacingBefore(10f);
            tabla2.setSpacingAfter(10f);
            
            //Configura ancho de columnas
            float[] anchoColumnas = {4.7f, 0.55f, 0.5f};
            tabla2.setWidths(anchoColumnas);
            
            PdfPCell celdaTotal = new PdfPCell(new Paragraph("TOTAL INGRESOS/EGRESOS", fuenteNegrita));
            PdfPCell celdaIngresos = new PdfPCell(new Paragraph(totalIngresos.toString(), fuenteNegrita));
            PdfPCell celdaEgresos = new PdfPCell(new Paragraph(totalEgresos.toString(), fuenteNegrita));
            
            tabla2.addCell(celdaTotal);
            tabla2.addCell(celdaIngresos);
            tabla2.addCell(celdaEgresos);
            
            celdaTotal = new PdfPCell(new Paragraph("DIFERENCIA", fuenteNegrita));
            celdaIngresos = new PdfPCell(new Paragraph(totalGanancia.toString(), fuenteNegrita));
            celdaIngresos.setColspan(2);
            
            tabla2.addCell(celdaTotal);
            tabla2.addCell(celdaIngresos);
            
            document.add(tabla);
            document.add(tabla2);
            document.close();
            escritor.close();
            File f = new File("src/gimnasio/imagenes/reporte.pdf");
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }catch(DocumentException | FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void generarReporteDias(LocalDate desde, LocalDate hasta) throws Notificaciones {
        Document document = new Document();
        try{
            PdfWriter escritor = PdfWriter.getInstance(document, new FileOutputStream("src/gimnasio/imagenes/reporteDias.pdf"));
            document.open();
            
            try {
                Image logo = Image.getInstance("src/gimnasio/imagenes/CountryGymLogo.jpg");
                logo.setAbsolutePosition(450f,800f);
                logo.scaleAbsolute(100,30);
                document.add(logo);
            } catch (BadElementException | IOException ex) {
                ex.printStackTrace();
            }
            document.addTitle("Resumen de Movimientos");
            
            Font fuenteGrande = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new CMYKColor(255, 255, 255, 255));
            Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(255, 255, 255, 255));
            
            Paragraph titulo = new Paragraph("Resumen de Movimientos", fuenteGrande);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setExtraParagraphSpace(20);
            document.add(titulo);
            
            //En el constructor la cantidad de columnas
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);
            
            //Configura ancho de columnas
            float[] columnWidths = {0.7f, 1f, 3f, 0.55f, 0.5f};
            tabla.setWidths(columnWidths);
            
            PdfPCell celda1 = new PdfPCell(new Paragraph("N° Mov.", fuenteNegrita));
            celda1.setMinimumHeight(20);
            celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell celda2 = new PdfPCell(new Paragraph("Fecha",fuenteNegrita));
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell celda3 = new PdfPCell(new Paragraph("Detalle",fuenteNegrita));
            celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell celda4 = new PdfPCell(new Paragraph("Ingreso",fuenteNegrita));
            celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                  
            PdfPCell celda5 = new PdfPCell(new Paragraph("Egreso",fuenteNegrita));
            celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            
            
            Double superTotalIngresos = 0.0;
            Double superTotalEgresos = 0.0;
            Double superTotalGanancia = 0.0;
            
            Double totalIngresos = 0.0;
            Double totalEgresos = 0.0;
            Double totalGanancia = 0.0;   
            
            LocalDate fechaAnterior = desde;
            for (Movimiento unMov : controladorMovimientos.getListaMovimientosDe(desde, hasta)) {
                celda1 = new PdfPCell(new Paragraph(unMov.getIdmovimiento().toString()));
                LocalDate fecha = unMov.getHora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                celda2 = new PdfPCell(new Paragraph(fecha.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
                if(unMov.getMonto()>0){
                    celda3 = new PdfPCell(new Paragraph(unMov.getDetalle()));
                    celda4 = new PdfPCell(new Paragraph(unMov.getMonto().toString()));
                    totalIngresos += unMov.getMonto();
                    celda5 = new PdfPCell(new Paragraph(""));
                }else if(unMov.getMonto()<0){
                    Paragraph detalleIdent = new Paragraph(unMov.getDetalle());
                    celda3 = new PdfPCell(detalleIdent);
                    celda3.setIndent(50);
                    celda4 = new PdfPCell(new Paragraph(""));
                    celda5 = new PdfPCell(new Paragraph(String.valueOf(unMov.getMonto()*-1)));
                    totalEgresos += unMov.getMonto()*-1;
                }
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);
            }
            totalGanancia = totalIngresos - totalEgresos;

            PdfPTable tabla2 = new PdfPTable(3);
            tabla2.setWidthPercentage(100);
            tabla2.setSpacingBefore(10f);
            tabla2.setSpacingAfter(10f);

            //Configura ancho de columnas
            float[] anchoColumnas = {4.7f, 0.55f, 0.5f};
            tabla2.setWidths(anchoColumnas);

            PdfPCell celdaTotal = new PdfPCell(new Paragraph("TOTAL INGRESOS/EGRESOS", fuenteNegrita));
            PdfPCell celdaIngresos = new PdfPCell(new Paragraph(totalIngresos.toString(), fuenteNegrita));
            PdfPCell celdaEgresos = new PdfPCell(new Paragraph(totalEgresos.toString(), fuenteNegrita));

            tabla2.addCell(celdaTotal);
            tabla2.addCell(celdaIngresos);
            tabla2.addCell(celdaEgresos);

            celdaTotal = new PdfPCell(new Paragraph("DIFERENCIA", fuenteNegrita));
            celdaIngresos = new PdfPCell(new Paragraph(totalGanancia.toString(), fuenteNegrita));
            celdaIngresos.setColspan(2);

            tabla2.addCell(celdaTotal);
            tabla2.addCell(celdaIngresos);

            //hacertablafinal
            document.add(tabla);
            document.add(tabla2);
            document.close();
            escritor.close();
            File f = new File("src/gimnasio/imagenes/reporteDias.pdf");
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }catch(DocumentException e){
            e.printStackTrace();
        }catch(FileNotFoundException e){
            throw new Notificaciones("Archivo bloqueado: Puede que este abierto un reporte ya generado, cierrelo e intente nuevamente");
        }
    }
}