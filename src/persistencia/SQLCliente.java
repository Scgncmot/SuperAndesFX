package persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.sun.jmx.snmp.Timestamp;

public class SQLCliente 
{
	//-----------------------------------------------------------
	//-------------------------Constantes------------------------
	//-----------------------------------------------------------	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra aca para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	//-----------------------------------------------------------
	//--------------------------Atributos------------------------
	//-----------------------------------------------------------
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicacion
	 */
	public SQLCliente (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarCliente(PersistenceManager pm,String tipoDocumento, String numDocumento, String nombre, String correo) 
	{		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente()+"(TIPODOCUMENTO,NUMDOCUMENTO,NOMBRE,CORREO) values (?,?,?,?)");
		q.setParameters(tipoDocumento, numDocumento, nombre, correo);
		return (long) q.executeUnique();
	}
	
	public void eliminarCliente (PersistenceManager pm, String tipoDocumento, String numDocumento)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE TIPODOCUMENTO = ? AND NUMDOCUMENTO = ?");
	    q.setParameters(tipoDocumento, numDocumento);
	    q.executeUnique();
	}
	
	public Object[] darClientePorNumDocumento (PersistenceManager pm, String numDocumento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() + " WHERE NUMDOCUMENTO = ?");
		q.setParameters(numDocumento);
		return (Object[]) q.executeUnique();
	}
	
		
	public List<Object[]> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente());
		return (List<Object[]>) q.executeList();
	}

	public void modificarCliente(PersistenceManager pm, String tipoDocAntiguo, String tipoDoc,String numDocAntiguo, String numDoc,String nombre,String correo) {
		
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCliente()+" SET TIPODOCUMENTO = ? , NUMDOCUMENTO = ?, NOMBRE = ?, CORREO = ?, WHERE TIPODOCUMENTO = ? AND NUMDOCUMENTO = ?");
		q.setParameters(tipoDoc, numDoc, nombre, correo, tipoDocAntiguo, numDocAntiguo);
		q.executeUnique();				
	}	
}
