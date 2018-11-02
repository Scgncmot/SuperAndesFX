package negocio;

public interface VOProveedor {

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return el nit del proveedor
	 */
	public String getNit();
	
	/**
	 * @return el nombre del proveedor
	 */
	public String getNombre();
	
	/**
	 * @return Una cadena de caracteres con la información del proveedor
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Proveedores
	 * @param s - El proveedor a comparar
	 * @return true si tienen el mismo nit 
	 */
	@Override
	public boolean equals (Object s); 
}
