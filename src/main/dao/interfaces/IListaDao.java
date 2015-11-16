/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.Lista;

/**
 * @author ddiaz
 *
 */
public interface IListaDao {
	
	void saveLista(Lista lista) throws DataException;
    
    List<Lista> findAllListas() throws DataException;
     
    void deleteListaById(int ssn) throws DataException;
     
    Lista findById(int ssn) throws DataException;
     
    void updateLista(Lista lista) throws DataException;

}
