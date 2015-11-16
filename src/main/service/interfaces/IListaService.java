/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Lista;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface IListaService {

	Lista getListaById(Integer id) throws Exception;
	
	List<Lista> getListas() throws Exception;

	ReturnAdapter saveLista(Lista lista) throws Exception;
	
	ReturnAdapter updateLista(Integer id, Lista lista) throws Exception;
	
	ReturnAdapter deleteLista(Integer id) throws Exception;

}
