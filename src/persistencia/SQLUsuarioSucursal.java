package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUsuarioSucursal {


	// -----------------------------------------------------------
	// -------------------------Constantes------------------------
	// -----------------------------------------------------------
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las
	 * sentencias de acceso a la base de datos Se renombra aca para facilitar la
	 * escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	// -----------------------------------------------------------
	// --------------------------Atributos------------------------
	// -----------------------------------------------------------
	/**
	 * El manejador de persistencia general de la aplicacion
	 */
	private PersistenciaSuperAndes pp;

	// -----------------------------------------------------------
	// --------------------------Metodos--------------------------
	// -----------------------------------------------------------
	public SQLUsuarioSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public List<Object[]> verificarDatos(PersistenceManager pm, String user, String pass) {

		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuarioSucursal() + " WHERE USUARIO = ? AND CONTRASENA = ?");
		q.setParameters(user, pass);
		return q.executeList();

		
	}

}
