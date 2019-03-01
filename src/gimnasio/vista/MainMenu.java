/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.vista;

import gimnasio.controlador.ControladorHuella;
import gimnasio.controlador.ControladorPrincipal;
import gimnasio.controlador.ControladorRele;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Alumno;
import gimnasio.modelo.Cuota;
import gimnasio.modelo.Usuario;
import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyVetoException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

/**
 *
 * @author adrian
 */
public class MainMenu extends javax.swing.JFrame {

    public static Usuario usuarioLogueado;
    public static boolean cajaAbierta = false;
    public static final ControladorRele rele = new ControladorRele();
    
    public static ControladorPrincipal miControlador;
    public static volatile ControladorHuella lector;

    public static void cobrarCuota(Alumno alumnoSeleccionado) {
            jInternalCobroCuotas panelCuotas = new jInternalCobroCuotas(miControlador,alumnoSeleccionado);
            jDesktopPane1.add(panelCuotas);
            panelCuotas.setVisible(true);
            panelCuotas.toFront();        
    }

    boolean mostrar = false;
    public static Boolean detenerEscaner = true;


    /**
     * Creates new form MainMenu
     *
     * @param controlador
     */
    public MainMenu(ControladorPrincipal controlador) {
        this.miControlador = controlador;
        initComponents();     
        this.btnAsistencia.setEnabled(mostrar);
        this.btnCuotas.setEnabled(mostrar);
        this.btnUsuarios.setEnabled(mostrar);
        this.comprobarAperturaCaja();
        
        lector = new ControladorHuella(miControlador, controlador.getMiPersistencia(), this.txtHabilitado, this.lblFoto, this.tablaAsistencias);
        jScrollPane2.getVerticalScrollBar().setValue(jScrollPane2.getVerticalScrollBar().getMaximum());
        Thread thread_object = new Thread(lector);
        thread_object.setDaemon(true);
        thread_object.start();

        jInternalLogueo login = new jInternalLogueo(miControlador);
        this.jDesktopPane1.add(login);
        login.setVisible(true);
        login.grabFocus();

        this.allOptions(false);

        Dimension desktopSize = jDesktopPane1.getSize();

        opcionesDefault();
    }
    

    public static void iniciarEscaner() {
        synchronized(lector){
            lector.start();
        }
    }

    public static void loguearUsuario(Usuario unUsuario) {
        usuarioLogueado = unUsuario;
        jDesktopPane1.grabFocus();
    }

    public static void detenerLector() {
        synchronized (lector) {
            lector.stop();
        }
    }

    public static void nuevoMovimiento(Alumno elAlumno, Cuota cuota, Double abono, boolean esSaldo) {
        
        jInternalMovimiento movimiento = new jInternalMovimiento(miControlador, elAlumno, cuota, abono, esSaldo);
        jDesktopPane1.add(movimiento);
        movimiento.setVisible(true);
    }

