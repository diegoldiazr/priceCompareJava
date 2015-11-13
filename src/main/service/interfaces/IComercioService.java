/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Comercio;

/**
 * @author ddiaz
 *
 */
public interface IComercioService {

	Comercio getComercioById(Integer id) throws Exception;

	List getComercios() throws Exception;

}
