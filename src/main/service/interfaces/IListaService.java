/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Lista;
import main.utils.IOrderingService;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface IListaService extends IOrderingService{

	Lista getListaById(Integer id) throws Exception;
	
	List<Lista> getListas() throws Exception;

	ReturnAdapter saveLista(Lista lista) throws Exception;
	
	ReturnAdapter updateLista(Integer id, Lista lista) throws Exception;
	
	ReturnAdapter deleteLista(Integer id) throws Exception;

	List<Lista> getListasByQuery(String descripcion, String idUsuario, String borrado) throws Exception;

}
