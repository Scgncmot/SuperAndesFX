package negocio;

import java.sql.Timestamp;

public interface VOPromocion {

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return codigo de la promocion
	 */
	public String getCodigoPromo();
	
	/**
	 * @return fecha de terminacion de la promocion
	 */
	public Timestamp getFechaTerminacion();
	
	/**
	 * @return Una cadena de caracteres con la información de la promocion
	 */
	@Override
	public String toString(); 

	/**
	 * Define la igualdad dos Promociones
	 * @param b - La promocion a comparar
	 * @return true si tienen el mismo identificador 
	 */
	@Override
	public boolean equals (Object b); 

}
