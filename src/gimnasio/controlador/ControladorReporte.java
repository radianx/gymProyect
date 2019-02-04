/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gimnasio.modelo.Cajadiaria;
import gimnasio.modelo.IngresosPuerta;
import gimnasio.modelo.Movimiento;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Family
 */
public class ControladorReporte {

    ControladorMovimiento controladorMovimiento;
    ControladorCaja controladorCaja;

    public ControladorReporte(ControladorMovimiento controladorMovimientos, ControladorCaja controladorCaja) {
        this.controladorCaja = controladorCaja;
        this.controladorMovimiento = controladorMovimientos;
    }

    public void generarReporteAsistencias(LocalDate desde, LocalDate hasta, List<IngresosPuerta> listaIngresos){
        Document document = new Document();
        try {
            PdfWriter escritor = PdfWriter.getInstance(document, new FileOutputStream("reporteIngresosEgresos.pdf"));
            document.open();

            document.addTitle("Resumen de Ingresos y Egresos por Puerta");

            Font fuenteGrande = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new CMYKColor(255, 255, 255, 255));
            Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(255, 255, 255, 255));

            LocalDate fechaGeneracion = LocalDate.now();
            Paragraph fechaG = new Paragraph(fechaGeneracion.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
            document.add(fechaG);

            Paragraph titulo = new Paragraph("Resumen de Ingresos y Egresos por Puerta", fuenteGrande);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setExtraParagraphSpace(20);
            document.add(titulo);
        
            //En el constructor la cantidad de columnas
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);
            tabla.setSpacingAfter(10f);

            //Configura ancho de columnas
            float[] columnWidths = {3f, 3f, 3f};
            tabla.setWidths(columnWidths);

            PdfPCell celda1 = new PdfPCell(new Paragraph("Usuario", fuenteNegrita));
            celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celda2 = new PdfPCell(new Paragraph("Hora Ingreso", fuenteNegrita));
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celda3 = new PdfPCell(new Paragraph("Hora Egreso", fuenteNegrita));
            celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            
            IngresosPuerta primer = listaIngresos.get(0);
            
