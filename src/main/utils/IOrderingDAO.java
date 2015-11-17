package main.utils;

/**
 * Interfaz para la ordenacion para la capa DAO
 * 
 * @author ddiaz
 *
 */
public interface IOrderingDAO {
	
	void setOrderBy(String orderBy);	

	String getOrderBy();


}
