package persistencia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLVenta {
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
	public SQLVenta (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarVenta(PersistenceManager pm, String sucursal, long numeroVenta, String documento,
			String documento2, double precioTotal, Date fecha) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVenta()+"(NUMEROVENTA,TIPODOCCLIENTE,NUMDOCCLIENTE,TOTALVENTA,FECHAVENTA,IDSUCURSAL) values (?,?,?,?,?,?)");
		q.setParameters(numeroVenta, documento, documento2, precioTotal, fecha, sucursal);
		return (long) q.executeUnique();
	}

	public List<Object[]> obtenerDineroRecolectado(PersistenceManager pm, Date fechaInicio, Date fechaFin) {
		
		Timestamp fechI = new Timestamp(fechaInicio.getTime()); 
		Timestamp fechF = new Timestamp(fechaFin.getTime()); 
		
		Query q = pm.newQuery(SQL, "SELECT idsucursal, SUM(totalventa) FROM venta WHERE fechaventa BETWEEN ? AND ? GROUP BY idsucursal");
		q.setParameters(fechI, fechF);
		return (List<Object[]>) q.executeList();
		
	}
}
