package gimnasio.modelo;
// Generated Dec 7, 2018 12:25:40 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Alumno generated by hbm2java
 */
public class Alumno  implements java.io.Serializable {


     private int idalumno;
     private Usuario usuario;
     private Contacto contacto;
     private String nombrealumno;
     private String apellidoalumno;
     private Double peso;
     private Double altura;
     private String estado;
     private Date fechanacimiento;
     private Set claseAlumnos = new HashSet(0);
     private Set cuotas = new HashSet(0);
     private Set inscripcions = new HashSet(0);

    public Alumno() {
    }

	
    public Alumno(int idalumno, Usuario usuario, Contacto contacto, String nombrealumno, String apellidoalumno) {
        this.idalumno = idalumno;
        this.usuario = usuario;
        this.contacto = contacto;
        this.nombrealumno = nombrealumno;
        this.apellidoalumno = apellidoalumno;
    }
    public Alumno(int idalumno, Usuario usuario, Contacto contacto, String nombrealumno, String apellidoalumno, Double peso, Double altura, String estado, Date fechanacimiento, Set claseAlumnos, Set cuotas, Set inscripcions) {
       this.idalumno = idalumno;
       this.usuario = usuario;
       this.contacto = contacto;
       this.nombrealumno = nombrealumno;
       this.apellidoalumno = apellidoalumno;
       this.peso = peso;
       this.altura = altura;
       this.estado = estado;
       this.fechanacimiento = fechanacimiento;
       this.claseAlumnos = claseAlumnos;
       this.cuotas = cuotas;
       this.inscripcions = inscripcions;
    }
   
    public int getIdalumno() {
        return this.idalumno;
    }
    
    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Contacto getContacto() {
        return this.contacto;
    }
    
    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
    public String getNombrealumno() {
        return this.nombrealumno;
    }
    
    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }
    public String getApellidoalumno() {
        return this.apellidoalumno;
    }
    
    public void setApellidoalumno(String apellidoalumno) {
        this.apellidoalumno = apellidoalumno;
    }
    public Double getPeso() {
        return this.peso;
    }
    
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public Double getAltura() {
        return this.altura;
    }
    
    public void setAltura(Double altura) {
        this.altura = altura;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Date getFechanacimiento() {
        return this.fechanacimiento;
    }
    
    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public Set getClaseAlumnos() {
        return this.claseAlumnos;
    }
    
    public void setClaseAlumnos(Set claseAlumnos) {
        this.claseAlumnos = claseAlumnos;
    }
    public Set getCuotas() {
        return this.cuotas;
    }
    
    public void setCuotas(Set cuotas) {
        this.cuotas = cuotas;
    }
    public Set getInscripcions() {
        return this.inscripcions;
    }
    
    public void setInscripcions(Set inscripcions) {
        this.inscripcions = inscripcions;
    }




}


