/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.Alumno;
import gimnasio.modelo.AsistenciaAlumno;
import gimnasio.modelo.AsistenciaProfesor;
import gimnasio.modelo.Cargo;
import gimnasio.modelo.Clase;
import gimnasio.modelo.CobroCuota;
import gimnasio.modelo.Cuota;
import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Modulo;
import gimnasio.modelo.PagoProfesor;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.Profesormodalidad;
import gimnasio.modelo.SaldoCuota;
import gimnasio.modelo.SaldoPagoProfesor;
import gimnasio.modelo.Sector;
import gimnasio.modelo.Usuario;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author wolix
 */
public class ControladorPersistencia {

    private static final SessionFactory sessionFactory;
    private static final Session sesion;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public boolean persistirInstancia(Object instancia) throws Notificaciones {
        boolean resultado = false;

        /*
		 * Se sincroniza la sesión para asegurar que otro thread no la use.
         */
        synchronized (this.sesion) {
            /*
			 * Se comprueba la conexión.
             */
            this.comprobarConexion();

            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.saveOrUpdate(instancia);
                t.commit();

                /*
				 * Se comprueba que se haya persistido la instancia.
                 */
                resultado = this.sesion.contains(instancia);

            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getMessage());
            }
        }

        return resultado;
    }

    /**
     * Permite eliminar una instancia de la base de datos.
     *
     * @param instancia
     * @return
     * @throws Notificaciones
     */
    public boolean eliminarInstancia(Object instancia) throws Notificaciones {
        boolean resultado = false;
        /*
		 * Se sincroniza la sesión para que otro thread no la utilice.
         */
        synchronized (this.sesion) {
            /*
			 * Se comprueba la conexión.
             */
            this.comprobarConexion();

            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.delete(instancia);
                t.commit();

                resultado = true;

            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getMessage());
            }
        }

        return resultado;
    }

    /**
     * Permite guardar las modificaciones realizadas a cualquier instancia que
     * se haya cargado desde la base de datos.
     *
     * @return
     * @throws Notificaciones
     */
    public boolean actualizarInstancias() throws Notificaciones {
        boolean resultado = false;

        /*
		 * Se sincroniza la sesión.
         */
        synchronized (this.sesion) {
            /*
			 * Se comprueba la conexión.
             */
            this.comprobarConexion();

            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.flush();
                t.commit();

                resultado = true;

            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getMessage());
            }
        }

        return resultado;
    }

    /**
     * Permite cerrar la sesión de la base de datos.
     */
    public void cerrarSesion() {

        /*
		 * Se sincroniza la sesión.
         */
        synchronized (this.sesion) {

            if (this.sesion != null && this.sesion.isConnected()) {
                this.sesion.close();
            }
            if (this.sesion != null && this.sesion.isOpen()) {
                this.sesion.close();
            }
        }
    }

    /**
     * Permite comprobar si la conexión a la base de datos se mantiene activa.
     *
     * @throws Notificaciones
     */
    private void comprobarConexion() throws Notificaciones {
        String mensaje = null;

        if (this.sesion == null || !this.sesion.isConnected()) {
            mensaje = "Se ha interrumpido la conexión con la base de datos.\n"
                    + "Reinicie la aplicación.";
            throw new Notificaciones(mensaje);
        }

    }

    public Set<Alumno> getAlumnos() throws Notificaciones {
        Set<Alumno> alumnos = new HashSet<>();

        String textoConsulta = "FROM alumno";
        List<Alumno> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Alumno unAlumno : lista) {
                alumnos.add(unAlumno);
            }
        }
        return alumnos;
    }

    public Set<AsistenciaAlumno> getAsistenciaAlumno() throws Notificaciones {
        Set<AsistenciaAlumno> asistAlumnos = new HashSet<>();

        String textoConsulta = "FROM asistencia_alumno";
        List<AsistenciaAlumno> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (AsistenciaAlumno unAsistAlumno : lista) {
                asistAlumnos.add(unAsistAlumno);
            }
        }
        return asistAlumnos;
    }
    
    public Set<AsistenciaProfesor> getAsistenciaProfesor() throws Notificaciones {
        Set<AsistenciaProfesor> asistProfe = new HashSet<>();

        String textoConsulta = "FROM asistencia_profesor";
        List<AsistenciaProfesor> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (AsistenciaProfesor unAsistProfe : lista) {
                asistProfe.add(unAsistProfe);
            }
        }
        return asistProfe;
    }
    
        public Set<Cargo> getCargos() throws Notificaciones {
        Set<Cargo> cargos = new HashSet<>();

        String textoConsulta = "FROM cargo";
        List<Cargo> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Cargo unCargo : lista) {
                cargos.add(unCargo);
            }
        }
        return cargos;
    }
        
        public Set<Clase> getClases() throws Notificaciones {
        Set<Clase> clases = new HashSet<>();

        String textoConsulta = "FROM clase";
        List<Clase> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Clase unaClase : lista) {
                clases.add(unaClase);
            }
        }
        return clases;
    }
        
    public Set<Cuota> getCuotas() throws Notificaciones {
        Set<Cuota> cuotas = new HashSet<>();

        String textoConsulta = "FROM cuota";
        List<Cuota> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Cuota unaCuota : lista) {
                cuotas.add(unaCuota);
            }
        }
        return cuotas;
    }
    
        public Set<CobroCuota> getCobroCuota() throws Notificaciones {
        Set<CobroCuota> cobroCuotas = new HashSet<>();

        String textoConsulta = "FROM cobro_cuota";
        List<CobroCuota> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (CobroCuota unCobroCuota : lista) {
                cobroCuotas.add(unCobroCuota);
            }
        }
        return cobroCuotas;
    }

        public Set<Modalidad> getModalidades() throws Notificaciones {
        Set<Modalidad> modalidades = new HashSet<>();

        String textoConsulta = "FROM modalidad";
        List<Modalidad> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Modalidad unaModalidad : lista) {
                modalidades.add(unaModalidad);
            }
        }
        return modalidades;
    }
    
    public Set<Modulo> getModulos() throws Notificaciones {
        Set<Modulo> modulos = new HashSet<>();

        String textoConsulta = "FROM modulo";
        List<Modulo> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Modulo unModulo : lista) {
                modulos.add(unModulo);
            }
        }
        return modulos;
    }
    
        public Set<PagoProfesor> getPagoProfesores() throws Notificaciones {
        Set<PagoProfesor> pagoProfesores = new HashSet<>();

        String textoConsulta = "FROM pago_profesor";
        List<PagoProfesor> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (PagoProfesor unPagoProfesor : lista) {
                pagoProfesores.add(unPagoProfesor);
            }
        }
        return pagoProfesores;
    }
        
    public Set<Profesor> getProfesores() throws Notificaciones {
        Set<Profesor> profesores = new HashSet<>();

        String textoConsulta = "FROM profesor";
        List<Profesor> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Profesor unProfesor : lista) {
                profesores.add(unProfesor);
            }
        }
        return profesores;
    }
    
        public Set<Profesormodalidad> getProfesorModalidad() throws Notificaciones {
        Set<Profesormodalidad> profesorModalidades = new HashSet<>();

        String textoConsulta = "FROM profesormodalidad";
        List<Profesormodalidad> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Profesormodalidad unProfesorModalidad : lista) {
                profesorModalidades.add(unProfesorModalidad);
            }
        }
        return profesorModalidades;
    }
        
        
    public Set<SaldoCuota> getSaldoCuota() throws Notificaciones {
        Set<SaldoCuota> saldoCuotas = new HashSet<>();

        String textoConsulta = "FROM saldo_cuota";
        List<SaldoCuota> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (SaldoCuota unSaldoCuota : lista) {
                saldoCuotas.add(unSaldoCuota);
            }
        }
        return saldoCuotas;
    }
    
    public Set<SaldoPagoProfesor> getSaldoPagoProfesores() throws Notificaciones {
        Set<SaldoPagoProfesor> saldoPagoProfesores = new HashSet<>();

        String textoConsulta = "FROM saldo_pago_profesor";
        List<SaldoPagoProfesor> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (SaldoPagoProfesor unSaldoPagoP : lista) {
                saldoPagoProfesores.add(unSaldoPagoP);
            }
        }
        return saldoPagoProfesores;
    }
    
        public Set<Sector> getSectores() throws Notificaciones {
        Set<Sector> sectores = new HashSet<>();

        String textoConsulta = "FROM sector";
        List<Sector> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Sector unSector : lista) {
                sectores.add(unSector);
            }
        }
        return sectores;
    }
        
     public Set<Usuario> getUsuarios() throws Notificaciones {
        Set<Usuario> usuarios = new HashSet<>();

        String textoConsulta = "FROM usuario";
        List<Usuario> lista = null;

        synchronized (this.sesion) {
            this.comprobarConexion();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        }
        if (lista != null) {
            for (Usuario unUsuario : lista) {
                usuarios.add(unUsuario);
            }
        }
        return usuarios;
    }
}
