/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Precio;
import main.utils.IOrderingService;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface IPrecioService extends IOrderingService{

	Precio getPrecioById(Integer id) throws Exception;
	
	List<Precio> getPrecios() throws Exception;

	ReturnAdapter savePrecio(Precio precio) throws Exception;
	
	ReturnAdapter updatePrecio(Integer id, Precio precio) throws Exception;
	
	ReturnAdapter deletePrecio(Integer id) throws Exception;

	List<Precio> getPreciosByQuery(String idComercio, String borrado, String idArticulo) throws Exception;

}
