package negocio;

public class PagueXCantidadLleveYPromo implements VOPagueXCantidadLleveY{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private String codigoPromo;
	
	private int cantidadPaga;
	
	private int cantidadLleva;
	
	

	public PagueXCantidadLleveYPromo(String codigoPromo, int cantidadPaga, int cantidadLleva) 
	{
		this.codigoPromo = codigoPromo;
		this.cantidadPaga = cantidadPaga;
		this.cantidadLleva = cantidadLleva;
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
	 * @return the cantidadPaga
	 */
	public int getCantidadPaga() {
		return cantidadPaga;
	}

	/**
	 * @param cantidadPaga the cantidadPaga to set
	 */
	public void setCantidadPaga(int cantidadPaga) {
		this.cantidadPaga = cantidadPaga;
	}

	/**
	 * @return the cantidadLleva
	 */
	public int getCantidadLleva() {
		return cantidadLleva;
	}

	/**
	 * @param cantidadLleva the cantidadLleva to set
	 */
	public void setCantidadLleva(int cantidadLleva) {
		this.cantidadLleva = cantidadLleva;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagueXCantidadLleveYPromo [codigoPromo=" + codigoPromo + ", cantidadPaga=" + cantidadPaga
				+ ", cantidadLleva=" + cantidadLleva + "]";
	}
	
	
	
}
