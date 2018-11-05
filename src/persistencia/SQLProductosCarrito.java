package persistencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductosCarrito 
{
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLProductosCarrito(PersistenciaSuperAndes persistenciaSuperAndes) 
	{		
		this.pp = persistenciaSuperAndes;
	}
	
	public long agregarProductosCarrito(PersistenceManager pm, long idCarrito, String codigoBarras, String cantidad) 
	{
		int canti = Integer.parseInt(cantidad);
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + PersistenciaSuperAndes.darTablaProductosCarrito() + " (IDCARRITO, CODIGOBARRASPRODUCTO,CANTIDADPRODUCTO) values (?,?,?)"); 
		q.setParameters(idCarrito, codigoBarras,canti);
		return (long) q.executeUnique();
	}
	
	public List<Object[]> darProductosCarrito(PersistenceManager pm, long idCarrito) 
	{		
		Query q = pm.newQuery(SQL,
				"SELECT * FROM " + PersistenciaSuperAndes.darTablaProducto() + " JOIN " + pp.darTablaProductosCarrito() + " ON "
				+ "PRODUCTOSCARRITO.CODIGOBARRASPRODUCTO = PRODUCTO.CODIGODEBARRAS WHERE productoscarrito.idcarrito = ?");
		q.setParameters(idCarrito);
		return q.executeList();
	}

	public long eliminarProductoCarrito(PersistenceManager pm, long idCarrito, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + PersistenciaSuperAndes.darTablaProductosCarrito() + " WHERE idCarrito = ? AND codigoBarras = ?");
	    q.setParameters(idCarrito, codigoBarras);
	    return (long) q.executeUnique();
	}

	public int darCantidad(PersistenceManager pm, long idcarro, String idproducto) {
		
		Query q = pm.newQuery(SQL,
				"SELECT CANTIDADPRODUCTO FROM " +pp.darTablaProductosCarrito() + " WHERE IDCARRITO = ? AND CODIGOBARRASPRODUCTO = ?");
		q.setParameters(idcarro, idproducto);
		return  ((BigDecimal) q.executeUnique()).intValue();


	}
}
