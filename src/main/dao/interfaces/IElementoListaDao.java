/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.ElementoLista;

/**
 * @author ddiaz
 *
 */
public interface IElementoListaDao {
	
	void saveElementoLista(ElementoLista elementoLista) throws DataException;
    
    List<ElementoLista> findAllElementoListas() throws DataException;
     
    void deleteElementoListaById(int ssn) throws DataException;
     
    ElementoLista findById(int ssn) throws DataException;
     
    void updateElementoLista(ElementoLista elementoLista) throws DataException;

}
