package negocio;

public interface VOProductoSucursal 
{
	/**
	 * Devuelve el id de la sucursal a la cual pertenece el producto.
	 * @return el id de la sucursal a la cual pertenece el producto.
	 */
	public long getIdSucursal();
	
	/**
	 * Devuelve el codigo de barras del producto .
	 * @return el codigo de barras del producto.
	 */
	public String getCodigoBarras();
	
	/**
	 * Devuelve el precio unitario del producto.
	 * @return
	 */
	public Double getPrecioUnitario();
	
	/**
	 * Devuelve el precio por unidad de medida del producto.
	 * @return el precio por unidad de medida del producto.
	 */
	public Double getPrecioUnidadMedida();
	
	/**
	 * Devuelve el nivel de reorden del producto.
	 * @return el nivel de reorden del producto.
	 */
	public Integer getNivelDeReorden();
	
	/**
	 * Devuelve la cantidad de recompra del producto.
	 * @return la cantidad de recompra del producto.
	 */
	public Integer getCantidadRecompra();
	
	/**
	 * Metodo toString.
	 * @return toString.
	 */
	public String toString();
}
