/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.herramientas.excepciones.Notificaciones;
import gimnasio.modelo.ModeloPrincipal;
import gimnasio.modelo.Usuario;
import java.util.List;

/**
 *
 * @author Julien_W
 */
public class ControladorUsuario {

    private ControladorPersistencia miPersistencia;
    private List<Usuario> listaUsuarios;
  
    
    
    public ControladorUsuario(ControladorPersistencia persistencia, List<Usuario> usuarios) throws Notificaciones {
        this.miPersistencia = persistencia;
        this.listaUsuarios = miPersistencia.getUsuarios();
    }

    
    
    

    public void altaUsuario(Usuario usuario) throws Notificaciones {
        Usuario unUsuario = buscarUsuarioAlta(usuario.getNombreusuario());
        String estado = "ACTIVO";
        usuario.setEstado(estado);
        if (unUsuario == null) {
            this.listaUsuarios.add(usuario);
            this.miPersistencia.persistirInstancia(usuario);
        } else {
            unUsuario.setEstado(estado);
            unUsuario.setContrasenia(usuario.getContrasenia());
            unUsuario.setPlanillahuellas(usuario.getPlanillahuellas());
            unUsuario.setFoto(usuario.getFoto());
            this.miPersistencia.persistirInstancia(unUsuario);
            this.listaUsuarios = miPersistencia.getUsuarios();

        }
    }

    public void bajaUsuario(int idUsuario) throws Notificaciones {
        Usuario unUsuario = buscarUsuarioBaja(idUsuario);
        String estado = "INACTIVO";
        unUsuario.setEstado(estado);
        this.miPersistencia.persistirInstancia(unUsuario);
    }

    public Usuario buscarUsuario(String nombreUsuario, String contrasenia) {
        Usuario unUsuario = null;
        for (Usuario miUsuario : this.listaUsuarios) {
            if (miUsuario.getNombreusuario().equalsIgnoreCase(nombreUsuario) && miUsuario.getContrasenia().equals(contrasenia)) {
                unUsuario = miUsuario;
                break;
            }
        }
        return unUsuario;
    }

    public Usuario buscarUsuarioAlta(String nombreUsuario) {
        Usuario unUsuario = new Usuario();
        unUsuario = null;
        for (Usuario miUsuario : this.listaUsuarios) {
            if (miUsuario.getNombreusuario().equalsIgnoreCase(nombreUsuario)) {
                unUsuario = miUsuario;
                break;
            }
        }
        return unUsuario;
    }

    public Usuario buscarUsuarioBaja(int idUsuario) {
        Usuario unUsuario = null;
        for (Usuario miUsuario : this.listaUsuarios) {
            if (miUsuario.getIdusuario() == idUsuario) {
                unUsuario = miUsuario;
                break;
            }
        }
        return unUsuario;
    }

    public List<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }

}
