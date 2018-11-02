package negocio;

import java.sql.Timestamp;

public interface VOLlegadaPedido {

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return id del pedido
	 */
	public long getId();
	
	/**
	 * @return id de la sucursal del pedido
	 */
	public long getIdSucursal();
	
	/**
	 * @return fecha de entrega del pedido
	 */
	public Timestamp getFechaEntrega();
	
	/**
	 * @return cantidad de productos del pedido
	 */
	public int getCantidadProductos();
	
	/**
	 * @return calidad de productos del pedido
	 */
	public String getCalidadProductos();
	
	/**
	 * @return calificacion del pedido
	 */
	public String getCalificacion();
	
	/**
	 * @return id del pedido original
	 */
	public long getIdPedido();
	
	/**
	 * @return Una cadena de caracteres con la información de la llegada del pedido
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos llegadas de pedido
	 * @param s - La llegada de pedido a comparar
	 * @return true si tienen el mismo identificador 
	 */
	@Override
	public boolean equals (Object s); 
}
