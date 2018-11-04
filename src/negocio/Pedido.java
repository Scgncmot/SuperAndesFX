package negocio;

import java.sql.Timestamp;
import java.util.Date;

import javax.jdo.PersistenceManager;

public class Pedido implements VOPedido{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id, idSucursal;
	
	
	private Date fechaEntrega;
	
	private double precioTotal;
	
	private String estadoOrden;
	
	private String nitProveedor;	
	
	

	public Pedido(long id, long idSucursal, Date fechaEntrega, double precioTotal, String nitProveedor) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.fechaEntrega = fechaEntrega;
		this.precioTotal = precioTotal;
		this.nitProveedor = nitProveedor;
	}

	public Pedido(long id, long idSucursal, Date fechaEntrega, double precioTotal, String estadoOrden,
			String nitProveedor) 
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.fechaEntrega = fechaEntrega;
		this.precioTotal = precioTotal;
		this.estadoOrden = estadoOrden;
		this.nitProveedor = nitProveedor;
	}

	public long getId() {return id;}	
	
	public void setId(long id) {this.id = id;}

	public long getIdSucursal() {return idSucursal;}

	public void setIdSucursal(long idSucursal) {this.idSucursal = idSucursal;}

	public Date getFechaEntrega() {return fechaEntrega;}

	public void setFechaEntrega(Date fechaEntrega) {this.fechaEntrega = fechaEntrega;}

	public double getPrecioTotal() {return precioTotal;}

	public void setPrecioTotal(double precioTotal) {this.precioTotal = precioTotal;}

	public String getEstadoOrden() {return estadoOrden;}

	public void setEstadoOrden(String estadoOrden) {this.estadoOrden = estadoOrden;}

	public String getNitProveedor() {return nitProveedor;}

	public void setNitProveedor(String nitProveedor) {this.nitProveedor = nitProveedor;}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idSucursal=" + idSucursal + ", fechaEntrega=" + fechaEntrega + ", precioTotal="
				+ precioTotal + ", estadoOrden=" + estadoOrden + ", nitProveedor=" + nitProveedor + "]";
	}

	
}
