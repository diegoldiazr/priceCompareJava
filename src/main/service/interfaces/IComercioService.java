/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Comercio;
import main.utils.IOrderingService;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface IComercioService extends IOrderingService{

	Comercio getComercioById(Integer id) throws Exception;
	
	List<Comercio> getComercios() throws Exception;

	ReturnAdapter saveComercio(Comercio comercio) throws Exception;
	
	ReturnAdapter updateComercio(Integer id, Comercio comercio) throws Exception;
	
	ReturnAdapter deleteComercio(Integer id) throws Exception;

	List<Comercio> getComerciosByQuery(String nombre, String borrado) throws Exception;	

}