    public static void nuevoUsuario() {
        try {
            jInternalUsuarios panelUsuarios = new jInternalUsuarios(true, miControlador);
            panelUsuarios.setVisible(true);
            jDesktopPane1.add(panelUsuarios);
            panelUsuarios.setSelected(true);
            panelUsuarios.toFront();
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public void abrirPromociones() {
        JInternalPromociones promo = new JInternalPromociones(miControlador);
        jDesktopPane1.add(promo);
        promo.setVisible(true);
    }

    public static Usuario getUsuario() {
        return usuarioLogueado;
    }

    public static boolean isUserAdmin() {
        boolean retorno = false;
        if (usuarioLogueado.getEstado().equalsIgnoreCase("ADMIN")) {
            retorno = true;
        }
        return retorno;
    }

    public static void abrirCobro(Alumno alumnoSeleccionado, Cuota cuota) {
        jInternalCobro cobro = new jInternalCobro(miControlador, alumnoSeleccionado, cuota);
        jDesktopPane1.add(cobro);
        cobro.setVisible(true);
    }

    public static void cajaAbierta(boolean b) {
        cajaAbierta = b;
    }


    public void cerrarSesion() {
        allOptions(false);
        usuarioLogueado = null;
        JInternalFrame[] frames = jDesktopPane1.getAllFrames();
        for(JInternalFrame fr:frames){
            fr.dispose();
        }
        jInternalLogueo login = new jInternalLogueo(miControlador);
        jDesktopPane1.add(login);
        login.setVisible(true);
        opcionesDefault();
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnUsuarios = new javax.swing.JButton();
        btnPromociones = new javax.swing.JButton();
        btnAsistencia = new javax.swing.JButton();
        btnAlumnos = new javax.swing.JButton();
        btnProfesores = new javax.swing.JButton();
        btnModalidad = new javax.swing.JButton();
        btnModalidadProfesor = new javax.swing.JButton();
        btnIngresos = new javax.swing.JButton();
        btnCuotas = new javax.swing.JButton();
        btnAlumnosCuotas = new javax.swing.JButton();
        btnClaseALumno = new javax.swing.JButton();
        btnClases = new javax.swing.JButton();
        btnClasesProfesor = new javax.swing.JButton();
        btnSinCLases = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAsistencias = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        txtHabilitado = new javax.swing.JTextField();
        btnAbrirPuerta = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMIUsuarios = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMIAlumnos = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMIProfesores = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMIClases = new javax.swing.JMenu();
        itemNuevaClase = new javax.swing.JMenuItem();
        itemListarClases = new javax.swing.JMenuItem();
        itemEliminarClase = new javax.swing.JMenuItem();
        jmiModalidades = new javax.swing.JMenuItem();
        jMIPersonal = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        JMIIniciarSesion = new javax.swing.JMenuItem();
        jMICerrarSesion = new javax.swing.JMenuItem();
        jMISalir = new javax.swing.JMenuItem();
        jMenuEdicion = new javax.swing.JMenu();
        jMIMovimientosCobro = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuCaja = new javax.swing.JMenu();
        jMIAperturaCaja = new javax.swing.JMenuItem();
        jMICierreCaja = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMIModificarCajas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE GESTION COUNTRYGYM");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setName("Sistema Country GYM"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        btnUsuarios.setBackground(new java.awt.Color(255, 153, 0));
        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        jPanel2.add(btnUsuarios);

        btnPromociones.setBackground(new java.awt.Color(255, 153, 0));
        btnPromociones.setText("Promociones");
        btnPromociones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromocionesActionPerformed(evt);
            }
        });
        jPanel2.add(btnPromociones);

