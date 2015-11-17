/**
 * 
 */
package main.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import main.dao.model.Lista;

import org.hibernate.exception.DataException;

/**
 * @author ddiaz
 *
 */
public interface IListaDao extends IAbstractDao<Lista, Serializable>{
	
	void saveLista(Lista lista) throws DataException;
    
    List<Lista> findAllListas() throws DataException;
     
    void deleteListaById(int ssn) throws DataException;
     
    Lista findById(int ssn) throws DataException;
     
    void updateLista(Lista lista) throws DataException;

}
