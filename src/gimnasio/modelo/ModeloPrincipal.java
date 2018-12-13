/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.modelo;

import gimnasio.controlador.ControladorPersistencia;
import gimnasio.herramientas.excepciones.Notificaciones;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Julien_W
 */
public class ModeloPrincipal {
    
    
    private List<Alumno> listaAlumnos = new ArrayList<>();
    private List<ClaseAlumno> listaClaseAlumno = new ArrayList<>();
    private List<Profesor> listaProfesores = new ArrayList<>();
    private List<Modalidad> listaModalidades = new ArrayList<>();
    private List<Profesormodalidad> listaProfesorModalidad = new ArrayList<>();
    private List<Sector> listaSectores = new ArrayList<>();
    private List<Clase> listaClases = new ArrayList<>();
    private List<AsistenciaAlumno> listaAsistenciaAlumno = new ArrayList<>();
    private List<AsistenciaProfesor> listaAsistenciaProfesor = new ArrayList<>();
    private List<Cuota> listaCuotas = new ArrayList<>();
    private List<CobroCuota> listaCobroCuota = new ArrayList<>();
    private List<SaldoCuota> listaSaldoCuota = new ArrayList<>();
    private List<PagoProfesor> listaPagoProfesores = new ArrayList<>();
    private List<Saldopagoprofesor> listaSaldoPagoProfesores = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Modulo> listaModulos = new ArrayList<>();
    private List<Cargo> listaCargos = new ArrayList<>();
    private List<Obrasocial> listaObraSociales = new ArrayList<>();
    private List<Contacto> listaContactos = new ArrayList<>();
    private List<Personal> listaPersonal = new ArrayList<>();
    private List<CargoPersonal> listaCargoPersonal = new ArrayList<>();
    private List<ClaseProfesor> listaClaseProfesor = new ArrayList<>();
    private List<SectorClase>  listaSectorClase = new ArrayList<>();
    private List<AperturaCajaDiaria> listaAperturaCajaDiaria = new ArrayList<>();
    private List<Cajadiaria> listaCajasDiarias = new ArrayList<>();
    private List<Documentacion> listaDocumentacion = new ArrayList<>();

    public ModeloPrincipal() {
        
    }
    
    
    
    
    
//  <-----------------LISTA DE GETTERS Y SETTERS------------------->

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public List<ClaseAlumno> getListaClaseAlumno() {
        return listaClaseAlumno;
    }

    public void setListaClaseAlumno(List<ClaseAlumno> listaClaseAlumno) {
        this.listaClaseAlumno = listaClaseAlumno;
    }

    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public List<Modalidad> getListaModalidades() {
        return listaModalidades;
    }

    public void setListaModalidades(List<Modalidad> listaModalidades) {
        this.listaModalidades = listaModalidades;
    }

    public List<Profesormodalidad> getListaProfesorModalidad() {
        return listaProfesorModalidad;
    }

    public void setListaProfesorModalidad(List<Profesormodalidad> listaProfesorModalidad) {
        this.listaProfesorModalidad = listaProfesorModalidad;
    }

    public List<Sector> getListaSectores() {
        return listaSectores;
    }

    public void setListaSectores(List<Sector> listaSectores) {
        this.listaSectores = listaSectores;
    }

    public List<Clase> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    public List<AsistenciaAlumno> getListaAsistenciaAlumno() {
        return listaAsistenciaAlumno;
    }

    public void setListaAsistenciaAlumno(List<AsistenciaAlumno> listaAsistenciaAlumno) {
        this.listaAsistenciaAlumno = listaAsistenciaAlumno;
    }

    public List<AsistenciaProfesor> getListaAsistenciaProfesor() {
        return listaAsistenciaProfesor;
    }

    public void setListaAsistenciaProfesor(List<AsistenciaProfesor> listaAsistenciaProfesor) {
        this.listaAsistenciaProfesor = listaAsistenciaProfesor;
    }

    public List<Cuota> getListaCuotas() {
        return listaCuotas;
    }

    public void setListaCuotas(List<Cuota> listaCuotas) {
        this.listaCuotas = listaCuotas;
    }

    public List<CobroCuota> getListaCobroCuota() {
        return listaCobroCuota;
    }

    public void setListaCobroCuota(List<CobroCuota> listaCobroCuota) {
        this.listaCobroCuota = listaCobroCuota;
    }

    public List<SaldoCuota> getListaSaldoCuota() {
        return listaSaldoCuota;
    }

    public void setListaSaldoCuota(List<SaldoCuota> listaSaldoCuota) {
        this.listaSaldoCuota = listaSaldoCuota;
    }

    public List<PagoProfesor> getListaPagoProfesores() {
        return listaPagoProfesores;
    }

    public void setListaPagoProfesores(List<PagoProfesor> listaPagoProfesores) {
        this.listaPagoProfesores = listaPagoProfesores;
    }

    public List<Saldopagoprofesor> getListaSaldoPagoProfesores() {
        return listaSaldoPagoProfesores;
    }

    public void setListaSaldoPagoProfesores(List<Saldopagoprofesor> listaSaldoPagoProfesores) {
        this.listaSaldoPagoProfesores = listaSaldoPagoProfesores;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Modulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public List<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public List<Obrasocial> getListaObraSociales() {
        return listaObraSociales;
    }

    public void setListaObraSociales(List<Obrasocial> listaObraSociales) {
        this.listaObraSociales = listaObraSociales;
    }

    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public List<Personal> getListaPersonal() {
        return listaPersonal;
    }

    public void setListaPersonal(List<Personal> listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    public List<CargoPersonal> getListaCargoPersonal() {
        return listaCargoPersonal;
    }

    public void setListaCargoPersonal(List<CargoPersonal> listaCargoPersonal) {
        this.listaCargoPersonal = listaCargoPersonal;
    }

    public List<ClaseProfesor> getListaClaseProfesor() {
        return listaClaseProfesor;
    }

    public void setListaClaseProfesor(List<ClaseProfesor> listaClaseProfesor) {
        this.listaClaseProfesor = listaClaseProfesor;
    }

    public List<SectorClase> getListaSectorClase() {
        return listaSectorClase;
    }

    public void setListaSectorClase(List<SectorClase> listaSectorClase) {
        this.listaSectorClase = listaSectorClase;
    }

    public List<AperturaCajaDiaria> getListaAperturaCajaDiaria() {
        return listaAperturaCajaDiaria;
    }

    public void setListaAperturaCajaDiaria(List<AperturaCajaDiaria> listaAperturaCajaDiaria) {
        this.listaAperturaCajaDiaria = listaAperturaCajaDiaria;
    }

    public List<Cajadiaria> getListaCajasDiarias() {
        return listaCajasDiarias;
    }

    public void setListaCajasDiarias(List<Cajadiaria> listaCajasDiarias) {
        this.listaCajasDiarias = listaCajasDiarias;
    }

    public List<Documentacion> getListaDocumentacion() {
        return listaDocumentacion;
    }

    public void setListaDocumentacion(List<Documentacion> listaDocumentacion) {
        this.listaDocumentacion = listaDocumentacion;
    }

}
