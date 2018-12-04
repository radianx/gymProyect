package gimnasio.herramientas.excepciones;

/**
 * Esta clase se utiliza para lanzar todas las excepciones que se envían a la
 * vista desde el controlador.
 * @author wj-92
 *
 */
public class Notificaciones extends Exception {


	private static final long serialVersionUID = 4984406577383662317L;

	public Notificaciones(String mensaje) {
		super(mensaje);
	}
}
