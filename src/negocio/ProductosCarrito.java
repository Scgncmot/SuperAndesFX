package negocio;

public class ProductosCarrito implements VOProductosCarrito
{
	private long idCarrito /*Id del carro que tiene el producto*/;
	
	private String codigoBarrasProducto /*Codigo de barras del producto que esta en el carrito*/;

	public ProductosCarrito(long idCarrito, String codigoBarrasProducto) 
	{
		this.idCarrito = idCarrito;
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

	public long getIdCarrito() {return idCarrito;}

	public void setIdCarrito(long idCarrito) {this.idCarrito = idCarrito;}

	public String getCodigoBarrasProducto() {return codigoBarrasProducto;}

	public void setCodigoBarrasProducto(String codigoBarrasProducto) {this.codigoBarrasProducto = codigoBarrasProducto;}

	@Override
	public String toString() 
	{
		return "ProductosCarrito [idCarrito=" + idCarrito + ", codigoBarrasProducto=" + codigoBarrasProducto + "]";
	}	
}
