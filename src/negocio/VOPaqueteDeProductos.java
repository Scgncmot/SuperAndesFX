package negocio;

public interface VOPaqueteDeProductos {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return el id de la promocion paquete de productos
	 */
	public long getId();
	
	/**
	 * @return id del otro producto
	 */
	public long getIdProducto();
	
	/**
	 * @return precio en conjunto de los productos
	 */
	public double getPrecioEnConjunto();
	
	/**
	 * @return id de la promocion generica
	 */
	public long getIdPromocion();
}
