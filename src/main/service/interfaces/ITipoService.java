/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Tipo;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface ITipoService {

	Tipo getTipoById(Integer id) throws Exception;
	
	List<Tipo> getTipos() throws Exception;

	ReturnAdapter saveTipo(Tipo tipo) throws Exception;
	
	ReturnAdapter updateTipo(Integer id, Tipo tipo) throws Exception;
	
	ReturnAdapter deleteTipo(Integer id) throws Exception;

}
