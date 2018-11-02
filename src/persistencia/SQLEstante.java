package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLEstante {
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
	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long insertarEstante(PersistenceManager pm, long idEstante, long idSucursal, double capacidadVolumen,
			double capacidadTotalVolumen, double capacidadPeso, double capacidadTotalPeso) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante  () + "(idsucursal, idestante,capacidadvolumen,capacidadtotalvolumen,capacidadpeso,capacidadtotalpeso) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idSucursal, idEstante, capacidadVolumen, capacidadTotalVolumen, capacidadPeso, capacidadTotalPeso);
        return (long) q.executeUnique();
	}
}
