package negocio;

public class Pague1Lleve2ConDescPromo implements VOPague1Lleve2ConDescPromo{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private String codigoPromo;
	
	private double porcentaje;
	
	
	

	public Pague1Lleve2ConDescPromo(String codigoPromo, double porcentaje) 
	{
		this.codigoPromo = codigoPromo;
		this.porcentaje = porcentaje;
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
	 * @return the porcentaje
	 */
	public double getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pague1Lleve2ConDescPromo [codigoPromo=" + codigoPromo + ", porcentaje=" + porcentaje + "]";
	}

	
	
}
