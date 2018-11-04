package negocio;

public class Carrito implements VOCarrito
{
	private long id /*Identificador del carrito*/;
	
	private String tipoDocumentoCliente /*Tipo de documneto del cliente al que pertenece el carrito*/, 
		numDocumentoCliente /*Numero de documento del cliente al que pertenece el carrito*/;

	public Carrito(long id, String tipoDocumentoCliente, String numDocumentoCliente) 
	{
		this.id = id;
		this.tipoDocumentoCliente = tipoDocumentoCliente;
		this.numDocumentoCliente = numDocumentoCliente;
	}

	public long getId() {return id;}

	public void setId(long id) {this.id = id;}

	public String getTipoDocumentoCliente() {return tipoDocumentoCliente;}

	public void setTipoDocumentoCliente(String tipoDocumentoCliente) {this.tipoDocumentoCliente = tipoDocumentoCliente;}

	public String getNumDocumentoCliente() {return numDocumentoCliente;}

	public void setNumDocumentoCliente(String numDocumentoCliente) {this.numDocumentoCliente = numDocumentoCliente;}

	@Override
	public String toString() 
	{
		return "Carrito [id=" + id + ", tipoDocumentoCliente=" + tipoDocumentoCliente + ", numDocumentoCliente="
				+ numDocumentoCliente + "]";
	}	
}
