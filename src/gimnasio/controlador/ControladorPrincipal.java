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
import gimnasio.modelo.Modalidad;
import gimnasio.modelo.Modulo;
import gimnasio.modelo.PagoProfesor;
import gimnasio.modelo.Profesor;
import gimnasio.modelo.SaldoCuota;
import gimnasio.modelo.SaldoPagoProfesor;
import gimnasio.modelo.Sector;
import gimnasio.modelo.Usuario;
import herramientas.excepciones.Notificaciones;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Family
 */
public class ControladorPrincipal {
    private Set<Alumno> listaAlumnos = new HashSet<>();
    private Set<Profesor> listaProfesores = new HashSet<>();
    private Set<Modalidad> listaModalidades = new HashSet<>();
    private Set<Sector> listaSectores = new HashSet<>();
    private Set<Clase> listaClases = new HashSet<>();
    private Set<AsistenciaAlumno> listaAsistenciaAlumno = new HashSet<>();
    private Set<AsistenciaProfesor> listaAsistenciaProfesor = new HashSet<>();
    private Set<Cuota> listaCuotas = new HashSet<>();
    private Set<CobroCuota> listaCobroCuota = new HashSet<>();
    private Set<SaldoCuota> listaSaldoCuota = new HashSet<>();
    private Set<PagoProfesor> listaPagoProfesores = new HashSet<>();
    private Set<SaldoPagoProfesor> listaSaldoPagoProfesores = new HashSet<>();
    private Set<Usuario> listaUsuarios = new HashSet<>();
    private Set<Modulo> listaModulos = new HashSet<>();
    private Set<Cargo> listaCargos = new HashSet<>();
// private LectorHuella miLector = new LectorHuella();
//  <-----------------CONTROLADORES EXTRA-------------------------->
    private ControladorHuella miLector = new ControladorHuella();
    private ControladorRele miRele = new ControladorRele();
    private ControladorPersistencia miPersistencia = new ControladorPersistencia();

    
//  <-----------------CONSTRUCTOR DEL CONTROLADOR PRINCIPAL------------------->

    public ControladorPrincipal(){
        try {
             this.listaAlumnos = miPersistencia.getAlumnos();
            this.listaAsistenciaAlumno = miPersistencia.getAsistenciaAlumno();
            this.listaAsistenciaProfesor = miPersistencia.getAsistenciaProfesor();
            this.listaCargos = miPersistencia.getCargos();
            this.listaClases = miPersistencia.getClases();
            this.listaCobroCuota = miPersistencia.getCuota();
            this.listaModalidades = miPersistencia.getModalidades();
            this.listaModulos = miPersistencia.getModulos();
            this.listaPagoProfesores = miPersistencia.getPagoProfesores();
            this.listaProfesores = miPersistencia.getProfesores();
            this.listaSaldoCuota = miPersistencia.getSaldoCuota();
            this.listaSaldoPagoProfesores = miPersistencia.getSaldoPagoProfesores();
            this.listaSectores = miPersistencia.getSectores();
            this.listaUsuarios = miPersistencia.getUsuarios();
            this.miLector = miPersistencia.getLector();
        } catch (Notificaciones ex) {
            Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//  <-----------------BUSQUEDAS-------------------> 
    
    public Alumno buscarAlumno(String nombrealu, String apellido){
        Alumno unAlumno = null;
        for(Alumno miAlumno:this.listaAlumnos){
            if(miAlumno.getNombrealumno().equalsIgnoreCase(nombrealu) && miAlumno.getApellidoalumno().equalsIgnoreCase(apellido)){
                unAlumno = miAlumno;
                break;
            }
        }
        return unAlumno;
    }
    
//  <-----------------ABMS-------------------> 
    public void bajaAlumno(String nombreAlu, String apellido) throws Notificaciones{
        Alumno miAlumno = buscarAlumno(nombreAlu,apellido);
        if(miAlumno!=null){
            try {
                this.listaAlumnos.remove(miAlumno);
                miAlumno.setEstado("BAJA");
                this.miPersistencia.persistirInstancia(miAlumno);
            } catch (Notificaciones ex) {
                throw new Notificaciones(ex.getMessage()); 
            }
        }
    }
    
    public void agregarAlumno(String nombreAlu, String apellido) throws Notificaciones{
        if(buscarAlumno(nombreAlu,apellido)!=null){//FALTA VERIFICAR SI EL ESTADO ESTA EN BAJA
            throw new Notificaciones("El Alumno ya existe");
        }else{
            Alumno unAlumno = new Alumno(nombreAlu,apellido);
            this.listaAlumnos.add(unAlumno);
            this.miPersistencia.persistirInstancia(unAlumno);
        }
    }
    
//  <-----------------LISTA DE GETTERS Y SETTERS------------------->

    public ControladorRele getMiRele() {
        return miRele;
    }

    public void setMiRele(ControladorRele miRele) {
        this.miRele = miRele;
    }

    public Set<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(Set<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public Set<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(Set<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public Set<Modalidad> getListaModalidades() {
        return listaModalidades;
    }

    public void setListaModalidades(Set<Modalidad> listaModalidades) {
        this.listaModalidades = listaModalidades;
    }

    public Set<Sector> getListaSectores() {
        return listaSectores;
    }

    public void setListaSectores(Set<Sector> listaSectores) {
        this.listaSectores = listaSectores;
    }

    public Set<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(Set<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    public Set<AsistenciaAlumno> getListaAsistenciaAlumno() {
        return listaAsistenciaAlumno;
    }

    public void setListaAsistenciaAlumno(Set<AsistenciaAlumno> listaAsistenciaAlumno) {
        this.listaAsistenciaAlumno = listaAsistenciaAlumno;
    }

    public Set<AsistenciaProfesor> getListaAsistenciaProfesor() {
        return listaAsistenciaProfesor;
    }

    public void setListaAsistenciaProfesor(Set<AsistenciaProfesor> listaAsistenciaProfesor) {
        this.listaAsistenciaProfesor = listaAsistenciaProfesor;
    }

    public Set<Cuota> getListaCuotas() {
        return listaCuotas;
    }

    public void setListaCuotas(Set<Cuota> listaCuotas) {
        this.listaCuotas = listaCuotas;
    }

    public Set<CobroCuota> getListaCobroCuota() {
        return listaCobroCuota;
    }

    public void setListaCobroCuota(Set<CobroCuota> listaCobroCuota) {
        this.listaCobroCuota = listaCobroCuota;
    }

    public Set<SaldoCuota> getListaSaldoCuota() {
        return listaSaldoCuota;
    }

    public void setListaSaldoCuota(Set<SaldoCuota> listaSaldoCuota) {
        this.listaSaldoCuota = listaSaldoCuota;
    }

    public Set<PagoProfesor> getListaPagoProfesores() {
        return listaPagoProfesores;
    }

    public void setListaPagoProfesores(Set<PagoProfesor> listaPagoProfesores) {
        this.listaPagoProfesores = listaPagoProfesores;
    }

    public Set<SaldoPagoProfesor> getListaSaldoPagoProfesores() {
        return listaSaldoPagoProfesores;
    }

    public void setListaSaldoPagoProfesores(Set<SaldoPagoProfesor> listaSaldoPagoProfesores) {
        this.listaSaldoPagoProfesores = listaSaldoPagoProfesores;
    }

    public Set<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(Set<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Set<Modulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(Set<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public Set<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(Set<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public ControladorHuella getMiLector() {
        return miLector;
    }

    public void setMiLector(ControladorHuella miLector) {
        this.miLector = miLector;
    }
    
}
