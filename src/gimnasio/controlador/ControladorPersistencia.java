/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.Alumno;
import herramientas.excepciones.Notificaciones;
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

        String textoConsulta = "FROM Alumno";
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

}
