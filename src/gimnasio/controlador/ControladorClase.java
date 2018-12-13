/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gimnasio.controlador;

import gimnasio.modelo.Clase;

/**
 *
 * @author Julien_W
 */
public class ControladorClase {

    public Clase buscarClase(Clase clase) {
        Clase unaClase = null;
        for (Clase miClase : this.listaClases) {
            if (miClase.getDescripcionclase().equalsIgnoreCase(clase.getDescripcionclase())) {
                unaClase = miClase;
                break;
            }
        }
        return unaClase;
    }

}
