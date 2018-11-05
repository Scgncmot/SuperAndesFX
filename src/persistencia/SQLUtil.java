package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class SQLUtil {
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
	public SQLUtil (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqSuperAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarSuperAndes (PersistenceManager pm)
	{
        Query tabla1 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdministrador());          
        Query tabla2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega()); 
        Query tabla3 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito());          
        Query tabla4= pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCategoria()); 
        Query tabla5 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente()); 
        Query tabla6 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDescPorcentajePromo());  
        Query tabla7 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante());       
        Query tabla9 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLlegadaPedido());          
        Query tabla10 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPague1Lleve2ConDescPromo());          
        Query tabla11 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPagueNUnidadesLleveMPromo());          
        Query tabla12 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPagueXCantidadLleveYPromo());          
        Query tabla13 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPaqueteDeProductosPromo());          
        Query tabla14 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPedido());          
        Query tabla15 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonaJuridica());          
        Query tabla16 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto());          
        Query tabla17 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoPedido());          
        Query tabla18 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoPromocion());          
        Query tabla19 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoProveedor());          
        Query tabla20 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosCarrito());          
        Query tabla21 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoSucursal());   
        Query tabla22 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion());   
        Query tabla23 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor());   
        Query tabla24 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal());   
        Query tabla25 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuarioSucursal());   
        Query tabla26 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVenta());   
        Query tabla27 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVentaProducto());  
        Query secuencia = pm.newQuery(SQL , "DELETE");

        long long4 = (long) tabla4.executeUnique (), long1 = (long) tabla1.executeUnique (), long2 = (long) tabla2.executeUnique (), long3 = (long) tabla3.executeUnique (), 
        		 long5 = (long) tabla5.executeUnique (), long6 = (long) tabla6.executeUnique ()
        				, long7 = (long) tabla7.executeUnique (), long9 = (long) tabla9.executeUnique ()
        						, long10 = (long) tabla10.executeUnique (), long11 = (long) tabla11.executeUnique (), long12 = (long) tabla12.executeUnique ()
        								, long13 = (long) tabla13.executeUnique (), long14 = (long) tabla14.executeUnique (), long15 = (long) tabla15.executeUnique ()
        										, long16 = (long) tabla16.executeUnique (), long17 = (long) tabla17.executeUnique (), long18 = (long) tabla18.executeUnique ()
        												, long19 = (long) tabla19.executeUnique (), long20 = (long) tabla20.executeUnique (), long21 = (long) tabla21.executeUnique ()
        														, long22 = (long) tabla22.executeUnique (), long23 = (long) tabla23.executeUnique (), long24 = (long) tabla24.executeUnique ()
        																, long25 = (long) tabla25.executeUnique (), long26 = (long) tabla26.executeUnique (), long27= (long) tabla27.executeUnique ();

        return new long[] {long1, long2, long3, long4, long5, long6, long7, long9, long10, long11, long12, long13, 
        		long14, long15, long17, long18, long19, long20, long21, long22, long23, long24, long25, long26, long27};
	}
}
