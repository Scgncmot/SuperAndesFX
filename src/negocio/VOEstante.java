package negocio;

public interface VOEstante {
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * @return el id de la sucursal del estante
	 */
	public long getIdSucursal();
	
	/**
	 * @return el id del estante
	 */
	public long getId();
	
	/**
	 * @return nombre del estante
	 */
	public String getNombre();
	
	/**
	 * @return el id del tipo producto del estante
	 */
	public long getIdTipoProducto();
	
	/**
	 * @return la capacidad actual en volumen
	 */
	public double getCapacidadVolumen();
	
	/**
	 * @return la capacidad maxima en volumen
	 */
	public double getCapacidadTotalVolumen();
	
	/**
	 * @return la capacidad actual en peso
	 */
	public double getCapacidadPeso();
	
	/**
	 * @return la capacidad maxima en peso
	 */
	public double getCapacidadTotalPeso();
	
	/**
	 * @return el nivel de abastecimiento del estante
	 */
	public double getNivelDeAbastecimiento();
	
	/**
	 * @return Una cadena de caracteres con la información del estante
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Estantes
	 * @param b - El estante a comparar
	 * @return true si tienen el mismo identificador 
	 */
	@Override
	public boolean equals (Object b); 
}
