package negocio;

public class Sucursal implements VOSucursal{
	
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------	
	private long id /*Id de la sucursal*/;
	
	private String nombre /*Nombre de la sucursal*/, 
		segmentacion /*Segmentacion de la sucursal*/, 
		direccion /*Direccion de la sucursal*/,
		ciudad /*Ciudad de la sucursal*/;
	
	private Double tamano;
	
	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	public Sucursal(long id, String nombre, String segmentacion, String direccion, String ciudad, Double tamano) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.segmentacion = segmentacion;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.tamano = tamano;
	}

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public long getId() {return id;}

	public void setId(long id) {this.id = id;}

	public String getNombre() {return nombre;}

	public void setNombre(String nombre) {this.nombre = nombre;}

	public String getSegmentacion() {return segmentacion;}

	public void setSegmentacion(String segmentacion) {this.segmentacion = segmentacion;}

	public String getDireccion() {return direccion;}

	public void setDireccion(String direccion) {this.direccion = direccion;}

	public String getCiudad() {return ciudad;}

	public void setCiudad(String ciudad) {this.ciudad = ciudad;}

	public Double getTamano() {return tamano;}

	public void setTamano(Double tamano) {this.tamano = tamano;}

	@Override
	public String toString() 
	{
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", segmentacion=" + segmentacion + ", direccion="
				+ direccion + ", ciudad=" + ciudad + ", tamano=" + tamano + "]";
	}
	
	
	
	
}
