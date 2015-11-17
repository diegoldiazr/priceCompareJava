/**
 * 
 */
package main.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import main.dao.model.Usuario;

import org.hibernate.exception.DataException;

/**
 * @author ddiaz
 *
 */
public interface IUsuarioDao extends IAbstractDao<Usuario, Serializable>{
	
	void saveUsuario(Usuario usuario) throws DataException;
    
    List<Usuario> findAllUsuarios() throws DataException;
     
    void deleteUsuarioById(int ssn) throws DataException;
     
    Usuario findById(int ssn) throws DataException;
     
    void updateUsuario(Usuario usuario) throws DataException;

}
