package negocio;

public class IndiceOcupacion {
	
	private long ID_ELEMENTO;
	
	private String TIPO;
	
	private double INDICE_VOLUMEN;
	
	private double INDICE_PESO;

	/**
	 * @return the idElemento
	 */
	public long getIdElemento() {
		return ID_ELEMENTO;
	}

	/**
	 * @param idElemento the idElemento to set
	 */
	public void setIdElemento(long idElemento) {
		this.ID_ELEMENTO = idElemento;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return TIPO;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.TIPO = tipo;
	}

	/**
	 * @return the indiceVolumen
	 */
	public double getIndiceVolumen() {
		return INDICE_VOLUMEN;
	}

	/**
	 * @param indiceVolumen the indiceVolumen to set
	 */
	public void setIndiceVolumen(double indiceVolumen) {
		this.INDICE_VOLUMEN = indiceVolumen;
	}

	/**
	 * @return the indicePeso
	 */
	public double getIndicePeso() {
		return INDICE_PESO;
	}

	/**
	 * @param indicePeso the indicePeso to set
	 */
	public void setIndicePeso(double indicePeso) {
		this.INDICE_PESO = indicePeso;
	}
	
	public String toString()
	{
		return ID_ELEMENTO+" | "+TIPO+" | "+INDICE_VOLUMEN+" | "+INDICE_PESO;
	}
	
}
