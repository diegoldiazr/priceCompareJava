/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.ElementoLista;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface IElementoListaService {

	ElementoLista getElementoListaById(Integer id) throws Exception;
	
	List<ElementoLista> getElementoListas() throws Exception;

	ReturnAdapter saveElementoLista(ElementoLista elementoLista) throws Exception;		
	
	ReturnAdapter deleteElementoLista(Integer id) throws Exception;

}
