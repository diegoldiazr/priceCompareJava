package main.utils;

/**
 * Interfaz para la paginacion para la capa de servicio
 * 
 * @author ddiaz
 * 
 */
public interface IOrderingService {
	
	
	/**
	 * indicar el orden para los resultados
	 * @param orderBy
	 */
	void setOrderBy(String orderBy);
}