        btnAsistencia.setBackground(new java.awt.Color(255, 153, 0));
        btnAsistencia.setText("Asistencia");
        btnAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciaActionPerformed(evt);
            }
        });
        jPanel2.add(btnAsistencia);

        btnAlumnos.setText("Alumnos");
        btnAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlumnosActionPerformed(evt);
            }
        });
        jPanel2.add(btnAlumnos);

        btnProfesores.setText("Profesores");
        btnProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfesoresActionPerformed(evt);
            }
        });
        jPanel2.add(btnProfesores);

        btnModalidad.setText("Modalidades");
        btnModalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModalidadActionPerformed(evt);
            }
        });
        jPanel2.add(btnModalidad);

        btnModalidadProfesor.setText("<HTML><center>Modalidades<br>por Profesor</center></HTML>");
        btnModalidadProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModalidadProfesorActionPerformed(evt);
            }
        });
        jPanel2.add(btnModalidadProfesor);

        btnIngresos.setBackground(new java.awt.Color(255, 153, 0));
        btnIngresos.setText("<html><center>Ingresos<br>por Puerta</center></html>");
        btnIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresosActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngresos);

        btnCuotas.setBackground(new java.awt.Color(255, 153, 0));
        btnCuotas.setText("<html><center>Cobro<br>de Cuotas</center></html>");
        btnCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuotasActionPerformed(evt);
            }
        });
        jPanel2.add(btnCuotas);

        btnAlumnosCuotas.setBackground(new java.awt.Color(255, 153, 0));
        btnAlumnosCuotas.setText("<HTML><CENTER>Clases y Cuotas<BR>de Alumnos</CENTER></HTML>");
        btnAlumnosCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlumnosCuotasActionPerformed(evt);
            }
        });
        jPanel2.add(btnAlumnosCuotas);

        btnClaseALumno.setText("Inscripciones");
        btnClaseALumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClaseALumnoActionPerformed(evt);
            }
        });
        jPanel2.add(btnClaseALumno);

        btnClases.setText("Clases");
        btnClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClasesActionPerformed(evt);
            }
        });
        jPanel2.add(btnClases);

        btnClasesProfesor.setText("<HTML><center>Clases por<br>Profesor</center></HTML>");
        btnClasesProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClasesProfesorActionPerformed(evt);
            }
        });
        jPanel2.add(btnClasesProfesor);

        btnSinCLases.setBackground(new java.awt.Color(0, 0, 0));
        btnSinCLases.setText("<html><center>Alumnos<br>Sin Clases</center></html>");
        btnSinCLases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSinCLasesActionPerformed(evt);
            }
        });
        jPanel2.add(btnSinCLases);

        jPanel1.add(jPanel2, java.awt.BorderLayout.EAST);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setMaximumSize(new java.awt.Dimension(500, 110));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setMaximumSize(new java.awt.Dimension(400, 110));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(400, 110));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(400, 110));

        tablaAsistencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaAsistencias);

        jPanel6.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        lblFoto.setText("FOTOGRAFIA CLIENTE");
        jPanel5.add(lblFoto, java.awt.BorderLayout.CENTER);

        txtHabilitado.setEditable(false);
        txtHabilitado.setBackground(new java.awt.Color(255, 204, 0));
        txtHabilitado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel5.add(txtHabilitado, java.awt.BorderLayout.SOUTH);

        btnAbrirPuerta.setBackground(new java.awt.Color(255, 153, 0));
        btnAbrirPuerta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gimnasio/Icons/payments.png"))); // NOI18N
        btnAbrirPuerta.setText("<HTML><CENTER>ABRIR<BR>PUERTA</CENTER></HTML>");
        btnAbrirPuerta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrirPuerta.setMaximumSize(new java.awt.Dimension(73, 20));
        btnAbrirPuerta.setMinimumSize(new java.awt.Dimension(73, 20));
        btnAbrirPuerta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrirPuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirPuertaActionPerformed(evt);
            }
        });
        jPanel5.add(btnAbrirPuerta, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel5, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jDesktopPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("ComboBox.selectionBackground"));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(500, 450));
        jDesktopPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDesktopPane1FocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jMenuArchivo.setText("Archivo");

        jMIUsuarios.setText("Usuarios");
        jMIUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMIUsuariosMouseClicked(evt);
            }
        });
        jMIUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIUsuariosActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Listar Usuarios");
        jMIUsuarios.add(jMenuItem3);

        jMenuItem1.setText("Nuevo Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMIUsuarios.add(jMenuItem1);

        jMenuItem2.setText("Eliminar Usuario");
        jMIUsuarios.add(jMenuItem2);

        jMenuArchivo.add(jMIUsuarios);

        jMIAlumnos.setText("Alumnos");

        jMenuItem4.setText("Listar Alumnos");
        jMIAlumnos.add(jMenuItem4);

        jMenuItem5.setText("Nuevo Alumno");
        jMIAlumnos.add(jMenuItem5);

        jMenuItem6.setText("Eliminar Alumno");
        jMIAlumnos.add(jMenuItem6);

        jMenuArchivo.add(jMIAlumnos);

        jMIProfesores.setText("Profesores");

        jMenuItem7.setText("Listar Profesores");
        jMIProfesores.add(jMenuItem7);

        jMenuItem8.setText("Nuevo Profesor");
        jMIProfesores.add(jMenuItem8);

        jMenuItem9.setText("Eliminar Profesor");
        jMIProfesores.add(jMenuItem9);

        jMenuArchivo.add(jMIProfesores);

        jMIClases.setText("Clases");

        itemNuevaClase.setText("Nueva Clase");
        itemNuevaClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevaClaseActionPerformed(evt);
            }
        });
        jMIClases.add(itemNuevaClase);

        itemListarClases.setText("Listar Clases");
        jMIClases.add(itemListarClases);

        itemEliminarClase.setText("Eliminar Clase");
        jMIClases.add(itemEliminarClase);

        jmiModalidades.setText("Modalidades de Clase");
        jmiModalidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiModalidadesActionPerformed(evt);
            }
        });
        jMIClases.add(jmiModalidades);

        jMenuArchivo.add(jMIClases);

        jMIPersonal.setText("Personal");

        jMenuItem16.setText("Listar Personal");
        jMIPersonal.add(jMenuItem16);

        jMenuItem17.setText("Nuevo Personal");
        jMIPersonal.add(jMenuItem17);

        jMenuItem18.setText("Eliminar Personal");
        jMIPersonal.add(jMenuItem18);

        jMenuArchivo.add(jMIPersonal);
        jMenuArchivo.add(jSeparator1);

        JMIIniciarSesion.setText("Iniciar Sesion");
        jMenuArchivo.add(JMIIniciarSesion);

        jMICerrarSesion.setText("Cerrar Sesion");
        jMICerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICerrarSesionActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMICerrarSesion);

        jMISalir.setText("Salir");
        jMISalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMISalir);

        jMenuBar1.add(jMenuArchivo);

        jMenuEdicion.setText("Movimientos");

        jMIMovimientosCobro.setText("Nuevo Movimiento");
        jMIMovimientosCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIMovimientosCobroActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMIMovimientosCobro);

        jMenuItem10.setText("Reportes Movimientos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMenuItem10);

        jMenuItem11.setText("Listado de Cuotas Cobradas");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenuEdicion.add(jMenuItem11);

        jMenuBar1.add(jMenuEdicion);

        jMenu5.setText("Asistencia");

        jMenuItem25.setText("Abrir Asistencias");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem25);

        jMenuItem12.setText("Listar Asistencias sin Egresos");
        jMenu5.add(jMenuItem12);
        jMenu5.add(jSeparator2);

        jMenuItem14.setText("Reporte de Ingresos por Puerta");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Horarios");

        jMenuItem28.setText("Planilla de Horarios");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem28);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Cuotas");

        jMenuItem29.setText("Listar Cuotas");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem29);

        jMenuItem13.setText("Ver Cuotas Pagadas");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuBar1.add(jMenu7);

        jMenu10.setText("Dipositivos");

        jMenuItem23.setText("Ver Estado de Lector");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem23);

        jMenuItem24.setText("Abrir Puerta");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem24);

        jMenuBar1.add(jMenu10);

        jMenuCaja.setText("Caja");

        jMIAperturaCaja.setText("Apertura de Caja");
        jMIAperturaCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAperturaCajaActionPerformed(evt);
            }
        });
        jMenuCaja.add(jMIAperturaCaja);

        jMICierreCaja.setText("Cierre de Caja");
        jMICierreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICierreCajaActionPerformed(evt);
            }
        });
        jMenuCaja.add(jMICierreCaja);
        jMenuCaja.add(jSeparator3);

        jMIModificarCajas.setText("Opciones de Supervisor");
        jMIModificarCajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIModificarCajasActionPerformed(evt);
            }
        });
        jMenuCaja.add(jMIModificarCajas);

        jMenuBar1.add(jMenuCaja);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMICerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMICerrarSesionActionPerformed
        this.cerrarSesion();
    }//GEN-LAST:event_jMICerrarSesionActionPerformed

    private void btnCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuotasActionPerformed
         if (!existeFrame(jInternalCobroCuotas.class)) {
            jInternalCobroCuotas panelCuotas = new jInternalCobroCuotas(this.miControlador);
            this.jDesktopPane1.add(panelCuotas);
            panelCuotas.setVisible(true);
            panelCuotas.toFront();
        }
    }//GEN-LAST:event_btnCuotasActionPerformed

    private void btnAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsistenciaActionPerformed
        if (!existeFrame(jInternalAsistencias.class)) {
            jInternalAsistencias asistencias = new jInternalAsistencias(this.miControlador);
            this.jDesktopPane1.add(asistencias);
            asistencias.setVisible(true);
            asistencias.toFront();
        }
    }//GEN-LAST:event_btnAsistenciaActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        if (!existeFrame(jInternalUsuarios.class)) {
            jInternalUsuarios panelUsuarios = new jInternalUsuarios(false, this.miControlador);
            panelUsuarios.setVisible(true);
            this.jDesktopPane1.add(panelUsuarios);
            panelUsuarios.toFront();
        }
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnAbrirPuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirPuertaActionPerformed
        synchronized(rele){
            try {
                rele.abrirPuerta();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnAbrirPuertaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        synchronized(rele){
            try {
                rele.abrirPuerta();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        synchronized(lector){
            this.txtHabilitado.setText(lector.getEstado());
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMIUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIUsuariosActionPerformed
        synchronized(lector){
            lector.stop();
        }
        this.detenerEscaner = false;
        if (!existeFrame(jInternalUsuarios.class)) {
            jInternalUsuarios panelUsuarios = new jInternalUsuarios(false,miControlador);

            this.jDesktopPane1.add(panelUsuarios);
            panelUsuarios.setVisible(true);
        }
    }//GEN-LAST:event_jMIUsuariosActionPerformed

    private void jMIUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMIUsuariosMouseClicked
        if (!existeFrame(jInternalUsuarios.class)) {
            jInternalUsuarios panelUsuarios = new jInternalUsuarios(false,miControlador);
            this.jDesktopPane1.add(panelUsuarios);
            panelUsuarios.setVisible(true);
        }
    }//GEN-LAST:event_jMIUsuariosMouseClicked

    private void btnAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlumnosActionPerformed
        if (!existeFrame(jInternalAlumno.class)) {
            jInternalAlumno panelAlumnos = new jInternalAlumno(miControlador);
            this.jDesktopPane1.add(panelAlumnos);
            panelAlumnos.setVisible(true);
            panelAlumnos.toFront();
        }
    }//GEN-LAST:event_btnAlumnosActionPerformed

    private void btnProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfesoresActionPerformed
        if (!existeFrame(jInternalProfesores.class)) {
            jInternalProfesores panelProfesores = new jInternalProfesores(miControlador);
            this.jDesktopPane1.add(panelProfesores);
            panelProfesores.setVisible(true);
            panelProfesores.toFront();
        }
    }//GEN-LAST:event_btnProfesoresActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void itemNuevaClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevaClaseActionPerformed
        if (!existeFrame(JInternalClasesProfesor.class)) {
            JInternalClasesProfesor panelClases = new JInternalClasesProfesor(this.miControlador);
            this.jDesktopPane1.add(panelClases);
            panelClases.setVisible(true);
        }
    }//GEN-LAST:event_itemNuevaClaseActionPerformed

    private void btnClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClasesActionPerformed
        if (!existeFrame(jInternalClases.class)) {
            jInternalClases panelClases = new jInternalClases(this.miControlador);
            this.jDesktopPane1.add(panelClases);
            panelClases.setVisible(true);
            panelClases.toFront();
        }
    }//GEN-LAST:event_btnClasesActionPerformed

    private void btnClasesProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClasesProfesorActionPerformed
        if (!existeFrame(JInternalClasesProfesor.class)) {
            JInternalClasesProfesor panelClaseProfesor = new JInternalClasesProfesor(this.miControlador);
            this.jDesktopPane1.add(panelClaseProfesor);
            panelClaseProfesor.setVisible(true);
            panelClaseProfesor.toFront();
        }
    }//GEN-LAST:event_btnClasesProfesorActionPerformed

    private void btnClaseALumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClaseALumnoActionPerformed
        if (!existeFrame(jInternalClasesAlumno.class)) {
            jInternalClasesAlumno ventanaClaseAlumn = new jInternalClasesAlumno(this.miControlador);
            this.jDesktopPane1.add(ventanaClaseAlumn);
            ventanaClaseAlumn.setVisible(true);
            ventanaClaseAlumn.toFront();
        }
    }//GEN-LAST:event_btnClaseALumnoActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
