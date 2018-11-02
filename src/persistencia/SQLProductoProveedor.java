package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoProveedor 
{

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
	public SQLProductoProveedor(PersistenciaSuperAndes pp){

		this.pp = pp;
	}


	public void registrarProductoAProveedor(PersistenceManager pm, String barCode, String nit, String calif, String prec) {

		double precio = Double.parseDouble(prec);

		Query q = pm.newQuery(SQL, "INSERT INTO " + PersistenciaSuperAndes.darTablaProductoProveedor()+ "(NITPROVEEDOR,CODIGOPRODUCTO,PRECIO,CALIFICACIONCALIDAD) values (?, ?, ?, ?)"); 
		q.setParameters(nit,barCode,precio,calif);
		q.executeUnique();
	}
	
	
	public List<String> darProductosProveedor(PersistenceManager pm, String nitStr) {

		Query q = pm.newQuery(SQL, "SELECT CODIGOPRODUCTO FROM " + PersistenciaSuperAndes.darTablaProductoProveedor()+" WHERE NITPROVEEDOR = ?");
		q.setParameters(nitStr);
		return q.executeList();				
	}


	

}
