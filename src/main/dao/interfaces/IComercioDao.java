/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.Comercio;

/**
 * @author ddiaz
 *
 */
public interface IComercioDao {
	
	void saveComercio(Comercio comercio) throws DataException;
    
    List<Comercio> findAllComercios() throws DataException;
     
    void deleteComercioById(int ssn) throws DataException;
     
    Comercio findById(int ssn) throws DataException;
     
    void updateComercio(Comercio comercio) throws DataException;

}