            LocalDate elDia = Instant.ofEpochMilli(primer.getHoraIngreso().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            
            Paragraph fechaPrimer = new Paragraph(elDia.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
            document.add(fechaPrimer);
            
            for (IngresosPuerta ingreso : listaIngresos) {
                LocalDate fecha = Instant.ofEpochMilli(ingreso.getHoraIngreso().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                
                if (fecha.isEqual(elDia)) {
                    if (fecha.isAfter(desde.minusDays(1)) && fecha.isBefore(hasta.plusDays(1))) {
                        celda1 = new PdfPCell(new Paragraph(ingreso.getUsuario().getNombreusuario()));
                        if (ingreso.getHoraIngreso() != null) {
                            LocalTime hIngreso = Instant.ofEpochMilli(ingreso.getHoraIngreso().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                            celda2 = new PdfPCell(new Paragraph(hIngreso.format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
                        } else {
                            celda2 = new PdfPCell(new Paragraph("Mal registro de ingreso"));
                        }
                        if (ingreso.getHoraEgreso() != null) {
                            LocalTime hEgreso = Instant.ofEpochMilli(ingreso.getHoraEgreso().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                            celda3 = new PdfPCell(new Paragraph(hEgreso.format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
                        } else {
                            celda3 = new PdfPCell(new Paragraph("egreso no registrado"));
                        }

                        tabla.addCell(celda1);
                        tabla.addCell(celda2);
                        tabla.addCell(celda3);
                    }
                }else{
                    document.add(tabla);
                    tabla = new PdfPTable(3);
                    tabla.setWidthPercentage(100);
                    tabla.setSpacingBefore(10f);
                    tabla.setSpacingAfter(10f);

                    //Configura ancho de columnas
                    tabla.setWidths(columnWidths);

                    celda1 = new PdfPCell(new Paragraph("Usuario", fuenteNegrita));
                    celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    celda2 = new PdfPCell(new Paragraph("Hora Ingreso", fuenteNegrita));
                    celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    celda3 = new PdfPCell(new Paragraph("Hora Egreso", fuenteNegrita));
                    celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    tabla.addCell(celda1);
                    tabla.addCell(celda2);
                    tabla.addCell(celda3);
                    
                    
                    elDia = Instant.ofEpochMilli(ingreso.getHoraIngreso().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

                    fechaPrimer = new Paragraph(elDia.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
                    document.add(fechaPrimer);

                    celda1 = new PdfPCell(new Paragraph(ingreso.getUsuario().getNombreusuario()));
                    if (ingreso.getHoraIngreso() != null) {
                        LocalTime hIngreso = Instant.ofEpochMilli(ingreso.getHoraIngreso().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                        celda2 = new PdfPCell(new Paragraph(hIngreso.format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
                    } else {
                        celda2 = new PdfPCell(new Paragraph("Mal registro de ingreso"));
                    }
                    if (ingreso.getHoraEgreso() != null) {
                        LocalTime hEgreso = Instant.ofEpochMilli(ingreso.getHoraEgreso().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                        celda3 = new PdfPCell(new Paragraph(hEgreso.format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
                    } else {
                        celda3 = new PdfPCell(new Paragraph("egreso no registrado"));
                    }

                    tabla.addCell(celda1);
                    tabla.addCell(celda2);
                    tabla.addCell(celda3);
                }
            }
            document.add(tabla);
            document.close();

            escritor.close();
            File f = new File("reporteIngresosEgresos.pdf");

            try {
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
}
 


  
    
    

    public void generarReporte(boolean dias, List<Cajadiaria> cajas, List<Movimiento> listaMovimientosDeHoy) {
        Double superIngreso = 0.0;
        Double superEgreso = 0.0;
        Double superTotal = 0.0;
        Document document = new Document();
        try {
            PdfWriter escritor = PdfWriter.getInstance(document, new FileOutputStream("reporte.pdf"));
            document.open();

            document.addTitle("Resumen de Movimientos");

            Font fuenteGrande = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new CMYKColor(255, 255, 255, 255));
            Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(255, 255, 255, 255));

            LocalDate fechaGeneracion = LocalDate.now();
            Paragraph fechaG = new Paragraph(fechaGeneracion.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
            document.add(fechaG);

            Paragraph titulo = new Paragraph("Resumen de Movimientos", fuenteGrande);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setExtraParagraphSpace(20);
            document.add(titulo);
            boolean stop = false;
            if (!cajas.isEmpty()) {
                for (Cajadiaria caja : cajas) {

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

                    PdfPCell celda2 = new PdfPCell(new Paragraph("Fecha", fuenteNegrita));
                    celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell celda3 = new PdfPCell(new Paragraph("Detalle", fuenteNegrita));
                    celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell celda4 = new PdfPCell(new Paragraph("Ingreso", fuenteNegrita));
                    celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    celda4.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell celda5 = new PdfPCell(new Paragraph("Egreso", fuenteNegrita));
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
                    LocalDate hoy = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalTime hora = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                    celda2 = new PdfPCell(new Paragraph(hoy.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
                    celda3 = new PdfPCell(new Paragraph("APERTURA DE CAJA\nHORA:" + hora.format(DateTimeFormatter.ofPattern("HH:mm")) + "\nUsuario: " + caja.getEstado()));
                    celda4 = new PdfPCell(new Paragraph(String.valueOf(caja.getMontoactual())));
                    totalIngresos += caja.getMontoactual();
                    celda5 = new PdfPCell(new Paragraph(""));

                    tabla.addCell(celda1);
                    tabla.addCell(celda2);
                    tabla.addCell(celda3);
                    tabla.addCell(celda4);
                    tabla.addCell(celda5);

                    for (Movimiento unMov : listaMovimientosDeHoy) {
                        if (unMov.getCaja().getIdcaja() == caja.getIdcaja()) {
                            LocalTime horaMov = Instant.ofEpochMilli(unMov.getHora().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                            LocalTime aperturaCaja = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                            LocalTime cierreCaja;
                            if(caja.getCierre()!=null){
                                cierreCaja = Instant.ofEpochMilli(caja.getCierre().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                            }else{
                                cierreCaja = LocalTime.of(23, 59, 59);
                            } 
                            if (horaMov.isAfter(aperturaCaja) && horaMov.isBefore(cierreCaja)) {
                                celda1 = new PdfPCell(new Paragraph(unMov.getIdmovimiento().toString()));
                                LocalDate fecha = unMov.getHora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                celda2 = new PdfPCell(new Paragraph(fecha.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
                                if (unMov.getMonto() > 0) {
                                    celda3 = new PdfPCell(new Paragraph(unMov.getDetalle()));
                                    celda4 = new PdfPCell(new Paragraph(unMov.getMonto().toString()));
                                    totalIngresos += unMov.getMonto();
                                    celda5 = new PdfPCell(new Paragraph(""));
                                } else if (unMov.getMonto() < 0) {
                                    Paragraph detalleIdent = new Paragraph(unMov.getDetalle());
                                    celda3 = new PdfPCell(detalleIdent);
                                    celda3.setIndent(50);
                                    celda4 = new PdfPCell(new Paragraph(""));
                                    celda5 = new PdfPCell(new Paragraph(String.valueOf(unMov.getMonto() * -1)));
                                    totalEgresos += unMov.getMonto() * -1;
                                }
                                tabla.addCell(celda1);
                                tabla.addCell(celda2);
                                tabla.addCell(celda3);
                                tabla.addCell(celda4);
                                tabla.addCell(celda5);
                            }
                        }
                    }
                    if (!stop) {
                        if(caja.getCierre()!=null){
                        celda1 = new PdfPCell(new Paragraph("-----"));
                        hora = Instant.ofEpochMilli(caja.getCierre().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
                        celda2 = new PdfPCell(new Paragraph(hoy.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
                        celda3 = new PdfPCell(new Paragraph("CIERRE DE CAJA\nHORA: " + hora.format(DateTimeFormatter.ofPattern("HH:mm")) + "\nUsuario: " + caja.getEstado()));
                        celda4 = new PdfPCell(new Paragraph(""));
                        celda5 = new PdfPCell(new Paragraph(""));

                        tabla.addCell(celda1);
                        tabla.addCell(celda2);
                        tabla.addCell(celda3);
                        tabla.addCell(celda4);
                        tabla.addCell(celda5);

                        totalGanancia = totalIngresos - totalEgresos;

                        superIngreso += totalIngresos;
                        superEgreso += totalEgresos;
                        
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
                        }else{
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            sdf.applyPattern("dd/MM/yyyy HH:mm");
                            JOptionPane.showMessageDialog(null, "La caja abierta en fecha: " + sdf.format(caja.getApertura()) + "\nPor el usuario: " + caja.getEstado() + "\nNo se cerró aún.\nSe Mostrarán los últimos movimientos");
                            totalGanancia = totalIngresos - totalEgresos;

                            superIngreso += totalIngresos;
                            superEgreso += totalEgresos;

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
                        }
                    }
                }
                if(dias){
                    superTotal = superIngreso - superEgreso;
                        PdfPTable tabla3 = new PdfPTable(3);
                        tabla3.setWidthPercentage(100);
                        tabla3.setSpacingBefore(10f);
                        tabla3.setSpacingAfter(10f);

                        //Configura ancho de columnas
                        float[] anchoColumnas = {4.7f, 0.55f, 0.5f};
                        tabla3.setWidths(anchoColumnas);

                        PdfPCell celdaTotal = new PdfPCell(new Paragraph("TOTAL ACUMULADO INGRESOS/EGRESOS", fuenteNegrita));
                        PdfPCell celdaIngresos = new PdfPCell(new Paragraph(superIngreso.toString(), fuenteNegrita));
                        PdfPCell celdaEgresos = new PdfPCell(new Paragraph(superEgreso.toString(), fuenteNegrita));

                        tabla3.addCell(celdaTotal);
                        tabla3.addCell(celdaIngresos);
                        tabla3.addCell(celdaEgresos);

                        celdaTotal = new PdfPCell(new Paragraph("SALDO ACUMULADO", fuenteNegrita));
                        celdaIngresos = new PdfPCell(new Paragraph(superTotal.toString(), fuenteNegrita));
                        celdaIngresos.setColspan(2);

                        tabla3.addCell(celdaTotal);
                        tabla3.addCell(celdaIngresos);

                        document.add(tabla3);
                }
                
            }
            document.close();
            escritor.close();
            File f = new File("reporte.pdf");
            try {
                if(!stop) Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (DocumentException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se puede acceder al archivo, se encuentra abierto un PDF con el mismo nombre.");
            e.printStackTrace();
        }
    }

    public void generarReporteMorosos() {
//        Document document = new Document();
//        try {
//            PdfWriter escritor = PdfWriter.getInstance(document, new FileOutputStream("reporteMorosos.pdf"));
//            document.open();
//
//            document.addTitle("Reporte de Cuotas Vencidas");
//
//            Font fuenteGrande = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new CMYKColor(255, 255, 255, 255));
//            Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(255, 255, 255, 255));
//
//            LocalDate fechaGeneracion = LocalDate.now();
//            Paragraph fechaG = new Paragraph(fechaGeneracion.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
//            document.add(fechaG);
//
//            Paragraph titulo = new Paragraph("Resumen de Cuotas Vencidas", fuenteGrande);
//            titulo.setAlignment(Element.ALIGN_CENTER);
//            titulo.setExtraParagraphSpace(20);
//            document.add(titulo);
//            boolean stop = false;
//            if (!cajas.isEmpty()) {
//                for (Cajadiaria caja : cajas) {
//
//                    //En el constructor la cantidad de columnas
//                    PdfPTable tabla = new PdfPTable(5);
//                    tabla.setWidthPercentage(100);
//                    tabla.setSpacingBefore(10f);
//                    tabla.setSpacingAfter(10f);
//
//                    //Configura ancho de columnas
//                    float[] columnWidths = {0.7f, 1f, 3f, 0.55f, 0.5f};
//                    tabla.setWidths(columnWidths);
//
//                    PdfPCell celda1 = new PdfPCell(new Paragraph("N° Mov.", fuenteNegrita));
//                    celda1.setMinimumHeight(20);
//                    celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//                    PdfPCell celda2 = new PdfPCell(new Paragraph("Fecha", fuenteNegrita));
//                    celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//                    PdfPCell celda3 = new PdfPCell(new Paragraph("Detalle", fuenteNegrita));
//                    celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//                    PdfPCell celda4 = new PdfPCell(new Paragraph("Ingreso", fuenteNegrita));
//                    celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda4.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//                    PdfPCell celda5 = new PdfPCell(new Paragraph("Egreso", fuenteNegrita));
//                    celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    celda5.setVerticalAlignment(Element.ALIGN_MIDDLE);
//
//                    tabla.addCell(celda1);
//                    tabla.addCell(celda2);
//                    tabla.addCell(celda3);
//                    tabla.addCell(celda4);
//                    tabla.addCell(celda5);
//
//                    Double totalIngresos = 0.0;
//                    Double totalEgresos = 0.0;
//                    Double totalGanancia = 0.0;
//
//                    celda1 = new PdfPCell(new Paragraph("-----"));
//                    LocalDate hoy = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
//                    LocalTime hora = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
//                    celda2 = new PdfPCell(new Paragraph(hoy.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
//                    celda3 = new PdfPCell(new Paragraph("APERTURA DE CAJA\nHORA:" + hora.format(DateTimeFormatter.ofPattern("HH:mm")) + "\nUsuario: " + caja.getEstado()));
//                    celda4 = new PdfPCell(new Paragraph(String.valueOf(caja.getMontoactual())));
//                    totalIngresos += caja.getMontoactual();
//                    celda5 = new PdfPCell(new Paragraph(""));
//
//                    tabla.addCell(celda1);
//                    tabla.addCell(celda2);
//                    tabla.addCell(celda3);
//                    tabla.addCell(celda4);
//                    tabla.addCell(celda5);
//
//                    for (Movimiento unMov : listaMovimientosDeHoy) {
//                        if (unMov.getCaja().getIdcaja() == caja.getIdcaja()) {
//                            LocalTime horaMov = Instant.ofEpochMilli(unMov.getHora().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
//                            LocalTime aperturaCaja = Instant.ofEpochMilli(caja.getApertura().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
//                            LocalTime cierreCaja;
//                            if(caja.getCierre()!=null){
//                                cierreCaja = Instant.ofEpochMilli(caja.getCierre().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
//                            }else{
//                                cierreCaja = LocalTime.of(23, 59, 59);
//                            } 
//                            if (horaMov.isAfter(aperturaCaja) && horaMov.isBefore(cierreCaja)) {
//                                celda1 = new PdfPCell(new Paragraph(unMov.getIdmovimiento().toString()));
//                                LocalDate fecha = unMov.getHora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                                celda2 = new PdfPCell(new Paragraph(fecha.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
//                                if (unMov.getMonto() > 0) {
//                                    celda3 = new PdfPCell(new Paragraph(unMov.getDetalle()));
//                                    celda4 = new PdfPCell(new Paragraph(unMov.getMonto().toString()));
//                                    totalIngresos += unMov.getMonto();
//                                    celda5 = new PdfPCell(new Paragraph(""));
//                                } else if (unMov.getMonto() < 0) {
//                                    Paragraph detalleIdent = new Paragraph(unMov.getDetalle());
//                                    celda3 = new PdfPCell(detalleIdent);
//                                    celda3.setIndent(50);
//                                    celda4 = new PdfPCell(new Paragraph(""));
//                                    celda5 = new PdfPCell(new Paragraph(String.valueOf(unMov.getMonto() * -1)));
//                                    totalEgresos += unMov.getMonto() * -1;
//                                }
//                                tabla.addCell(celda1);
//                                tabla.addCell(celda2);
//                                tabla.addCell(celda3);
//                                tabla.addCell(celda4);
//                                tabla.addCell(celda5);
//                            }
//                        }
//                    }
//                    if (!stop) {
//                        if(caja.getCierre()!=null){
//                        celda1 = new PdfPCell(new Paragraph("-----"));
//                        hora = Instant.ofEpochMilli(caja.getCierre().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
//                        celda2 = new PdfPCell(new Paragraph(hoy.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
//                        celda3 = new PdfPCell(new Paragraph("CIERRE DE CAJA\nHORA: " + hora.format(DateTimeFormatter.ofPattern("HH:mm")) + "\nUsuario: " + caja.getEstado()));
//                        celda4 = new PdfPCell(new Paragraph(""));
//                        celda5 = new PdfPCell(new Paragraph(""));
//
//                        tabla.addCell(celda1);
//                        tabla.addCell(celda2);
//                        tabla.addCell(celda3);
//                        tabla.addCell(celda4);
//                        tabla.addCell(celda5);
//
//                        totalGanancia = totalIngresos - totalEgresos;
//
//                        superIngreso += totalIngresos;
//                        superEgreso += totalEgresos;
//                        
//                        PdfPTable tabla2 = new PdfPTable(3);
//                        tabla2.setWidthPercentage(100);
//                        tabla2.setSpacingBefore(10f);
//                        tabla2.setSpacingAfter(10f);
//
//                        //Configura ancho de columnas
//                        float[] anchoColumnas = {4.7f, 0.55f, 0.5f};
//                        tabla2.setWidths(anchoColumnas);
//
//                        PdfPCell celdaTotal = new PdfPCell(new Paragraph("TOTAL INGRESOS/EGRESOS", fuenteNegrita));
//                        PdfPCell celdaIngresos = new PdfPCell(new Paragraph(totalIngresos.toString(), fuenteNegrita));
//                        PdfPCell celdaEgresos = new PdfPCell(new Paragraph(totalEgresos.toString(), fuenteNegrita));
//
//                        tabla2.addCell(celdaTotal);
//                        tabla2.addCell(celdaIngresos);
//                        tabla2.addCell(celdaEgresos);
//
//                        celdaTotal = new PdfPCell(new Paragraph("DIFERENCIA", fuenteNegrita));
//                        celdaIngresos = new PdfPCell(new Paragraph(totalGanancia.toString(), fuenteNegrita));
//                        celdaIngresos.setColspan(2);
//
//                        tabla2.addCell(celdaTotal);
//                        tabla2.addCell(celdaIngresos);
//
//                        document.add(tabla);
//                        document.add(tabla2);
//                        }else{
//                            SimpleDateFormat sdf = new SimpleDateFormat();
//                            sdf.applyPattern("dd/MM/yyyy HH:mm");
//                            JOptionPane.showMessageDialog(null, "La caja abierta en fecha: " + sdf.format(caja.getApertura()) + "\nPor el usuario: " + caja.getEstado() + "\nNo se cerró aún.\nSe Mostrarán los últimos movimientos");
//                            totalGanancia = totalIngresos - totalEgresos;
//
//                            superIngreso += totalIngresos;
//                            superEgreso += totalEgresos;
//
//                            PdfPTable tabla2 = new PdfPTable(3);
//                            tabla2.setWidthPercentage(100);
//                            tabla2.setSpacingBefore(10f);
//                            tabla2.setSpacingAfter(10f);
//
//                            //Configura ancho de columnas
//                            float[] anchoColumnas = {4.7f, 0.55f, 0.5f};
//                            tabla2.setWidths(anchoColumnas);
//
//                            PdfPCell celdaTotal = new PdfPCell(new Paragraph("TOTAL INGRESOS/EGRESOS", fuenteNegrita));
//                            PdfPCell celdaIngresos = new PdfPCell(new Paragraph(totalIngresos.toString(), fuenteNegrita));
//                            PdfPCell celdaEgresos = new PdfPCell(new Paragraph(totalEgresos.toString(), fuenteNegrita));
//
//                            tabla2.addCell(celdaTotal);
//                            tabla2.addCell(celdaIngresos);
//                            tabla2.addCell(celdaEgresos);
//
//                            celdaTotal = new PdfPCell(new Paragraph("DIFERENCIA", fuenteNegrita));
//                            celdaIngresos = new PdfPCell(new Paragraph(totalGanancia.toString(), fuenteNegrita));
//                            celdaIngresos.setColspan(2);
//
//                            tabla2.addCell(celdaTotal);
//                            tabla2.addCell(celdaIngresos);
//
//                            document.add(tabla);
//                            document.add(tabla2);
//                        }
//                    }
//                }
//                if(dias){
//                    superTotal = superIngreso - superEgreso;
//                        PdfPTable tabla3 = new PdfPTable(3);
//                        tabla3.setWidthPercentage(100);
//                        tabla3.setSpacingBefore(10f);
//                        tabla3.setSpacingAfter(10f);
//
//                        //Configura ancho de columnas
//                        float[] anchoColumnas = {4.7f, 0.55f, 0.5f};
//                        tabla3.setWidths(anchoColumnas);
//
//                        PdfPCell celdaTotal = new PdfPCell(new Paragraph("TOTAL ACUMULADO INGRESOS/EGRESOS", fuenteNegrita));
//                        PdfPCell celdaIngresos = new PdfPCell(new Paragraph(superIngreso.toString(), fuenteNegrita));
//                        PdfPCell celdaEgresos = new PdfPCell(new Paragraph(superEgreso.toString(), fuenteNegrita));
//
//                        tabla3.addCell(celdaTotal);
//                        tabla3.addCell(celdaIngresos);
//                        tabla3.addCell(celdaEgresos);
//
//                        celdaTotal = new PdfPCell(new Paragraph("SALDO ACUMULADO", fuenteNegrita));
//                        celdaIngresos = new PdfPCell(new Paragraph(superTotal.toString(), fuenteNegrita));
//                        celdaIngresos.setColspan(2);
//
//                        tabla3.addCell(celdaTotal);
//                        tabla3.addCell(celdaIngresos);
//
//                        document.add(tabla3);
//                }
//                
//            }
//            document.close();
//            escritor.close();
//            File f = new File("reporte.pdf");
//            try {
//                if(!stop) Desktop.getDesktop().open(f);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        } catch (DocumentException | FileNotFoundException e) {
//            JOptionPane.showMessageDialog(null, "No se puede acceder al archivo, se encuentra abierto un PDF con el mismo nombre.");
//            e.printStackTrace();
//        }
    }

}
