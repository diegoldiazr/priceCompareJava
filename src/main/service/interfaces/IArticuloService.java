/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Articulo;
import main.utils.IOrderingService;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface IArticuloService extends IOrderingService{

	Articulo getArticuloById(Integer id) throws Exception;
	
	List<Articulo> getArticulos() throws Exception;

	ReturnAdapter saveArticulo(Articulo articulo) throws Exception;
	
	ReturnAdapter updateArticulo(Integer id, Articulo articulo) throws Exception;
	
	ReturnAdapter deleteArticulo(Integer id) throws Exception;

}
