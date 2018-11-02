package negocio;

import java.sql.Timestamp;

public class Promocion implements VOPromocion{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private String codigoPromo;
	
	private Timestamp fechaTerminacion;

	
	
	public Promocion(String codigoPromo, Timestamp fechaTerminacion) 
	{
		this.codigoPromo = codigoPromo;
		this.fechaTerminacion = fechaTerminacion;
	}

	/**
	 * @return the codigoPromo
	 */
	public String getCodigoPromo() {
		return codigoPromo;
	}

	/**
	 * @param codigoPromo the codigoPromo to set
	 */
	public void setCodigoPromo(String codigoPromo) {
		this.codigoPromo = codigoPromo;
	}

	/**
	 * @return the fechaTerminacion
	 */
	public Timestamp getFechaTerminacion() {
		return fechaTerminacion;
	}

	/**
	 * @param fechaTerminacion the fechaTerminacion to set
	 */
	public void setFechaTerminacion(Timestamp fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Promocion [codigoPromo=" + codigoPromo + ", fechaTerminacion=" + fechaTerminacion + "]";
	}
	
	

}
