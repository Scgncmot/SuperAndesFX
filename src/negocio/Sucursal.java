package negocio;

public class Sucursal implements VOSucursal{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id;
	
	private String nombre;
	
	private String segmentacion;
	
	private String direccion;
	
	private String tamano;
	
	private String ciudad;

	
	
	
	public Sucursal(long id, String nombre, String segmentacion, String direccion, String tamano, String ciudad) 
	{
		this.id = id;
		this.nombre = nombre;
		this.segmentacion = segmentacion;
		this.direccion = direccion;
		this.tamano = tamano;
		this.ciudad = ciudad;
	}

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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the segmentacion
	 */
	public String getSegmentacion() {
		return segmentacion;
	}

	/**
	 * @param segmentacion the segmentacion to set
	 */
	public void setSegmentacion(String segmentacion) {
		this.segmentacion = segmentacion;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the tamano
	 */
	public String getTamano() {
		return tamano;
	}

	/**
	 * @param tamano the tamano to set
	 */
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", segmentacion=" + segmentacion + ", direccion="
				+ direccion + ", tamano=" + tamano + ", ciudad=" + ciudad + "]";
	}
	
	
	
}
