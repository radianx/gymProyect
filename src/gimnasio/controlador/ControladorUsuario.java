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
  
    
    
    public ControladorUsuario(ControladorPersistencia persistencia) throws Notificaciones {
        this.miPersistencia = persistencia;
        this.listaUsuarios = miPersistencia.getUsuarios();
    }

    public void altaUsuario(Usuario usuario) throws Notificaciones {
        this.miPersistencia.persistirInstancia(usuario);
        this.listaUsuarios = miPersistencia.getUsuarios();
    }

    public void bajaUsuario(int idUsuario) throws Notificaciones {
        Usuario unUsuario = buscarUsuarioBaja(idUsuario);
        String estado = "INACTIVO";
        unUsuario.setEstado(estado);
        unUsuario.setPlanillahuellas(null);
        int i = this.listaUsuarios.lastIndexOf(unUsuario);
        if(i != -1){
            this.listaUsuarios.set(i, unUsuario);
        }else{
            throw new Notificaciones("Lista de usuarios no actualizada");
        }
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
        Usuario unUsuario = null;
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

    public List<Usuario> getListaUsuarios() throws Notificaciones {
        miPersistencia.actualizarInstancias();
        listaUsuarios = miPersistencia.getUsuarios();
        return this.listaUsuarios;
    }

}
