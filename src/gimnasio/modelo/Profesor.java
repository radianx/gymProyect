package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Profesor generated by hbm2java
 */
public class Profesor  implements java.io.Serializable {


     private int idprofesor;
     private Contacto contacto;
     private Obrasocial obrasocial;
     private Usuario usuario;
     private String nombreprofesor;
     private String apellidoprofesor;
     private Double peso;
     private Double altura;
     private Date fechanacimiento;
     private String estado;
     private Set pagoProfesors = new HashSet(0);
     private Set<ClaseProfesor> claseProfesors = new HashSet(0);
     private Set<Profesormodalidad> profesorModalidads = new HashSet(0);

    public Profesor() {
    }

	
    public Profesor(Contacto contacto, Obrasocial obrasocial, Usuario usuario, String nombreprofesor, String apellidoprofesor, String estado) {
        this.contacto = contacto;
        this.obrasocial = obrasocial;
        this.usuario = usuario;
        this.nombreprofesor = nombreprofesor;
        this.apellidoprofesor = apellidoprofesor;
        this.estado = estado;
    }
    public Profesor(Contacto contacto, Obrasocial obrasocial, Usuario usuario, String nombreprofesor, String apellidoprofesor, Double peso, Double altura, Date fechanacimiento, String estado) {
       this.contacto = contacto;
       this.obrasocial = obrasocial;
       this.usuario = usuario;
       this.nombreprofesor = nombreprofesor;
       this.apellidoprofesor = apellidoprofesor;
       this.peso = peso;
       this.altura = altura;
       this.fechanacimiento = fechanacimiento;
       this.estado = estado;
    }
   
    public int getIdprofesor() {
        return this.idprofesor;
    }
    
    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }
    public Contacto getContacto() {
        return this.contacto;
    }
    
    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
    public Obrasocial getObrasocial() {
        return this.obrasocial;
    }
    
    public void setObrasocial(Obrasocial obrasocial) {
        this.obrasocial = obrasocial;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getNombreprofesor() {
        return this.nombreprofesor;
    }
    
    public void setNombreprofesor(String nombreprofesor) {
        this.nombreprofesor = nombreprofesor;
    }
    public String getApellidoprofesor() {
        return this.apellidoprofesor;
    }
    
    public void setApellidoprofesor(String apellidoprofesor) {
        this.apellidoprofesor = apellidoprofesor;
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
    public Date getFechanacimiento() {
        return this.fechanacimiento;
    }
    
    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getPagoProfesors() {
        return this.pagoProfesors;
    }
    
    public void setPagoProfesors(Set pagoProfesors) {
        this.pagoProfesors = pagoProfesors;
    }
    public Set<ClaseProfesor> getClaseProfesors() {
        return this.claseProfesors;
    }
    
    public void setClaseProfesors(Set claseProfesors) {
        this.claseProfesors = claseProfesors;
    }
    public Set<Profesormodalidad> getProfesorModalidads() {
        return this.profesorModalidads;
    }
    
    public void setProfesorModalidads(Set profesorModalidads) {
        this.profesorModalidads = profesorModalidads;
    }

    @Override
    public String toString() {
        return this.nombreprofesor + " " + this.apellidoprofesor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idprofesor;
        hash = 97 * hash + Objects.hashCode(this.usuario);
        hash = 97 * hash + Objects.hashCode(this.nombreprofesor);
        hash = 97 * hash + Objects.hashCode(this.apellidoprofesor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (this.idprofesor != other.idprofesor) {
            return false;
        }
        if (!Objects.equals(this.nombreprofesor, other.nombreprofesor)) {
            return false;
        }
        if (!Objects.equals(this.apellidoprofesor, other.apellidoprofesor)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
    
}

