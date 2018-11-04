package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLAdministrador {

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
	public SQLAdministrador (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	

	public List<Object[]> verificarDatos(PersistenceManager pm, String usuario, String contrasena) throws Exception {
		
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdministrador() + " WHERE USUARIO = ? AND CONTRASENA = ?");
		q.setParameters(usuario, contrasena);
		return q.executeList();
	}

}
