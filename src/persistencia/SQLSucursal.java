package persistencia;

import java.sql.Timestamp;
import java.util.Date;
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
	 * Se renombra acÃ¡ para facilitar la escritura de las sentencias
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
	
	/*RFC10 - CONSULTAR CONSUMO EN SUPERANDES
	Se quiere conocer la información de los usuarios que realizaron al menos una compra de un determinado
	producto en un rango de fechas. Los resultados deben ser clasificados según un criterio deseado por quien
	realiza la consulta. En la clasificación debe ofrecerse la posibilidad de agrupamiento y ordenamiento de las
	respuestas según los intereses del usuario que consulta como, por ejemplo, por los datos del cliente, por fecha
	y número de unidades compradas del producto.*/
	
	public List<Object[]> darClientesConAlMenosUnaCompra(PersistenceManager pm, String codigoBarras, Date fechaInicial, Date fechaFinal,  long idSucursal, String ordenamiento)
	{
		Timestamp fechaI = new Timestamp(fechaInicial.getTime());
		Timestamp fechaF = new Timestamp(fechaFinal.getTime());
				
		Query q;
		
		//Se pasa por parámetro -1 como referencia de que se esta invocando a nivel global
		if(idSucursal == -1) 
		{			
			q = pm.newQuery(SQL, "SELECT TIPODOCUMENTO, NUMDOCUMENTO, NOMBRE, CORREO FROM ((VENTAPRODUCTO prod INNER JOIN VENTA ven ON prod.NUMEROVENTA = ven.NUMEROVENTA) INNER JOIN CLIENTE cli ON ven.NUMDOCCLIENTE = cli.NUMDOCUMENTO) WHERE prod.CODIGOPRODUCTO = ? AND ven.FECHAVENTA BETWEEN ? AND ? ORDER BY ?");
			q.setParameters(codigoBarras, fechaI, fechaF, ordenamiento);			
		}		
		else 
		{			
			q = pm.newQuery(SQL, "SELECT TIPODOCUMENTO, NUMDOCUMENTO, NOMBRE, CORREO FROM ((VENTAPRODUCTO prod INNER JOIN VENTA ven ON prod.NUMEROVENTA = ven.NUMEROVENTA) INNER JOIN CLIENTE cli ON ven.NUMDOCCLIENTE = cli.NUMDOCUMENTO) WHERE prod.CODIGOPRODUCTO = ? AND ven.FECHAVENTA BETWEEN ? AND ? AND ven.IDSUCURSAL = ? ORDER BY ?");
			q.setParameters(codigoBarras, fechaI, fechaF, idSucursal, ordenamiento);				
		}		
	
		
		return q.executeList();
	}

	public List<Object[]> darClientesSinCompras(PersistenceManager pm, String codigoBarras, Date fechaInicio, Date fechaFinal, long sucursalId, String ordenamiento) {
		
		Timestamp fechaI = new Timestamp(fechaInicio.getTime());
		Timestamp fechaF = new Timestamp(fechaFinal.getTime());
				
		Query q;
		
		//Se pasa por parámetro -1 como referencia de que se esta invocando a nivel global
		if(sucursalId == -1) 
		{			
			q = pm.newQuery(SQL, "SELECT TIPODOCUMENTO, NUMDOCUMENTO, NOMBRE, CORREO FROM CLIENTE MINUS (SELECT TIPODOCUMENTO, NUMDOCUMENTO, NOMBRE, CORREO FROM ((VENTAPRODUCTO prod INNER JOIN VENTA ven ON prod.NUMEROVENTA = ven.NUMEROVENTA) INNER JOIN CLIENTE cli ON ven.NUMDOCCLIENTE = cli.NUMDOCUMENTO) WHERE prod.CODIGOPRODUCTO = ? AND ven.FECHAVENTA BETWEEN ? AND ?) ORDER BY NOMBRE");
			q.setParameters(codigoBarras, fechaI, fechaF);			
		}		
		else 
		{			
			q = pm.newQuery(SQL, "SELECT TIPODOCUMENTO, NUMDOCUMENTO, NOMBRE, CORREO FROM CLIENTE MINUS (SELECT TIPODOCUMENTO, NUMDOCUMENTO, NOMBRE, CORREO FROM ((VENTAPRODUCTO prod INNER JOIN VENTA ven ON prod.NUMEROVENTA = ven.NUMEROVENTA) INNER JOIN CLIENTE cli ON ven.NUMDOCCLIENTE = cli.NUMDOCUMENTO) WHERE prod.CODIGOPRODUCTO = ? AND ven.FECHAVENTA BETWEEN ? AND ? AND ven.IDSUCURSAL = ?) ORDER BY NOMBRE");
			q.setParameters(codigoBarras, fechaI, fechaF, sucursalId);				
		}		
	
		
		return q.executeList();
	}
}
