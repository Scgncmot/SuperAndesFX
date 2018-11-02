package negocio;

public class PaqueteDeProductos implements VOPaqueteDeProductos{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id;
	
	private long idProducto;
	
	private double precioEnConjunto;
	
	private long idPromocion;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the idProducto
	 */
	public long getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * @return the precioEnConjunto
	 */
	public double getPrecioEnConjunto() {
		return precioEnConjunto;
	}

	/**
	 * @param precioEnConjunto the precioEnConjunto to set
	 */
	public void setPrecioEnConjunto(double precioEnConjunto) {
		this.precioEnConjunto = precioEnConjunto;
	}

	/**
	 * @return the idPromocion
	 */
	public long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * @param idPromocion the idPromocion to set
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}
	
	
}
