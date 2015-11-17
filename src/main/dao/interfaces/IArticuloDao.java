/**
 * 
 */
package main.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import main.dao.model.Articulo;

import org.hibernate.exception.DataException;

/**
 * @author ddiaz
 *
 */
public interface IArticuloDao extends IAbstractDao<Articulo, Serializable>{
	
	void saveArticulo(Articulo articulo) throws DataException;
    
    List<Articulo> findAllArticulos() throws DataException;
     
    void deleteArticuloById(int ssn) throws DataException;
     
    Articulo findById(int ssn) throws DataException;
     
    void updateArticulo(Articulo articulo) throws DataException;

	List<Articulo> getByQuery(Map<String, Object> m) throws DataException;

}
