/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.Tipo;

/**
 * @author ddiaz
 *
 */
public interface ITipoDao {
	
	void saveTipo(Tipo tipo) throws DataException;
    
    List<Tipo> findAllTipos() throws DataException;
     
    void deleteTipoById(int ssn) throws DataException;
     
    Tipo findById(int ssn) throws DataException;
     
    void updateTipo(Tipo tipo) throws DataException;

}
