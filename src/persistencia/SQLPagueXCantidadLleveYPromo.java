package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPagueXCantidadLleveYPromo {
	
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
	public SQLPagueXCantidadLleveYPromo (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	public long adicionarPromocion(PersistenceManager pm, String codigoPromo, int cantidadPaga, int cantidadLleva) {
		Query q = pm.newQuery(SQL,"INSERT INTO "+pp.darTablaPagueXCantidadLleveYPromo()+"(CODIGOPROMO,cantidadpaga,cantidadlleva) VALUES (?,?,?)");
		q.setParameters(codigoPromo,cantidadPaga,cantidadLleva);
		return (long) q.executeUnique();
	}
	public long eliminarPromocion(PersistenceManager pm, String codigoPromo){
		Query q = pm.newQuery(SQL,"DELETE FROM "+pp.darTablaPagueXCantidadLleveYPromo()+" "
				+ "WHERE CODIGOPROMO = ? ");
		q.setParameters(codigoPromo);
		return (long) q.executeUnique();
	}

}
