package negocio;

public class Proveedor implements VOProveedor{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private String nit;
	
	private String nombre;

	public Proveedor(String pNit, String pNombre) {
		
		nit = pNit;
		
		nombre = pNombre;
		
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
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
	
	
	
}
