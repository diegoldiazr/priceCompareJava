/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.Precio;

/**
 * @author ddiaz
 *
 */
public interface IPrecioDao {
	
	void savePrecio(Precio precio) throws DataException;
    
    List<Precio> findAllPrecios() throws DataException;
     
    void deletePrecioById(int ssn) throws DataException;
     
    Precio findById(int ssn) throws DataException;
     
    void updatePrecio(Precio precio) throws DataException;

}
