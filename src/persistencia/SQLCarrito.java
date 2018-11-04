package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCarrito 
{
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLCarrito(PersistenciaSuperAndes persistenciaSuperAndes) 
	{
		
		this.pp = persistenciaSuperAndes;
	}
	
	public long crearCarrito(PersistenceManager pm, long id, String tipoDocumentoCliente, String numDocumentoCliente) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarrito() + "(id, tipoDocumentoCliente, numDocumentoCliente) VALUES (?, ?, ?)");
		q.setParameters( id, tipoDocumentoCliente, numDocumentoCliente);
		return (long) q.executeUnique();
	}
	
	public void modificarCarritoCliente(PersistenceManager pm, long idCarrito, String tipoDocumentoClienteNuevo,
			String numDocumentoClienteNuevo) 
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCarrito()
				+ " SET tipoDocumentoCliente = ? , numDocumentoCliente = ?, WHERE id = ?");
		q.setParameters( tipoDocumentoClienteNuevo, numDocumentoClienteNuevo, idCarrito);
		q.executeUnique();
	}
	
	public void eliminarCarrito(PersistenceManager pm, long idCarrito, String tipoDocumentoCliente, String numDocumentoCliente)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito() + " WHERE id = ? AND numDocumentoCliente = ? AND tipoDocumentoCliente = ?");
	    q.setParameters(idCarrito, numDocumentoCliente, tipoDocumentoCliente);
	    q.executeUnique();
	}	
	
	public void eliminarCarritoPorId(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito() + " WHERE idCarrito = ?");
	    q.setParameters(idCarrito);
	    q.executeUnique();
	}
	
	//Puede borar mas de un carrito
	public void eliminarCarritoPorCliente(PersistenceManager pm, String tipoDocumentoCliente, String numDocumentoCliente)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito() + " WHERE tipoDocumentoCliente = ? AND numDocumentoCliente = ?");
		q.setParameters(tipoDocumentoCliente, numDocumentoCliente);
		q.executeUnique();
	}
}
