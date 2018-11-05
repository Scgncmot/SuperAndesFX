package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductosEstante 
{
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLProductosEstante(PersistenciaSuperAndes persistenciaSuperAndes) 
	{		
		this.pp = persistenciaSuperAndes;
	}

	public void quitarNCantidadDeProductos(PersistenceManager pm, String codigoBarras, String cantidad, long sucursalId) {

		int cant = Integer.parseInt(cantidad);

		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosEstante()+" SET CANTIDADPRODUCTO = CANTIDADPRODUCTO - ? WHERE CODIGOBARRAS = ? AND IDSUCURSAL = ?");
		q.setParameters(cant, codigoBarras, sucursalId);
		q.executeUnique();
	}


	public void ponerNCantidadDeProductos(PersistenceManager pm, String codigoBarras, int cantidad, long sucursalId) {


		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosEstante()+" SET CANTIDADPRODUCTO = CANTIDADPRODUCTO + ? WHERE CODIGOBARRAS = ? AND IDSUCURSAL = ?");
		q.setParameters(cantidad, codigoBarras, sucursalId);
		q.executeUnique();
	}




}
