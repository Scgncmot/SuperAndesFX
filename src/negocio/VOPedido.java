package negocio;

import java.sql.Timestamp;

public interface VOPedido {
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return id del pedido
	 */
	public long getId();
	
	/**
	 * @return fecha de entrega del pedido
	 */
	public Timestamp getFechaEntrega();
	
	/**
	 * @return precio total del pedido
	 */
	public double getPrecioTotal();
	
	/**
	 * @return estado de la orden del pedido
	 */
	public String getEstadoOrden();
	
	/**
	 * @return nit del proveedor del pedido
	 */
	public String getNitProveedor();
	
	/**
	 * @return Una cadena de caracteres con la información del pedido
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos pedidos
	 * @param b - El pedido a comparar
	 * @return true si tienen el mismo identificador 
	 */
	@Override
	public boolean equals (Object b); 

}
