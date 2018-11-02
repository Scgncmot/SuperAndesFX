package negocio;

public interface VOPagueNUnidadesLleveMPromo {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return el codigo de la promocion pague n unidades lleve m unidades
	 */
	public String getCodigoPromo();
	
	/**
	 * @return las unidades que paga el cliente
	 */
	public int getCompraUnidades();
	
	/**
	 * @return las unidades que lleva el cliente
	 */
	public int getLlevaUnidades();
	
}
