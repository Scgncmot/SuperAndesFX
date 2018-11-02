package negocio;

public interface VOProducto {
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	
	public String getCodigoBarras();
	
	public String getNombre();
	
	public String getPresentacion();
	
	public int getCantidad();
	
	public String getUnidadMedida();
	
	public String getEspecificacionEmpacado();
	
	
	/**
	 * @return Una cadena de caracteres con la información del producto
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Productos
	 * @param s - El proveedor a comparar
	 * @return true si tienen el mismo codigo de barras 
	 */
	@Override
	public boolean equals (Object s); 
	
}
