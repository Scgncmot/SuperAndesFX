package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLDescPorcentajePromo {

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
	public SQLDescPorcentajePromo (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarPromocion(PersistenceManager pm, String codigoPromo, double porcentaje) {
		Query q = pm.newQuery(SQL,"INSERT INTO "+pp.darTablaDescPorcentajePromo()+"(CODIGOPROMO,porcentajedesc) VALUES (?,?)");
		q.setParameters(codigoPromo,porcentaje);
		return (long) q.executeUnique();
	}
	public long eliminarPromocion(PersistenceManager pm, String codigoPromo){
		Query q = pm.newQuery(SQL,"DELETE FROM "+pp.darTablaDescPorcentajePromo()+" "
				+ "WHERE CODIGOPROMO = ? ");
		q.setParameters(codigoPromo);
		return (long) q.executeUnique();
	}

}
