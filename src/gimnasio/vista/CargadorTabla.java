/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorPrincipal;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.AsistenciaAlumno;
import gimnasio.modelo.Cargo;
import gimnasio.modelo.Clase;
import gimnasio.modelo.ClaseAlumno;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Modulo;
import gimnasio.modelo.Obrasocial;
import gimnasio.modelo.PagoProfesor;
import gimnasio.modelo.Personal;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import gimnasio.modelo.SaldoCuota;
import gimnasio.modelo.Saldopagoprofesor;
import gimnasio.modelo.Sector;
import gimnasio.modelo.Usuario;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Family
 */
public abstract class CargadorTabla {
    
    public static void usuariosActivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        Object[] fila = new Object[1];

        for (Usuario miUsuario : controlador.getListaUsuarios()) {
            if (miUsuario.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = miUsuario;
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }
    
    public static void usuariosInactivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
                DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        Object[] fila = new Object[1];

        for (Usuario miUsuario : controlador.getListaUsuarios()) {
            if (miUsuario.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = miUsuario;
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }
    
    public static void cargosActivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Descripcion");
            Object[] fila = new Object[2];

            try {
                for (Cargo unCargo : controlador.getListaCargos()) {
                    if (unCargo.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = unCargo;
                        fila[1] = unCargo.getDescripcioncargo();
                        modeloTabla.addRow(fila);
                    }
                }
            } catch (NullPointerException ex) {
                System.err.println("Error al cargar Cargos: " + ex.getMessage());
            }
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }
    
    public static void cargosInactivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Descripcion");
            Object[] fila = new Object[2];

            try {
                for (Cargo unCargo : controlador.getListaCargos()) {
                    if (unCargo.getEstado().equalsIgnoreCase("INACTIVO")) {
                        fila[0] = unCargo;
                        fila[1] = unCargo.getDescripcioncargo();
                        modeloTabla.addRow(fila);
                    }
                }
            } catch (NullPointerException ex) {
                System.err.println("Error al cargar Cargos: " + ex.getMessage());
            }
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }
    
