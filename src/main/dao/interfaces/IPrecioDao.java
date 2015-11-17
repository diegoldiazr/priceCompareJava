/**
 * 
 */
package main.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import main.dao.model.Precio;

import org.hibernate.exception.DataException;

/**
 * @author ddiaz
 *
 */
public interface IPrecioDao extends IAbstractDao<Precio, Serializable>{
	
	void savePrecio(Precio precio) throws DataException;
    
    List<Precio> findAllPrecios() throws DataException;
     
    void deletePrecioById(int ssn) throws DataException;
     
    Precio findById(int ssn) throws DataException;
     
    void updatePrecio(Precio precio) throws DataException;

	List<Precio> getByQuery(Map<String, Object> m) throws DataException;

}
