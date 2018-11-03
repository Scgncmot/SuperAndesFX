package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLEstante 
{
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
	public SQLEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
		
	public long adicionarEstante(PersistenceManager pm, long id, long idSucursal, long idCategoria, 
			Double volumenMaximo, Double pesoMaximo, Integer nivelDeAbastecimiento) 
	{		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante()+"(id,  idSucursal,  idCategoria, "+
			 "volumenActual,  volumenMaximo,  pesoActual, pesoMaximo,  nivelDeAbastecimiento)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(id, idSucursal,idCategoria, 0, volumenMaximo, 0,  pesoMaximo, nivelDeAbastecimiento);
		return (long) q.executeUnique();
	}
	
	public List<Object[]> darEstanteBodegaPorIdSucursal(PersistenceManager pm, long idSucursal) 
	{		
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante() + " WHERE idSucursal = ?");
		q.setParameters(idSucursal);
		return q.executeList();
	}
		
	public long eliminarEstantePorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante() + " WHERE id = ?");
	    q.setParameters(id);
	    return (long) q.executeUnique();
	}	
	
	public long eliminarEstantePorIds(PersistenceManager pm, long id, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante() + " WHERE idSucursal = ? AND id = ?");
	    q.setParameters(idSucursal, id);
	    return (long) q.executeUnique();
	}
}
