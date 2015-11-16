/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.Usuario;
import main.utils.ReturnAdapter;

/**
 * @author ddiaz
 *
 */
public interface IUsuarioService {

	Usuario getUsuarioById(Integer id) throws Exception;
	
	List<Usuario> getUsuarios() throws Exception;

	ReturnAdapter saveUsuario(Usuario usuario) throws Exception;
	
	ReturnAdapter updateUsuario(Integer id, Usuario usuario) throws Exception;
	
	ReturnAdapter deleteUsuario(Integer id) throws Exception;

}
