package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProducto {
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
	public SQLProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarProducto(PersistenceManager pm, String codigosBarras, String nombres, String presentaciones, String marcas, int cantidades, String unidadesMedida, String especificacionesEmpacado, String categorias) {
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto()+"(CODIGODEBARRAS,NOMBRE,PRESENTACION,MARCA,CANTIDAD,UNIDADDEMEDIDA,ESPECIFICACIONEMPACADO,IDCATEGORIA) values (?,?,?,?,?,?,?,?)");
		q.setParameters(codigosBarras,nombres,presentaciones,marcas,cantidades,unidadesMedida,especificacionesEmpacado,categorias);
		return (long) q.executeUnique();
	}

	public String darProductoPorCodigo(PersistenceManager pm, String string) {
		
		Query q = pm.newQuery(SQL, "SELECT NOMBRE FROM " + pp.darTablaProducto()+" WHERE CODIGODEBARRAS = ?");
		q.setParameters(string);
		return (String) q.executeUnique();				
		
	}

	public Object[] darProductoPorNombre(PersistenceManager pm, String nombreProducto) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto()+" WHERE NOMBRE = ?");
		q.setParameters(nombreProducto);
		return (Object[]) q.executeUnique();
	}
}