    public static void modalidadesActivas(JTable tabla, ControladorPrincipal controlador) throws Notificaciones {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripcion");
        Object[] fila = new Object[2];

        for (Modalidad miModalidad : controlador.getListaModalidades()) {
            if (miModalidad.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = miModalidad;
                fila[1] = miModalidad.getDescripcionmodalidad();
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }
    
    public static void modalidadesInactivas(JTable tabla, ControladorPrincipal controlador) throws Notificaciones {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Descripcion");
        Object[] fila = new Object[2];

        for (Modalidad miModalidad : controlador.getListaModalidades()) {
            if (miModalidad.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = miModalidad;
                fila[1] = miModalidad.getDescripcionmodalidad();
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }    
    
    public static void obraSocialesActivas(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            Object[] fila = new Object[1];

                for (Obrasocial miOS : controlador.getListaObraSociales()) {
                    if (miOS.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = miOS;
                        modeloTabla.addRow(fila);
                    }
                }
                
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }
    
    public static void obraSocialesInactivas(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            Object[] fila = new Object[1];

                for (Obrasocial miOS : controlador.getListaObraSociales()) {
                    if (miOS.getEstado().equalsIgnoreCase("INACTIVO")) {
                        fila[0] = miOS;
                        modeloTabla.addRow(fila);
                    }
                }
                
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }

    public static void profesoresActivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Apellido");
            modeloTabla.addColumn("Usuario");
            Object[] fila = new Object[3];
            
            for (Profesor miProfesor : controlador.getListaProfesores()) {
                if (miProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                    fila[0] = miProfesor;
                    fila[1] = miProfesor.getApellidoprofesor();
                    fila[2] = miProfesor.getUsuario().getNombreusuario();
                    modeloTabla.addRow(fila);
                }
            }
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }
    
    public static void profesoresInactivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Apellido");
            modeloTabla.addColumn("Usuario");
            Object[] fila = new Object[3];
            
            for (Profesor miProfesor : controlador.getListaProfesores()) {
                if (miProfesor.getEstado().equalsIgnoreCase("INACTIVO")) {
                    fila[0] = miProfesor;
                    fila[1] = miProfesor.getApellidoprofesor();
                    fila[2] = miProfesor.getUsuario().getNombreusuario();
                    modeloTabla.addRow(fila);
                }
            }
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }
    
    public static void sectoresActivos(JTable tabla, ControladorPrincipal controlador){
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Descripcion");
            Object[] fila = new Object[2];

                for (Sector miSector : controlador.getListaSectores()) {
                    if (miSector.getEstado().equalsIgnoreCase("ACTIVO")) {
                        fila[0] = miSector;
                        fila[1] = miSector.getDescripcionsector();
                        modeloTabla.addRow(fila);
                    }
                }
                
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }
    
    public static void sectoresInactivos(JTable tabla, ControladorPrincipal controlador){
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Descripcion");
            Object[] fila = new Object[2];

                for (Sector miSector : controlador.getListaSectores()) {
                    if (miSector.getEstado().equalsIgnoreCase("INACTIVO")) {
                        fila[0] = miSector;
                        fila[1] = miSector.getDescripcionsector();
                        modeloTabla.addRow(fila);
                    }
                }
                
            tabla.setModel(modeloTabla);
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
            tabla.setRowSorter(rowSorter);
    }    
    
    public static void alumnosActivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Usuario");
        Object[] fila = new Object[3];

        for (Alumno miAlumno : controlador.getListaAlumnos()) {
            if (miAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = miAlumno;
                fila[1] = miAlumno.getApellidoalumno();
                fila[2] = miAlumno.getUsuario().getNombreusuario();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }
    
    public static void alumnosInactivos(JTable tabla, ControladorPrincipal controlador) throws Notificaciones{
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Usuario");
        Object[] fila = new Object[3];

        for (Alumno miAlumno : controlador.getListaAlumnos()) {
            if (miAlumno.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = miAlumno;
                fila[1] = miAlumno.getApellidoalumno();
                fila[2] = miAlumno.getUsuario().getNombreusuario();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }    

    public static void clasesAlumnosActivos(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Clase");
        modeloTabla.addColumn("Nombre Alumno");
        modeloTabla.addColumn("Apellido Alumno");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("¿Obra Social?");
        modeloTabla.addColumn("Profesor");
        Object[] fila = new Object[7];

        for (ClaseAlumno claseAlumno : controlador.getListaClasesAlumnos()) {
            if (claseAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = claseAlumno;
                fila[1] = claseAlumno.getAlumno().getNombrealumno();
                fila[2] = claseAlumno.getAlumno().getApellidoalumno();
                fila[3] = claseAlumno.getAlumno().getContacto().getEmail1();
                fila[4] = claseAlumno.getAlumno().getContacto().getTelefono1();
                if(claseAlumno.getAlumno().getObrasocial()!=null){
                    fila[5] = claseAlumno.getAlumno().getObrasocial().getNombreobrasocial();
                }else{
                    fila[5] = "NO";
                }
                fila[6] = claseAlumno.getClaseProfesor().getProfesor().getNombreprofesor();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }
    
public static void clasesAlumnosInactivos(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Clase");
        modeloTabla.addColumn("Nombre Alumno");
        modeloTabla.addColumn("Apellido Alumno");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("¿Obra Social?");
        modeloTabla.addColumn("Profesor");
        Object[] fila = new Object[7];

        for (ClaseAlumno claseAlumno : controlador.getListaClasesAlumnos()) {
            if (claseAlumno.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = claseAlumno;
                fila[1] = claseAlumno.getAlumno().getNombrealumno();
                fila[2] = claseAlumno.getAlumno().getApellidoalumno();
                fila[3] = claseAlumno.getAlumno().getContacto().getEmail1();
                fila[4] = claseAlumno.getAlumno().getContacto().getTelefono1();
                if(claseAlumno.getAlumno().getObrasocial()!=null){
                    fila[5] = claseAlumno.getAlumno().getObrasocial().getNombreobrasocial();
                }else{
                    fila[5] = "NO";
                }
                fila[6] = claseAlumno.getClaseProfesor().getProfesor().getNombreprofesor();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }
    
    public static void profesorModalidadActivos(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Profesor");
        modeloTabla.addColumn("Apellido Profesor");
        modeloTabla.addColumn("Modalidad");
        modeloTabla.addColumn("Precio/Hora");
        Object[] fila = new Object[3];

        for (Profesormodalidad profesorModalidad : controlador.getListaProfesorModalidades()) {
            if (profesorModalidad.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = profesorModalidad;
                fila[1] = profesorModalidad.getProfesor().getApellidoprofesor();
                fila[2] = profesorModalidad.getModalidad().getNombremodalidad();
                fila[3] = profesorModalidad.getPreciohora();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }

    public static void profesorModalidadInactivo(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Profesor");
        modeloTabla.addColumn("Apellido Profesor");
        modeloTabla.addColumn("Modalidad");
        modeloTabla.addColumn("Precio/Hora");
        Object[] fila = new Object[4];

        for (Profesormodalidad profesorModalidad : controlador.getListaProfesorModalidades()) {
            if (profesorModalidad.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = profesorModalidad;
                fila[1] = profesorModalidad.getProfesor().getApellidoprofesor();
                fila[2] = profesorModalidad.getModalidad().getNombremodalidad();
                fila[3] = profesorModalidad.getPreciohora();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
    }
    
    public static void clasesActivas(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Alumnos Maximos");
        modeloTabla.addColumn("Descripcion");
        Object[] fila = new Object[3];
        
        for (Clase clase : controlador.getListaClases()) {
            if (clase.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = clase;
                fila[1] = clase.getAlumnosmaximo();
                fila[2] = clase.getDescripcionclase();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
                
    }
    
    public static void clasesInactivas(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Alumnos Maximos");
        modeloTabla.addColumn("Descripcion");
        Object[] fila = new Object[3];
        
        for (Clase clase : controlador.getListaClases()) {
            if (clase.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = clase;
                fila[1] = clase.getAlumnosmaximo();
                fila[2] = clase.getDescripcionclase();
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
                
    }
         
    public static void asistenciaAlumnosActivos(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Hora");
        Object[] fila = new Object[2];
        String patron = "HH:mm dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (AsistenciaAlumno asistenciaAlumno : controlador.getListaAsistenciaAlumno()) {
            if (asistenciaAlumno.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = asistenciaAlumno;
                fila[1] = f.format(asistenciaAlumno.getSalida());
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);            
    } 
     
    public static void asistenciaAlumnosInactivos(JTable tabla, ControladorPrincipal controlador){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Hora");
        Object[] fila = new Object[2];
        String patron = "HH:mm dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (AsistenciaAlumno asistenciaAlumno : controlador.getListaAsistenciaAlumno()) {
            if (asistenciaAlumno.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = asistenciaAlumno;
                fila[1] = f.format(asistenciaAlumno.getSalida());
                modeloTabla.addRow(fila);
            }
        }
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);
                
    } 
    
    public static void cuotasActivas(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Clase");
        modeloTabla.addColumn("Cuota");
        modeloTabla.addColumn("Inicio");
        modeloTabla.addColumn("Vencimiento");
        Object[] fila = new Object[5];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (Cuota cuota : controlador.getListaCuotas()) {
            if (cuota.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = cuota;
                fila[1] = cuota.getClase().getTipoclase();
                fila[2] = cuota.getMonto();
                fila[3] = f.format(cuota.getAltacuota());
                fila[4] = f.format(cuota.getVencimiento());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }     
    
    public static void cuotasInactivas(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Clase");
        modeloTabla.addColumn("Cuota");
        modeloTabla.addColumn("Inicio");
        modeloTabla.addColumn("Vencimiento");
        Object[] fila = new Object[5];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (Cuota cuota : controlador.getListaCuotas()) {
            if (cuota.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = cuota;
                fila[1] = cuota.getClase().getTipoclase();
                fila[2] = cuota.getMonto();
                fila[3] = f.format(cuota.getAltacuota());
                fila[4] = f.format(cuota.getVencimiento());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }         
    
    public static void cobroCuotaActivas(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Cuota");
        modeloTabla.addColumn("Fecha de Pago");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (CobroCuota cobroCuota : controlador.getListaCobroCuota()) {
            if (cobroCuota.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = cobroCuota;
                fila[1] = cobroCuota.getMontocobro();
                fila[2] = f.format(cobroCuota.getFechapago());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }    
    
    public static void cobroCuotaInactivas(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Cuota");
        modeloTabla.addColumn("Fecha de Pago");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (CobroCuota cobroCuota : controlador.getListaCobroCuota()) {
            if (cobroCuota.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = cobroCuota;
                fila[1] = cobroCuota.getMontocobro();
                fila[2] = f.format(cobroCuota.getFechapago());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }    
    
    public static void saldoCuotasActivas(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Saldo");
        modeloTabla.addColumn("Cuota");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (SaldoCuota saldoCuota : controlador.getListaSaldoCuota()) {
            if (saldoCuota.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = saldoCuota;
                fila[1] = saldoCuota.getMontosaldo();
                fila[2] = f.format(saldoCuota.getCobroCuota().getCuota().getAltacuota());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }        
    
    public static void saldoCuotasInactivas(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Alumno");
        modeloTabla.addColumn("Saldo");
        modeloTabla.addColumn("Cuota");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (SaldoCuota saldoCuota : controlador.getListaSaldoCuota()) {
            if (saldoCuota.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = saldoCuota;
                fila[1] = saldoCuota.getMontosaldo();
                fila[2] = f.format(saldoCuota.getCobroCuota().getCuota().getAltacuota());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }    

 
    public static void pagoProfesorActivos(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Profesor");
        modeloTabla.addColumn("Monto Pagado");
        modeloTabla.addColumn("Fecha de Pago");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (PagoProfesor pagoProfesor : controlador.getListaPagoProfesores()) {
            if (pagoProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = pagoProfesor;
                fila[1] = pagoProfesor.getMontopago();
                fila[2] = f.format(pagoProfesor.getFechapago());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }       
    
    public static void pagoProfesorInactivos(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Profesor");
        modeloTabla.addColumn("Monto Pagado");
        modeloTabla.addColumn("Fecha de Pago");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (PagoProfesor pagoProfesor : controlador.getListaPagoProfesores()) {
            if (pagoProfesor.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = pagoProfesor;
                fila[1] = pagoProfesor.getMontopago();
                fila[2] = f.format(pagoProfesor.getFechapago());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }           
    
    public static void saldoPagoProfesorActivos(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Profesor");
        modeloTabla.addColumn("Monto Saldo");
        modeloTabla.addColumn("Cuota");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (Saldopagoprofesor saldoPagoProfesor : controlador.getListaSaldoPagoProfesores()) {
            if (saldoPagoProfesor.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = saldoPagoProfesor;
                fila[1] = saldoPagoProfesor.getMontosaldo();
                fila[2] = f.format(saldoPagoProfesor.getPagoProfesor().getFechapago());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }            
    
    public static void saldoPagoProfesorInactivos(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Profesor");
        modeloTabla.addColumn("Monto Saldo");
        modeloTabla.addColumn("Cuota");
        Object[] fila = new Object[3];
        String patron = "dd/MM/yyyy";
        SimpleDateFormat f  = new SimpleDateFormat(patron, new Locale("es", "AR"));
        for (Saldopagoprofesor saldoPagoProfesor : controlador.getListaSaldoPagoProfesores()) {
            if (saldoPagoProfesor.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = saldoPagoProfesor;
                fila[1] = saldoPagoProfesor.getMontosaldo();
                fila[2] = f.format(saldoPagoProfesor.getPagoProfesor().getFechapago());
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }  
    
    public static void modulosActivos(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Modulo");
        modeloTabla.addColumn("Sistema");
        Object[] fila = new Object[2];

        for (Modulo modulo : controlador.getListaModulos()) {
            if (modulo.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = modulo;
                fila[1] = modulo.getSistema().getPropietario();
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }      
    
    public static void modulosInactivos(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Modulo");
        modeloTabla.addColumn("Sistema");
        Object[] fila = new Object[2];

        for (Modulo modulo : controlador.getListaModulos()) {
            if (modulo.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = modulo;
                fila[1] = modulo.getSistema().getPropietario();
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }       
    
    public static void personalActivo(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Personal");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Obra Social");
        modeloTabla.addColumn("Usuario");
        Object[] fila = new Object[6];

        for (Personal personal : controlador.getListaPersonal()) {
            if (personal.getEstado().equalsIgnoreCase("ACTIVO")) {
                fila[0] = personal;
                fila[1] = personal.getContacto().getTelefono1();
                fila[2] = personal.getContacto().getEmail1();
                fila[3] = personal.getContacto().getDireccion();
                if(personal.getObrasocial()!=null){
                    fila[4] = personal.getObrasocial();
                }else{
                    fila[4] = "NO";
                }
                fila[5] = personal.getUsuario().getNombreusuario();
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }       
    
    public static void personalInactivo(JTable tabla, ControladorPrincipal controlador)throws Notificaciones{ 

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Personal");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Obra Social");
        modeloTabla.addColumn("Usuario");
        Object[] fila = new Object[6];

        for (Personal personal : controlador.getListaPersonal()) {
            if (personal.getEstado().equalsIgnoreCase("INACTIVO")) {
                fila[0] = personal;
                fila[1] = personal.getContacto().getTelefono1();
                fila[2] = personal.getContacto().getEmail1();
                fila[3] = personal.getContacto().getDireccion();
                if(personal.getObrasocial()!=null){
                    fila[4] = personal.getObrasocial();
                }else{
                    fila[4] = "NO";
                }
                fila[5] = personal.getUsuario().getNombreusuario();
                modeloTabla.addRow(fila);
            }
        }
        
        tabla.setModel(modeloTabla);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(rowSorter);                
    }    
    
    //CARGOPERSONAL, CLASEPROFESOR, APERTURACAJADIARIA
    //SECTORCLASE
    
}
