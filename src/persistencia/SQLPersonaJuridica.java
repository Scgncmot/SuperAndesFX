package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPersonaJuridica {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPersonaJuridica(PersistenciaSuperAndes pp) {
		this.pp = pp;
	}

	public long adicionarPersonaJuridica(PersistenceManager pm, String tipoDocumento, String numDocumento,
			String direccion) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonaJuridica()+" (TIPODOCUMENTO,NUMDOCUMENTO,DIRECCION) values (?,?,?)");
		q.setParameters(tipoDocumento,numDocumento,direccion);
		return (long) q.executeUnique();
	}

	public String buscarDireccion(PersistenceManager pm, String numDoc) {
		Query q = pm.newQuery(SQL, "SELECT DIRECCION FROM " + pp.darTablaPersonaJuridica()+" WHERE NUMDOCUMENTO = ?");
		q.setParameters(numDoc);
		return (String) q.executeUnique();
		
	}

	public void modificarPersonaJuridica(PersistenceManager pm, String numDocAntiguo, String numDoc, String nombre, String direccion) {
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPersonaJuridica()+" SET NUMDOCUMENTO = ?, NOMBRE = ?, DIRECCION = ?, WHERE NUMDOCUMENTO = ?");
		q.setParameters(numDoc,nombre,direccion,numDocAntiguo);
		q.executeUnique();		
	}

}
