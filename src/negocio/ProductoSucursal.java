package negocio;

public class ProductoSucursal implements VOProductoSucursal
{
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------
	private long idSucursal /*Id de la sucursal a la que pertenece el producto*/;
	
	private String codigoBarras /*Codigo de barras del producto*/;
	
	private Double precioUnitario /*Precio unitario del producto*/, 
		precioUnidadMedida /*Precio por unidad de medida del producto*/;
	
	private Integer nivelDeReorden /*Nivel de reorden del producto*/, 
		cantidadRecompra /*Cantidad de recompra del producto*/;

	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	public ProductoSucursal() 
	{
		this.idSucursal = 0;
		this.codigoBarras = "";
		this.precioUnitario = 0.0;
		this.precioUnidadMedida = 0.0;
		this.nivelDeReorden = 0;
		this.cantidadRecompra = 0;
	}
	
	public ProductoSucursal(long idSucursal, String codigoBarras, Double precioUnitario, Double precioUnidadMedida,
			Integer nivelDeReorden, Integer cantidadRecompra) 
	{
		this.idSucursal = idSucursal;
		this.codigoBarras = codigoBarras;
		this.precioUnitario = precioUnitario;
		this.precioUnidadMedida = precioUnidadMedida;
		this.nivelDeReorden = nivelDeReorden;
		this.cantidadRecompra = cantidadRecompra;
	}

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public long getIdSucursal() {return idSucursal;}

	public void setIdSucursal(long idSucursal) {this.idSucursal = idSucursal;}

	public String getCodigoBarras() {return codigoBarras;}

	public void setCodigoBarras(String codigoBarras) {this.codigoBarras = codigoBarras;}

	public Double getPrecioUnitario() {return precioUnitario;}

	public void setPrecioUnitario(Double precioUnitario) {this.precioUnitario = precioUnitario;}

	public Double getPrecioUnidadMedida() {return precioUnidadMedida;}

	public void setPrecioUnidadMedida(Double precioUnidadMedida) {this.precioUnidadMedida = precioUnidadMedida;}

	public Integer getNivelDeReorden() {return nivelDeReorden;}

	public void setNivelDeReorden(Integer nivelDeReorden) {this.nivelDeReorden = nivelDeReorden;}

	public Integer getCantidadRecompra() {return cantidadRecompra;}

	public void setCantidadRecompra(Integer cantidadRecompra) {this.cantidadRecompra = cantidadRecompra;}

	@Override
	public String toString() 
	{
		return "ProductoSucursal [idSucursal=" + idSucursal + ", codigoBarras=" + codigoBarras + ", precioUnitario="
				+ precioUnitario + ", precioUnidadMedida=" + precioUnidadMedida + ", nivelDeReorden=" + nivelDeReorden
				+ ", cantidadRecompra=" + cantidadRecompra + "]";
	}
}
