package negocio;

public interface VOPagueXCantidadLleveY {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return el codigo de la promocion pague x cantidad lleve y cantidad
	 */
	public String getCodigoPromo();
	
	/**
	 * @return la cantidad que paga el cliente
	 */
	public int getCantidadPaga();
	
	/**
	 * @return la cantidad que lleva el cliente
	 */
	public int getCantidadLleva();
	
}
