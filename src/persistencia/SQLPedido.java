package persistencia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPedido {
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
	public SQLPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarPedido(PersistenceManager pm, long codigoPedido, Date fechaEntrega, Double precioTotal,
			String nitProveedor, long idSucursal) 
	{
		Timestamp fechI = new Timestamp(fechaEntrega.getTime()); 
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPedido()+
				" (CODIGOPEDIDO, FECHAENTREGA, PRECIOTOTAL, ESTADOORDEN, NITPROVEEDOR, IDSUCURSAL) values (?, ?, ?, ?, ?, ?)");
		q.setParameters( codigoPedido, fechI, precioTotal, "En progreso" , nitProveedor, idSucursal);
		return (long) q.executeUnique();
	}
	
	public List<Object[]> darProductosPedidosSucursal(PersistenceManager pm, long idPedido) 
	{
		//SELECT * FROM producto JOIN ventaproducto ON producto.codigodebarras = ventaproducto.codigoproducto WHERE ventaproducto.numeroventa = ?
		Query q = pm.newQuery(SQL,
				"SELECT * FROM " + pp.darTablaProducto() + " JOIN " + pp.darTablaProductoPedido() + 
				" ON producto.codigodebarras = productopedido.codigoproducto WHERE productopedido.codigopedido  = ?");
		q.setParameters(idPedido);
		return (List<Object[]>) q.executeList();
	}
	
	
}
