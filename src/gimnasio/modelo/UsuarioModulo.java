package gimnasio.modelo;
// Generated Dec 8, 2018 5:14:35 PM by Hibernate Tools 4.3.1



/**
 * UsuarioModulo generated by hbm2java
 */
public class UsuarioModulo  implements java.io.Serializable {


     private UsuarioModuloId id;
     private Modulo modulo;
     private Usuario usuario;
     private String estado;

    public UsuarioModulo() {
    }

    public UsuarioModulo(UsuarioModuloId id, Modulo modulo, Usuario usuario, String estado) {
       this.id = id;
       this.modulo = modulo;
       this.usuario = usuario;
       this.estado = estado;
    }
   
    public UsuarioModuloId getId() {
        return this.id;
    }
    
    public void setId(UsuarioModuloId id) {
        this.id = id;
    }
    public Modulo getModulo() {
        return this.modulo;
    }
    
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}

