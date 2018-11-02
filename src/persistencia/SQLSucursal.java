package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.IndiceOcupacion;
import negocio.Producto;

public class SQLSucursal {
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
	public SQLSucursal (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long insertarSucursal(PersistenceManager pm,long idSucursal, String nombre, String segmentacion, String tamano, String ciudad, String direccion)
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal() + "(IDSUCURSAL,NOMBRE,SEGMENTACION,TAMANO,CIUDAD,DIRECCION) values (?, ?, ?, ?, ?, ?)");
	     q.setParameters(idSucursal, nombre, segmentacion, tamano, ciudad, direccion);
	     return (long) q.executeUnique();   
	}
	
	public List<Object[]> darIndiceOcupacion(PersistenceManager pm, long idSucursal){
		Query q = pm.newQuery(SQL, "SELECT * FROM ( SELECT idEstante as id_Elemento, 'ESTANTE' as TIPO, CAPACIDADVOLUMEN / CAPACIDADTOTALVOLUMEN as INDICE_VOLUMEN, CAPACIDADPESO / CAPACIDADTOTALPESO as INDICE_PESO "
				+ "FROM ESTANTE WHERE idsucursal = ? "
				+ "UNION ALL SELECT idBodega as id_Elemento,'BODEGA' AS TIPO,CAPACIDADVOLUMEN / CAPACIDADTOTALVOLUMEN AS INDICE_VOLUMEN, CAPACIDADPESO / CAPACIDADTOTALPESO as INDICE_PESO "
				+ "FROM BODEGA WHERE idsucursal= ?" 
				+")");
		q.setParameters(idSucursal,idSucursal);
		//q = pm.newQuery(SQL, "SELECT * FROM PRODUCTO");
		
		//q.setResultClass(Producto.class);

		return q.executeList();
	}
}
