/**
 * 
 */
package main.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import main.dao.model.Comercio;

import org.hibernate.exception.DataException;

/**
 * @author ddiaz
 *
 */
public interface IComercioDao extends IAbstractDao<Comercio, Serializable>{
	
	void saveComercio(Comercio comercio) throws DataException;
    
    List<Comercio> findAllComercios() throws DataException;
     
    void deleteComercioById(int ssn) throws DataException;
     
    Comercio findById(int ssn) throws DataException;
     
    void updateComercio(Comercio comercio) throws DataException;

    List<Comercio>  getByQuery(Map<String, Object> m) throws DataException;

}
