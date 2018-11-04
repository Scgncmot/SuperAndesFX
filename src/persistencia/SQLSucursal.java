package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class SQLSucursal 
{
	//-----------------------------------------------------------
	//-------------------------Constantes------------------------
	//-----------------------------------------------------------
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	//-----------------------------------------------------------
	//--------------------------Atributos------------------------
	//-----------------------------------------------------------
	/**
	 * El manejador de persistencia general de la aplicacion
	 */
	private PersistenciaSuperAndes pp;

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicacion
	 */
	public SQLSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long insertarSucursal(PersistenceManager pm,long idSucursal, String nombre, String segmentacion, Double tamano,
			String ciudad, String direccion)
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal() + "(IDSUCURSAL,NOMBRE,SEGMENTACION,TAMANO,CIUDAD,DIRECCION) values (?, ?, ?, ?, ?, ?)");
	     q.setParameters(idSucursal, nombre, segmentacion, tamano, ciudad, direccion);
	     return (long) q.executeUnique();   
	}
	
	public long eliminarSucursal (PersistenceManager pm, long id, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal() + " WHERE nombre = ? AND idSucursal = ?");
	    q.setParameters(nombre, id);
	    return (long) q.executeUnique();
	}
	
	public long modificarSucursalPorNombre(PersistenceManager pm,String nombreActual ,String nombreNuevo, String segmentacion,
			Double tamano, String ciudad, String direccion) 
	{		
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaSucursal() + " SET nombre = ? , segmentacion = ?, tamano = ?, ciudad = ?, direccion = ? "
				+ "WHERE nombre = ?");
		q.setParameters(nombreNuevo, segmentacion, tamano, ciudad, direccion, nombreActual);
		return (long) q.executeUnique();
	}	
	
	
	public Object[] darSucursalPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (Object[]) q.executeUnique();
	}
	
	public Object[] darSucursalPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal() + " WHERE idSucursal = ?");
		q.setParameters(id);
		return (Object[]) q.executeUnique();
	}
	
	public List<Object[]> darVentasSucursal(PersistenceManager pm, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVenta() + " WHERE idSucursal = ?");
		q.setParameters(idSucursal);
		return q.executeList();
	}
	
	public List<Object[]> darPedidosSucursal(PersistenceManager pm, long idSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPedido() + " WHERE idSucursal = ?");
		q.setParameters(idSucursal);
		return q.executeList();
	}
	
	
	
	public List<Object[]> darIndiceOcupacion(PersistenceManager pm, long idSucursal){
		Query q = pm.newQuery(SQL, "SELECT * FROM ( SELECT idEstante as id_Elemento, 'ESTANTE' as TIPO, CAPACIDADVOLUMEN / CAPACIDADTOTALVOLUMEN as INDICE_VOLUMEN, CAPACIDADPESO / CAPACIDADTOTALPESO as INDICE_PESO "
				+ "FROM ESTANTE WHERE idsucursal = ? "
				+ "UNION ALL SELECT idBodega as id_Elemento,'BODEGA' AS TIPO,CAPACIDADVOLUMEN / CAPACIDADTOTALVOLUMEN AS INDICE_VOLUMEN, CAPACIDADPESO / CAPACIDADTOTALPESO as INDICE_PESO "
				+ "FROM BODEGA WHERE idsucursal= ?" 
				+")");
		q.setParameters(idSucursal,idSucursal);

		return q.executeList();
	}
}
