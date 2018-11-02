package negocio;

public interface VOSucursal 
{
	
	
	/**
	 * @return el id de la sucursal
	 */
	public long getId();
	
	/**
	 * @return el nombre de la sucursal
	 */
	public String getNombre();
	
	/**
	 * @return la segmentacion de la sucursal
	 */
	public String getSegmentacion();
	
	/**
	 * @return el tamano de la sucursal
	 */
	public Double getTamano();
	
	/**
	 * @return la ciudad de la sucursal
	 */
	public String getCiudad();
	
	/**
	 * @return la direccion de la sucursal
	 */
	public String getDireccion();
	
	/**
	 * @return Una cadena de caracteres con la información de la sucursal
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Sucursales
	 * @param s - La sucursal a comparar
	 * @return true si tienen el mismo identificador 
	 */
	@Override
	public boolean equals (Object s); 
}
