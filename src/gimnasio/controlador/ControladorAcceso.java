/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.AsistenciaAlumno;
import gimnasio.modelo.AsistenciaProfesor;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.ClaseProfesor;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.HorarioAlumno;
import gimnasio.modelo.HorarioProfesor;
import gimnasio.modelo.Personal;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Usuario;
import gimnasio.vista.MainMenu;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Family
 */
public class ControladorAcceso {

    ControladorPrincipal miControlador;
    List<Alumno> listaAlumnos;
    List<Profesor> listaProfesores;
    List<Personal> listaPersonal;
    List<ClaseAlumno> listaClasesAlumnos;
    List<ClaseProfesor> listaClasesProfesores;
    List<HorarioAlumno> listaHorariosAlumnos;
    List<HorarioProfesor> listaHorariosProfesores;
    List<AsistenciaAlumno> listaAsistenciasAlumnos;
    List<AsistenciaProfesor> listaAsistenciasProfesores;
    List<Cuota> listaCuotas;

    public ControladorAcceso(ControladorPrincipal miControlador) {
        try {
            this.miControlador = miControlador;
            listaAlumnos = miControlador.getListaAlumnos();
            listaProfesores = miControlador.getListaProfesores();
            listaClasesAlumnos = miControlador.getListaClasesAlumnos();
            listaClasesProfesores = miControlador.getListaClaseProfesor();
            listaHorariosProfesores = miControlador.getListaHorarios();
            listaAsistenciasAlumnos = miControlador.getListaAsistenciaAlumno();
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, "Excepcion de inicializacion del controlador de acceso");
            ex.printStackTrace();
        }
    }

    public void verificarAcceso(JTextField texto, DefaultTableModel modeloTabla, JTable tabla, Usuario miUsuario) {
        try {
            System.out.println("Verificando acceso: ");

            //       listaAsistenciasProfesores = miControlador.getListaAsistenciaProfesor();

            LocalTime ahora = LocalTime.now();

            Set<Alumno> listaAlumnosUsuario = miUsuario.getAlumnos();
            Set<Profesor> listaProfesorUsuario = miUsuario.getProfesors();
            List<ClaseAlumno> listaBuscarClaseAlumno = new ArrayList<>();
            boolean acceso = false;
            boolean asistenciaManual = false;
            String textoMostrar = "USUARIO NO REGISTRADO";
            if(listaAlumnosUsuario.isEmpty()){
                System.out.println("ALUMNO SIN CLASES DETECTADO");
                textoMostrar = miUsuario.getNombreusuario() + "ES ALUMNO SIN CLASE";
            }
            if (listaAlumnosUsuario.size()==1) {
                System.out.println("ALUMNO DETECTADO");
                for (Alumno unAlu : listaAlumnosUsuario) {
                    System.out.println("For alumno: "+unAlu.getNombrealumno());
                    for (ClaseAlumno claseAlu : unAlu.getClaseAlumnos()) {
                        System.out.println("Correcto, agregando a listaBuscarClaseAlumno");
                        listaBuscarClaseAlumno.add(claseAlu);
                    }
                }
                if(listaBuscarClaseAlumno.isEmpty()){
                    System.out.println("NO SE AGREGO NINGUNA CLASEALUMNO");
                }
                if (listaBuscarClaseAlumno.size() == 1) {
                    System.out.println("EL ALUMNO TIENE 1 SOLA CLASE");
                    for (ClaseAlumno claseAlu : listaBuscarClaseAlumno) {
                        listaHorariosAlumnos = miControlador.getListaHorariosAlumno(claseAlu);
                        for (HorarioAlumno horario : listaHorariosAlumnos) {
                            System.out.println("Verificando horario de clase...");
                            LocalTime horarioInicio = Instant.ofEpochMilli(horario
                                    .getInicio()
                                    .getTime())
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalTime();
                            horarioInicio.minusMinutes(15);

                            LocalTime horarioFin = Instant.ofEpochMilli(horario
                                    .getFin()
                                    .getTime())
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalTime();
                            horarioFin.plusMinutes(15);
                            System.out.println("HORARIO ACTUAL: " + ahora);
                            System.out.println("HORARIO INICIO CLASE: " + horarioInicio);
                            System.out.println("HORARIO FIN CLASE: " + horarioFin);
                            if (horarioInicio.isBefore(ahora)
                                    && horarioFin.isAfter(ahora)) {
                                System.out.println("Correcto\n Verificando cantidad de asistencias...");
                                int contador = 0;

                                for (AsistenciaAlumno asistencia : listaAsistenciasAlumnos) {
                                    if (asistencia.getEstado().equalsIgnoreCase("ACTIVO")) {
                                        if (asistencia.getClaseAlumno().getIdclasealumno() == claseAlu.getIdclasealumno()) {
                                            contador++;
                                        }
                                    }
                                }
                                if (contador > claseAlu.getDiasPorSemana()) {
                                    JOptionPane.showMessageDialog(null, "El alumno supero la cantidad de clases habilitadas"
                                            + "\nDebe registrar una asistencia manual.");
                                    System.out.println("Supera asistencias maximas");
                                }

                                listaCuotas = miControlador.getCuotasDeAlumno(claseAlu.getAlumno());
                                int selector = listaCuotas.size();
                                System.out.println("cantidad de cuotas del alumno: " + selector);
                                Cuota cuota = null;
                                try {
                                    cuota = listaCuotas.get(selector - 2);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    textoMostrar = "Alumno no posee cuotas en condiciones, acceso denegado.";
                                    break;
                                }
                                System.out.println("id de la cuota seleccionada: " + cuota.getIdcuota());
                                System.out.println("estado de la cuota: " + cuota.getEstado());
                                LocalDate fecha = LocalDate.now();
                                LocalDate vencimiento = Instant.ofEpochMilli(cuota.getVencimiento().getTime())
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();
                                if (cuota.getEstado().equalsIgnoreCase("PAGADO")
                                        && fecha.isBefore(vencimiento.plusDays(3)
                                                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)))) {
                                    acceso = true;

                                    Object[] fila = new Object[3];
                                    fila[0] = miUsuario;
                                    Date ahorag = new Date();
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                                    fila[1] = sdf.format(ahorag);
                                    fila[2] = miUsuario.getEstado();
                                    modeloTabla.insertRow(0, fila);
                                    miControlador.nuevoIngresoPuerta(ahorag, miUsuario, modeloTabla, tabla);

                                    miControlador.altaAsistenciaAlumno(claseAlu, new Date());
                                    break;
                                }
                                if (cuota.getEstado().equalsIgnoreCase("GENERADO")) {
                                    textoMostrar = "Alumno adeuda cuota, acceso denegado.";
                                    break;
                                }
                            } else {
                                textoMostrar = "Alumno fuera de horario, acceso denegado.";
                                break;
                            }
                        }
                    }
                }
                if (listaBuscarClaseAlumno.size() > 1) {
                    System.out.println("EL ALUMNO TIENE MAS DE 1 CLASE, NECESITA ASISTENCIA MANUAL");
                    acceso = true;
                    Object[] fila = new Object[3];
                    fila[0] = miUsuario;
                    Date ahorag = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                    fila[1] = sdf.format(ahorag);
                    fila[2] = miUsuario.getEstado();
                    modeloTabla.insertRow(0, fila);
                    for(ClaseAlumno claseAlumno:listaBuscarClaseAlumno){
                        Alumno unAlu = claseAlumno.getAlumno();
                        ClaseProfesor claseProf = claseAlumno.getClaseProfesor();
                        listaCuotas = miControlador.getCuotasDeAlumno(unAlu);
                        List<Cuota>cuotasClaseProfesor =new ArrayList<>();
                        for(Cuota cuota:listaCuotas){
                            if(cuota.getClaseProfesor().getIdclaseprofesor()==claseProf.getIdclaseprofesor()){
                                cuotasClaseProfesor.add(cuota);
                            }
                        }
                        int selector = cuotasClaseProfesor.size();
                        System.out.println("cantidad de cuotas del alumno: " + selector + "en la clase: " + claseProf.getClase());
                        Cuota cuota = null;
                        try {
                            cuota = listaCuotas.get(selector - 2);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            textoMostrar = "Alumno no posee cuotas en condiciones, acceso denegado.";
                            break;
                        }          
                        System.out.println("id de la cuota seleccionada: " + cuota.getIdcuota());
                        System.out.println("estado de la cuota: " + cuota.getEstado());
                        LocalDate fecha = LocalDate.now();
                        LocalDate vencimiento = Instant.ofEpochMilli(cuota.getVencimiento().getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        if (cuota.getEstado().equalsIgnoreCase("PAGADO")
                                && fecha.isBefore(vencimiento.plusDays(3)
                                        .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)))) {
                            acceso = true;

                            miControlador.nuevoIngresoPuerta(ahorag, miUsuario, modeloTabla, tabla);
                            break;
                        }
                        if (cuota.getEstado().equalsIgnoreCase("GENERADO")) {
                            textoMostrar = "Alumno adeuda cuota, acceso denegado.";
                            break;
                        }                 
                        
                        
                    }
                    miControlador.nuevoIngresoPuerta(ahorag, miUsuario, modeloTabla, tabla);
                    asistenciaManual= true;
                }
            }
            if (listaProfesorUsuario.size()==1) {
                System.out.println("PROFESOR DETECTADO");
                Object[] fila = new Object[3];
                fila[0] = miUsuario;
                Date ahorag = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                fila[1] = sdf.format(ahorag);
                fila[2] = miUsuario.getEstado();
                modeloTabla.insertRow(0, fila);
                miControlador.nuevoIngresoPuerta(ahorag, miUsuario, modeloTabla, tabla);   
                acceso = true;
                //       "BUSCAR CLASE Y MARCAR ASISTENCIA"
                Set<Profesor> setProfesor = miUsuario.getProfesors();
                Profesor profesor = null;
                for(Profesor profeDB:miControlador.getListaProfesores()){
                    for(Profesor profe:setProfesor){
                        if(profeDB.getIdprofesor()==profe.getIdprofesor()){
                            profesor = profeDB;
                            break;
                        }
                    }
                }
                ClaseProfesor claseProfesor = null;
                Set<ClaseProfesor> setClases = profesor.getClaseProfesors();
                for(ClaseProfesor claseProfeDB:miControlador.getListaClaseProfesor()){
                    for(ClaseProfesor claseProfe:setClases){
                        if(claseProfeDB.getIdclaseprofesor()==claseProfe.getIdclaseprofesor()){
                            claseProfesor = claseProfeDB;
                        }
                    }
                }
                
                miControlador.altaAsistenciaProfesor(false,claseProfesor, new Date());
            }
            if (miUsuario.getEstado().equalsIgnoreCase("ADMIN")
                    || miUsuario.getEstado().equalsIgnoreCase("OPERADOR")
                    || miUsuario.getEstado().equalsIgnoreCase("NORMAL")) {
                System.out.println("PERSONAL DETECTADO, BIENVENIDO " + miUsuario.getNombreusuario());
                Object[] fila = new Object[3];
                fila[0] = miUsuario;
                Date ahorag = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                fila[1] = sdf.format(ahorag);
                fila[2] = miUsuario.getEstado();
                modeloTabla.insertRow(0, fila);
                miControlador.nuevoIngresoPuerta(ahorag, miUsuario, modeloTabla, tabla);
                acceso = true;
            }
            if(acceso){
                try {
                    MainMenu.rele.abrirPuerta();
                    if(asistenciaManual){
                                            
                    JOptionPane.showMessageDialog(null, "El alumno con usuario: "+miUsuario.getNombreusuario()+" tiene varias clases,"
                            + "\n debe registrarse manualmente a una de ellas");
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(null, textoMostrar);
                texto.setText(textoMostrar);
            }
            
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }

    }
}
