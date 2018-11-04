package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoSucursal 
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
	public SQLProductoSucursal(PersistenciaSuperAndes pp) 
	{
		
		this.pp = pp;
	}
	
	public long agregarProductosSucursal(PersistenceManager pm, long idSucursal, String codigoBarras, Double precioUnitario, Double precioUnidadMedida,
			Integer nivelDeReorden, Integer cantidadRecompra) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + PersistenciaSuperAndes.darTablaProductoSucursal()+ 
				" (idSucursal, codigoBarras, precioUnitario, precioUnidadMedida, nivelDeReorden, cantidadRecompra) values (?, ?, ?, ?, ?, ?)"); 
		q.setParameters(idSucursal, codigoBarras, precioUnitario, precioUnidadMedida, nivelDeReorden, cantidadRecompra);
		return (long) q.executeUnique();
	}
	
	public List<Object[]> darProductosPorIdSucursal(PersistenceManager pm, long idSucursal) 
	{
		//SELECT * FROM PRODUCTOSUCURSAL JOIN PRODUCTO ON PRODUCTOSUCURSAL.CODIGOBARRAS = PRODUCTO.CODIGODEBARRAS WHERE PRODUCTOSUCURSAL.IDSUCURSAL = ?;
		Query q = pm.
				newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal() + " JOIN " + pp.darTablaProducto() 
				+" ON " + pp.darTablaProductoSucursal() + ".codigoBarras = " + pp.darTablaProducto() + ".CODIGODEBARRAS WHERE "
				+ pp.darTablaProductoSucursal() + ".idSucursal = ?");
		q.setParameters(idSucursal);
		return q.executeList();
	}	
	
	public long eliminarProductoSucursalPorIds(PersistenceManager pm, long idSucursal, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoSucursal() + " WHERE idSucursal = ? AND codigoBarras = ?");
	    q.setParameters(idSucursal, codigoBarras);
	    return (long) q.executeUnique();
	}
}
