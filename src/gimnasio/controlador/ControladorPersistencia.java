/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.*;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.util.Calendar;
import java.util.Date;

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

    private SessionFactory sessionFactory;
    private Session sesion;
    private ServiceRegistry serviceRegistry;
    private Configuration configuration;
    
    public ControladorPersistencia(){
        try {
            configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

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
            /*
			 * Se comprueba la conexión.
             */

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            
            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.saveOrUpdate(instancia);
                this.sesion.flush();
                t.commit();

                /*
				 * Se comprueba que se haya persistido la instancia.
                 */
                resultado = this.sesion.contains(instancia);
                sesion.close();

            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getLocalizedMessage());
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
            /*
			 * Se comprueba la conexión.
             */
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.delete(instancia);
                t.commit();

                resultado = true;
                sesion.close();
            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getMessage());
            }

        return resultado;
    }

    
    public void refrescar(Object o) throws Notificaciones{
 
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.refresh(o);
                sesion.flush();
                t.commit();

            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getMessage());
            }
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
            /*
			 * Se comprueba la conexión.
             */
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();

            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.flush();
                t.commit();

                resultado = true;
                sesion.close();

            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getMessage());
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

            if (this.sesion != null && this.sesion.isConnected()) {
                this.sesion.close();
            }
            if (this.sesion != null && this.sesion.isOpen()) {
                this.sesion.close();
            }
    }

    public List<Alumno> getAlumnos() throws Notificaciones {

        String textoConsulta = "FROM Alumno";
        List<Alumno> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<AsistenciaAlumno> getAsistenciaAlumno() throws Notificaciones {
        
        String textoConsulta = "FROM AsistenciaAlumno";
        List<AsistenciaAlumno> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        
        return lista;
    }

    public List<AsistenciaProfesor> getAsistenciaProfesor() throws Notificaciones {
        
        String textoConsulta = "FROM AsistenciaProfesor";
        List<AsistenciaProfesor> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Cargo> getCargos() throws Notificaciones {
        String textoConsulta = "FROM Cargo";
        List<Cargo> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Clase> getClases() throws Notificaciones {
        
        String textoConsulta = "FROM Clase";
        List<Clase> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        
        return lista;
    }

    public List<Cuota> getCuotas() throws Notificaciones {
        String textoConsulta = "FROM Cuota";
        List<Cuota> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<CobroCuota> getCobroCuota() throws Notificaciones {
        
        String textoConsulta = "FROM CobroCuota";
        List<CobroCuota> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Modalidad> getModalidades() throws Notificaciones {

        String textoConsulta = "FROM Modalidad";
        List<Modalidad> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Modulo> getModulos() throws Notificaciones {
        
        String textoConsulta = "FROM Modulo";
        List<Modulo> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        
        return lista;
    }

    public List<PagoProfesor> getPagoProfesores() throws Notificaciones {

        String textoConsulta = "FROM PagoProfesor";
        List<PagoProfesor> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Profesor> getProfesores() throws Notificaciones {

        String textoConsulta = "FROM Profesor";
        List<Profesor> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Profesormodalidad> getProfesorModalidad() throws Notificaciones {

        String textoConsulta = "FROM Profesormodalidad";
        List<Profesormodalidad> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<SaldoCuota> getSaldoCuota() throws Notificaciones {
        
        String textoConsulta = "FROM SaldoCuota";
        List<SaldoCuota> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Saldopagoprofesor> getSaldoPagoProfesores() throws Notificaciones {
        
        String textoConsulta = "FROM Saldopagoprofesor";
        List<Saldopagoprofesor> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        
        return lista;
    }

    public List<Sector> getSectores() throws Notificaciones {

        String textoConsulta = "FROM Sector";
        List<Sector> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Usuario> getUsuarios() throws Notificaciones {
        
        String textoConsulta = "FROM Usuario";
        List<Usuario> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    
    
    public List<Obrasocial> getObraSociales() throws Notificaciones{
        
        String textoConsulta = "FROM Obrasocial";
        List<Obrasocial> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        
        return lista;
    }

    public List<ClaseProfesor> getClasesProfesores() throws Notificaciones {
                
        String textoConsulta = "FROM ClaseProfesor";
        List<ClaseProfesor> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        
        return lista;
    }

    public List<ClaseAlumno> getClasesAlumno() throws Notificaciones {
                        
        String textoConsulta = "FROM ClaseAlumno";
        List<ClaseAlumno> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
       
        return lista;
    }

    public List<HorarioProfesor> getHorariosProfesor() throws Notificaciones {
        String textoConsulta = "FROM HorarioProfesor WHERE estado like 'ACTIVO'";
        List<HorarioProfesor> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<HorarioAlumno> getHorariosAlumnos() throws Notificaciones {
        String textoConsulta = "FROM HorarioAlumno";
        List<HorarioAlumno> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Personal> getPersonales() throws Notificaciones{
        String textoConsulta = "FROM Personal";
        List<Personal> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Cajadiaria> getCajaDiarias() throws Notificaciones {
        String textoConsulta = "FROM Cajadiaria";
        List<Cajadiaria> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public List<Movimiento> getMovimientos() throws Notificaciones {
        String textoConsulta = "FROM Movimiento";
        List<Movimiento> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery(textoConsulta);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }

        return lista;
    }

    public List<IngresosPuerta> getIngresosPuerta() throws Notificaciones {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -365);
        Date d = c.getTime();
        
        List<IngresosPuerta> lista = null;

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();
            try {
                Query consulta = this.sesion.createQuery("SELECT a FROM IngresosPuerta a WHERE a.horaIngreso > :param")
                        .setParameter("param", d);
                lista = consulta.list();
                sesion.close();
            } catch (Exception e) {
                throw new Notificaciones(e.getMessage());
            }
        return lista;
    }

    public void actualizarInstancias(Object o) throws Notificaciones {
      boolean resultado = false;

        /*
		 * Se sincroniza la sesión.
         */
            /*
			 * Se comprueba la conexión.
             */
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sesion = sessionFactory.openSession();

            Transaction t = this.sesion.getTransaction();

            try {
                t.begin();
                this.sesion.evict(o);
                this.sesion.update(o);
                this.sesion.flush();
                t.commit();

                resultado = true;
                sesion.close();

            } catch (Exception e) {
                t.rollback();
                throw new Notificaciones(e.getMessage());
            }

    }
}
