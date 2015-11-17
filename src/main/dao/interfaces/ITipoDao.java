/**
 * 
 */
package main.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import main.dao.model.Tipo;

import org.hibernate.exception.DataException;

/**
 * @author ddiaz
 *
 */
public interface ITipoDao extends IAbstractDao<Tipo, Serializable>{
	
	void saveTipo(Tipo tipo) throws DataException;
    
    List<Tipo> findAllTipos() throws DataException;
     
    void deleteTipoById(int ssn) throws DataException;
     
    Tipo findById(int ssn) throws DataException;
     
    void updateTipo(Tipo tipo) throws DataException;

}
