package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProveedor {
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
	public SQLProveedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarProveedor(PersistenceManager pm, String nit, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor()+"(NIT,NOMBRE) values (?,?)");
		q.setParameters(nit,nombre);
		return (long) q.executeUnique();

	}
	
	public List<Object[]> darProveedores(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor());
		return (List<Object[]>) q.executeList();
	}

	public void eliminarPorNombre(PersistenceManager pm, String proveedor) {

		Query q = pm.newQuery(SQL, "DELETE FROM " +pp.darTablaProveedor()+" WHERE NOMBRE = ?");
		q.setParameters(proveedor);		
		q.executeUnique();			
	}

	public void modificarProveedor(PersistenceManager pm, String nombreAntiguo ,String nitAntiguo ,String nit, String nombre) {

		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProveedor()+" SET NOMBRE = ? , NIT = ? WHERE NIT = ?");
		q.setParameters(nombre,nit,nitAntiguo);
		q.executeUnique();		
	}




}
