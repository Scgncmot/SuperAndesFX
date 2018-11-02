package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPagueNUnidadesLleveMPromo {
	
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
	public SQLPagueNUnidadesLleveMPromo (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarPromocion(PersistenceManager pm, String codigoPromocion, int compraUnidades, int llevaUnidades)
	{
		Query q = pm.newQuery(SQL,"INSERT INTO "+pp.darTablaPagueNUnidadesLleveMPromo()+"(CODIGOPROMO,COMPRAUNIDADES,LLEVAUNIDADES) VALUES (?,?,?)");
		q.setParameters(codigoPromocion,compraUnidades,llevaUnidades);
		return (long) q.executeUnique();

	}
	public long eliminarPromocion(PersistenceManager pm, String codigoPromo){
		Query q = pm.newQuery(SQL,"DELETE FROM "+pp.darTablaPagueNUnidadesLleveMPromo()+" "
				+ "WHERE CODIGOPROMO = ? ");
		q.setParameters(codigoPromo);
		return (long) q.executeUnique();
	}
}
