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
import org.hibernate.Hibernate;
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

    private ServiceRegistry serviceRegistry;
    private Configuration configuration;
    private SessionFactory sessionFactory;

    public ControladorPersistencia() {
        try {
            configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public boolean persistirInstancia(Object instancia) throws Notificaciones {
        boolean resultado = false;

        /*
		 * Se sincroniza la sesión para asegurar que otro thread no la use.
         */
 /*
			 * Se comprueba la conexión.
         */
        Session sesion = sessionFactory.openSession();

        Transaction t = sesion.getTransaction();

        try {
            t.begin();
            sesion.saveOrUpdate(instancia);
            sesion.flush();
            t.commit();

        } catch (Exception e) {
            t.rollback();
            throw new Notificaciones(e.getLocalizedMessage());
        } finally {
            sesion.close();
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

        Session sesion = sessionFactory.openSession();

        Transaction t = sesion.getTransaction();

        try {
            t.begin();
            sesion.delete(instancia);
            t.commit();

            resultado = true;

        } catch (Exception e) {
            t.rollback();
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return resultado;
    }

    public void refrescar(Object o) throws Notificaciones {

        Session sesion = sessionFactory.openSession();

        Transaction t = sesion.getTransaction();

        try {
            t.begin();
            sesion.refresh(o);
            sesion.flush();
            t.commit();

        } catch (Exception e) {
            t.rollback();
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
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
        Session sesion = sessionFactory.openSession();

        Transaction t = sesion.getTransaction();

        try {
            t.begin();
            sesion.flush();
            t.commit();

            resultado = true;
        } catch (Exception e) {
            t.rollback();
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return resultado;
    }

    public List<Alumno> getAlumnos() throws Notificaciones {

        String textoConsulta = "FROM Alumno";
        List<Alumno> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();
            for (Alumno a : lista) {
                Hibernate.initialize(a.getClaseAlumnos());
                Hibernate.initialize(a.getCuotas());
            }
        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<AsistenciaAlumno> getAsistenciaAlumno() throws Notificaciones {

        String textoConsulta = "FROM AsistenciaAlumno";
        List<AsistenciaAlumno> lista = null;

        Session sesion = sessionFactory.openSession();

        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<AsistenciaProfesor> getAsistenciaProfesor() throws Notificaciones {

        String textoConsulta = "FROM AsistenciaProfesor";
        List<AsistenciaProfesor> lista = null;

        Session sesion = sessionFactory.openSession();

        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();
        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Cargo> getCargos() throws Notificaciones {
        String textoConsulta = "FROM Cargo";
        List<Cargo> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();
        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Clase> getClases() throws Notificaciones {

        String textoConsulta = "FROM Clase";
        List<Clase> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();
        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<Cuota> getCuotas() throws Notificaciones {
        String textoConsulta = "FROM Cuota";
        List<Cuota> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();
        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<CobroCuota> getCobroCuota() throws Notificaciones {

        String textoConsulta = "FROM CobroCuota";
        List<CobroCuota> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Modalidad> getModalidades() throws Notificaciones {

        String textoConsulta = "FROM Modalidad";
        List<Modalidad> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Modulo> getModulos() throws Notificaciones {

        String textoConsulta = "FROM Modulo";
        List<Modulo> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<PagoProfesor> getPagoProfesores() throws Notificaciones {

        String textoConsulta = "FROM PagoProfesor";
        List<PagoProfesor> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Profesor> getProfesores() throws Notificaciones {

        String textoConsulta = "FROM Profesor";
        List<Profesor> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Profesormodalidad> getProfesorModalidad() throws Notificaciones {

        String textoConsulta = "FROM Profesormodalidad";
        List<Profesormodalidad> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<SaldoCuota> getSaldoCuota() throws Notificaciones {

        String textoConsulta = "FROM SaldoCuota";
        List<SaldoCuota> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Saldopagoprofesor> getSaldoPagoProfesores() throws Notificaciones {

        String textoConsulta = "FROM Saldopagoprofesor";
        List<Saldopagoprofesor> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<Sector> getSectores() throws Notificaciones {

        String textoConsulta = "FROM Sector";
        List<Sector> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Usuario> getUsuarios() throws Notificaciones {

        String textoConsulta = "FROM Usuario";
        List<Usuario> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Obrasocial> getObraSociales() throws Notificaciones {

        String textoConsulta = "FROM Obrasocial";
        List<Obrasocial> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<ClaseProfesor> getClasesProfesores() throws Notificaciones {

        String textoConsulta = "FROM ClaseProfesor";
        List<ClaseProfesor> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<ClaseAlumno> getClasesAlumno() throws Notificaciones {

        String textoConsulta = "FROM ClaseAlumno";
        List<ClaseAlumno> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<HorarioProfesor> getHorariosProfesor() throws Notificaciones {
        String textoConsulta = "FROM HorarioProfesor WHERE estado like 'ACTIVO'";
        List<HorarioProfesor> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<HorarioAlumno> getHorariosAlumnos() throws Notificaciones {
        String textoConsulta = "FROM HorarioAlumno";
        List<HorarioAlumno> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Personal> getPersonales() throws Notificaciones {
        String textoConsulta = "FROM Personal";
        List<Personal> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Cajadiaria> getCajaDiarias() throws Notificaciones {
        String textoConsulta = "FROM Cajadiaria";
        List<Cajadiaria> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

    public List<Movimiento> getMovimientos() throws Notificaciones {
        String textoConsulta = "FROM Movimiento";
        List<Movimiento> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

        return lista;
    }

    public List<IngresosPuerta> getIngresosPuerta() throws Notificaciones {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -365);
        Date d = c.getTime();

        List<IngresosPuerta> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery("SELECT a FROM IngresosPuerta a WHERE a.horaIngreso > :param")
                    .setParameter("param", d);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
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
        Session sesion = sessionFactory.openSession();

        Transaction t = sesion.getTransaction();

        try {
            t.begin();
            sesion.evict(o);
            sesion.update(o);
            sesion.flush();
            t.commit();

            resultado = true;

        } catch (Exception e) {
            t.rollback();
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }

    }

    public void actualizarInstancia(Object objeto) throws Notificaciones {
        Session sesion = sessionFactory.openSession();

        Transaction t = sesion.getTransaction();

        try {
            t.begin();

            sesion.merge(objeto);
            t.commit();

        } catch (Exception e) {
            t.rollback();
            throw new Notificaciones(e.getLocalizedMessage());
        } finally {
            sesion.close();
        }
    }

    public List<HorarioAlumno> getHorarioClaseAlumno(ClaseAlumno clase) throws Notificaciones {
        int id = clase.getIdclasealumno();
        System.out.println(id);
        String textoConsulta = "FROM HorarioAlumno WHERE idClaseAlumno = :idClaseAlumno";
        List<HorarioAlumno> lista = null;

        Session sesion = sessionFactory.openSession();
        try {
            Query consulta = sesion.createQuery(textoConsulta).setParameter("idClaseAlumno", id);
            lista = consulta.list();

        } catch (Exception e) {
            throw new Notificaciones(e.getMessage());
        } finally {
            sesion.close();
        }
        return lista;
    }

}
