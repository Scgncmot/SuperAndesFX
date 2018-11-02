package negocio;

public class PagueNUnidadesLleveMPromo implements VOPagueNUnidadesLleveMPromo{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private String codigoPromo;
	
	private int compraUnidades;
	
	private int llevaUnidades;
	
	
	public PagueNUnidadesLleveMPromo(String codigoPromo, int compraUnidades, int llevaUnidades) 
	{
		this.codigoPromo = codigoPromo;
		this.compraUnidades = compraUnidades;
		this.llevaUnidades = llevaUnidades;
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
	 * @return the compraUnidades
	 */
	public int getCompraUnidades() {
		return compraUnidades;
	}

	/**
	 * @param compraUnidades the compraUnidades to set
	 */
	public void setCompraUnidades(int compraUnidades) {
		this.compraUnidades = compraUnidades;
	}

	/**
	 * @return the llevaUnidades
	 */
	public int getLlevaUnidades() {
		return llevaUnidades;
	}

	/**
	 * @param llevaUnidades the llevaUnidades to set
	 */
	public void setLlevaUnidades(int llevaUnidades) {
		this.llevaUnidades = llevaUnidades;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagueNUnidadesLleveMPromo [codigoPromo=" + codigoPromo + ", compraUnidades=" + compraUnidades
				+ ", llevaUnidades=" + llevaUnidades + "]";
	}

	
	
}