//        lector.start();
//        lector.Iniciar();
    }//GEN-LAST:event_formComponentShown

    private void jDesktopPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDesktopPane1FocusGained
        setAcceso();
    }//GEN-LAST:event_jDesktopPane1FocusGained

    private void jMISalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMISalirActionPerformed
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea Cerrar el Sistema?", "Confirmacion", 2);
        if (seleccion == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_jMISalirActionPerformed

    private void jMIAperturaCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIAperturaCajaActionPerformed
        try {
            if (!miControlador.hayCajaAbiertaHoy()) {
                JInternalAbrirCaja caja = new JInternalAbrirCaja(miControlador);
                jDesktopPane1.add(caja);
                caja.setVisible(true);
                cajaAbierta = true;
            } else {
                JOptionPane.showMessageDialog(null, "Actualmente existe una caja abierta.");
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jMIAperturaCajaActionPerformed

    private void jMICierreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMICierreCajaActionPerformed
        try {
            miControlador.cerrarCaja();
            cajaAbierta = false;
            JOptionPane.showMessageDialog(null, "Caja cerrada");
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jMICierreCajaActionPerformed

    private void jMIMovimientosCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIMovimientosCobroActionPerformed
        if (cajaAbierta == true) {
            jInternalMovimiento movimientos = new jInternalMovimiento(miControlador);
            jDesktopPane1.add(movimientos);
            movimientos.setVisible(true);
            movimientos.toFront();
        } else {
            JOptionPane.showMessageDialog(null, "La caja se encuentra cerrada.");
        }

    }//GEN-LAST:event_jMIMovimientosCobroActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (!existeFrame(jInternalReportes.class)) {
            jInternalReportes reportes = new jInternalReportes(miControlador);
            jDesktopPane1.add(reportes);
            reportes.setVisible(true);
            reportes.toFront();
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        if (!existeFrame(jInternalAsistencias.class)) {
            jInternalAsistencias asistencias = new jInternalAsistencias(this.miControlador);
            this.jDesktopPane1.add(asistencias);
            asistencias.setVisible(true);
            asistencias.toFront();
        }
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void btnPromocionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromocionesActionPerformed
        if (!existeFrame(JInternalPromociones.class)) {
            JInternalPromociones promos = new JInternalPromociones(miControlador);
            jDesktopPane1.add(promos);
            promos.setVisible(true);
        }
    }//GEN-LAST:event_btnPromocionesActionPerformed

    private void btnAlumnosCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlumnosCuotasActionPerformed
        if (!existeFrame(JInternalAlumnosCuotas.class)) {
            JInternalAlumnosCuotas alumnoCuota = new JInternalAlumnosCuotas(miControlador);
            jDesktopPane1.add(alumnoCuota);
            alumnoCuota.setVisible(true);
            alumnoCuota.toFront();
        }
    }//GEN-LAST:event_btnAlumnosCuotasActionPerformed

    private void btnModalidadProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModalidadProfesorActionPerformed
        if (!existeFrame(jInternalProfesorModalidad.class)) {
            jInternalProfesorModalidad panelProfeMod = new jInternalProfesorModalidad(this.miControlador);
            this.jDesktopPane1.add(panelProfeMod);
            panelProfeMod.setVisible(true);
            panelProfeMod.toFront();
        }
    }//GEN-LAST:event_btnModalidadProfesorActionPerformed

    private void btnModalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModalidadActionPerformed
        if (!existeFrame(jInternalModalidades.class)) {
            jInternalModalidades panelModalidad = new jInternalModalidades(this.miControlador);
            this.jDesktopPane1.add(panelModalidad);
            panelModalidad.setVisible(true);
        }
    }//GEN-LAST:event_btnModalidadActionPerformed

    private void jmiModalidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiModalidadesActionPerformed
        if (!existeFrame(jInternalModalidades.class)) {
            jInternalModalidades panelModalidad = new jInternalModalidades(this.miControlador);
            this.jDesktopPane1.add(panelModalidad);
            panelModalidad.setVisible(true);
            panelModalidad.toFront();
        }
    }//GEN-LAST:event_jmiModalidadesActionPerformed

    private void btnIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresosActionPerformed
        if (!existeFrame(JInternalPuerta.class)) {
            JInternalPuerta panelPuerta = new JInternalPuerta(this.miControlador);
            this.jDesktopPane1.add(panelPuerta);
            panelPuerta.setVisible(true);
            panelPuerta.toFront();
        }
    }//GEN-LAST:event_btnIngresosActionPerformed

    private void btnSinCLasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSinCLasesActionPerformed
        if (!existeFrame(jInternalClasesAlumnosOff.class)) {
            jInternalClasesAlumnosOff alumnos = new jInternalClasesAlumnosOff(this.miControlador);
            this.jDesktopPane1.add(alumnos);
            alumnos.setVisible(true);
            alumnos.toFront();
        }
    }//GEN-LAST:event_btnSinCLasesActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        if (!existeFrame(JInternalAlumnosCuotas.class)) {
            JInternalAlumnosCuotas alumnoCuota = new JInternalAlumnosCuotas(miControlador);
            jDesktopPane1.add(alumnoCuota);
            alumnoCuota.setVisible(true);
            alumnoCuota.toFront();
        }
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        if (!existeFrame(JInternalClasesProfesor.class)) {
            JInternalClasesProfesor panelClaseProfesor = new JInternalClasesProfesor(this.miControlador);
            this.jDesktopPane1.add(panelClaseProfesor);
            panelClaseProfesor.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        if (!existeFrame(JInternalCuotasCobradas.class)) {
            JInternalCuotasCobradas panelCuotas = new JInternalCuotasCobradas(this.miControlador);
            this.jDesktopPane1.add(panelCuotas);
            panelCuotas.setVisible(true);
            panelCuotas.toFront();
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        if (!existeFrame(JInternalCuotasCobradas.class)) {
            JInternalCuotasCobradas panelCuotas = new JInternalCuotasCobradas(this.miControlador);
            this.jDesktopPane1.add(panelCuotas);
            panelCuotas.setVisible(true);
            panelCuotas.toFront();
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        if (!existeFrame(JInternalCuotasCobradas.class)) {
            JInternalReporteIngresos panelIngresos = new JInternalReporteIngresos(this.miControlador);
            this.jDesktopPane1.add(panelIngresos);
            panelIngresos.setVisible(true);
            panelIngresos.toFront();
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMIModificarCajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIModificarCajasActionPerformed
        if (!usuarioLogueado.getEstado().equalsIgnoreCase("SUPER")) {
            JOptionPane.showMessageDialog(null, "Este usuario no posee Superpoderes");
        } else {
            if (!existeFrame(JInternalCuotasCobradas.class)) {
                JInternalSuperAdmin admin = new JInternalSuperAdmin(this.miControlador);
                this.jDesktopPane1.add(admin);
                admin.setVisible(true);
                admin.toFront();
            }
        }
    }//GEN-LAST:event_jMIModificarCajasActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JMIIniciarSesion;
    private javax.swing.JButton btnAbrirPuerta;
    private javax.swing.JButton btnAlumnos;
    private javax.swing.JButton btnAlumnosCuotas;
    private javax.swing.JButton btnAsistencia;
    private javax.swing.JButton btnClaseALumno;
    private javax.swing.JButton btnClases;
    private javax.swing.JButton btnClasesProfesor;
    private javax.swing.JButton btnCuotas;
    private javax.swing.JButton btnIngresos;
    private javax.swing.JButton btnModalidad;
    private javax.swing.JButton btnModalidadProfesor;
    private javax.swing.JButton btnProfesores;
    private javax.swing.JButton btnPromociones;
    private javax.swing.JButton btnSinCLases;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JMenuItem itemEliminarClase;
    private javax.swing.JMenuItem itemListarClases;
    private javax.swing.JMenuItem itemNuevaClase;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMIAlumnos;
    private javax.swing.JMenuItem jMIAperturaCaja;
    private javax.swing.JMenuItem jMICerrarSesion;
    private javax.swing.JMenuItem jMICierreCaja;
    private javax.swing.JMenu jMIClases;
    private javax.swing.JMenuItem jMIModificarCajas;
    private javax.swing.JMenuItem jMIMovimientosCobro;
    private javax.swing.JMenu jMIPersonal;
    private javax.swing.JMenu jMIProfesores;
    private javax.swing.JMenuItem jMISalir;
    private javax.swing.JMenu jMIUsuarios;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCaja;
    private javax.swing.JMenu jMenuEdicion;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem jmiModalidades;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTable tablaAsistencias;
    private javax.swing.JTextField txtHabilitado;
    // End of variables declaration//GEN-END:variables

    public void setAcceso() {
        LocalDate caducacion = LocalDate.of(2019, 03, 18);
        if (LocalDate.now().isBefore(caducacion)) {
            if (usuarioLogueado != null) {
                if (usuarioLogueado.getEstado().equalsIgnoreCase("SUPER")) {
                    allOptions(true);
                    this.jMIModificarCajas.setEnabled(true);
                }

                if (usuarioLogueado.getEstado().equalsIgnoreCase("ADMIN")) {
                    allOptions(true);
                }
                if (usuarioLogueado.getEstado().equalsIgnoreCase("OPERADOR")) {
                    operadorOptions(true);
                }
                if (usuarioLogueado.getEstado().equalsIgnoreCase("ACTIVO")) {
                    allOptions(false);
                    JOptionPane.showMessageDialog(null, "Usuario sin permisos operativos");
                    jInternalLogueo login = new jInternalLogueo(miControlador);
                    this.jDesktopPane1.add(login);
                    login.setVisible(true);
                }
            } else {
                opcionesDefault();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se agoto el modo de prueba");
        }
    }

    public void opcionesDefault() {
        this.jMenuArchivo.setEnabled(true);
        this.jMIUsuarios.setEnabled(false);
        this.jMIAlumnos.setEnabled(false);
        this.jMIProfesores.setEnabled(false);
        this.jMIClases.setEnabled(false);
        this.jMIPersonal.setEnabled(false);
        this.JMIIniciarSesion.setEnabled(true);
        this.jMICerrarSesion.setEnabled(false);
        this.jMISalir.setEnabled(true);
    }

    public void operadorOptions(boolean estado) {
        allOptions(estado);
        this.jMenuCaja.setEnabled(false);
    }

    public void allOptions(boolean estado) {
        this.jMIModificarCajas.setEnabled(false);
        this.JMIIniciarSesion.setEnabled(!estado);
        this.jMIClases.setEnabled(estado);
        this.jMIUsuarios.setEnabled(estado);
        this.btnSinCLases.setEnabled(estado);
        this.btnAbrirPuerta.setEnabled(estado);
        this.btnAlumnos.setEnabled(estado);
        this.btnAsistencia.setEnabled(estado);
        this.btnClaseALumno.setEnabled(estado);
        this.btnClases.setEnabled(estado);
        this.btnClasesProfesor.setEnabled(estado);
        this.btnCuotas.setEnabled(estado);
        this.btnModalidad.setEnabled(estado);
        this.btnModalidadProfesor.setEnabled(estado);
        this.btnProfesores.setEnabled(estado);
        this.btnUsuarios.setEnabled(estado);
        this.btnAlumnosCuotas.setEnabled(estado);
        this.btnPromociones.setEnabled(estado);
        this.btnIngresos.setEnabled(estado);
        this.jMICerrarSesion.setEnabled(estado);
        this.jMIAlumnos.setEnabled(estado);
        this.jMIProfesores.setEnabled(estado);
        this.jMenuCaja.setEnabled(estado);
        this.jMenu5.setEnabled(estado);
        this.jMenu6.setEnabled(estado);
        this.jMenu7.setEnabled(estado);
        this.jMenuCaja.setEnabled(estado);
        this.jMenu10.setEnabled(estado);
        this.jMIPersonal.setEnabled(estado);
        this.jMenuArchivo.setEnabled(estado);
        this.jMenuBar1.setEnabled(estado);
        this.jMenuEdicion.setEnabled(estado);
        this.jMenuItem1.setEnabled(estado);
        this.jMenuItem2.setEnabled(estado);
        this.jMenuItem3.setEnabled(estado);
        this.jMenuItem4.setEnabled(estado);
        this.jMenuItem5.setEnabled(estado);
        this.jMenuItem6.setEnabled(estado);
        this.jMenuItem7.setEnabled(estado);
        this.jMenuItem8.setEnabled(estado);
        this.jMenuItem9.setEnabled(estado);
        this.jMIAperturaCaja.setEnabled(estado);
        this.jMICierreCaja.setEnabled(estado);
        this.jMenuItem16.setEnabled(estado);
        this.jMenuItem17.setEnabled(estado);
        this.jMenuItem18.setEnabled(estado);
        this.jMIMovimientosCobro.setEnabled(estado);
        this.jMIAperturaCaja.setEnabled(estado);
        this.jMICierreCaja.setEnabled(estado);
        this.jMenuItem23.setEnabled(estado);
        this.jMenuItem24.setEnabled(estado);
        this.jMenuItem25.setEnabled(estado);
        this.jMenuItem28.setEnabled(estado);
        this.jMenuItem29.setEnabled(estado);
    }

    private void comprobarAperturaCaja() {
        try {
            if (miControlador.hayCajaAbiertaHoy()) {
                cajaAbierta = true;
            }
        } catch (Notificaciones ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public boolean existeFrame(Class c){
        Object[] frames = this.jDesktopPane1.getAllFrames();
        boolean yaExiste = false;
        for (Object frame : frames) {
            if (c.isInstance(frame)) {
                yaExiste = true;

                JInternalFrame internal = (JInternalFrame) frame;
                internal.toFront();
                internal.grabFocus();
                try {
                    internal.setSelected(true);
                } catch (PropertyVetoException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
        return yaExiste;
    }

}
