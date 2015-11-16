/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.Usuario;

/**
 * @author ddiaz
 *
 */
public interface IUsuarioDao {
	
	void saveUsuario(Usuario usuario) throws DataException;
    
    List<Usuario> findAllUsuarios() throws DataException;
     
    void deleteUsuarioById(int ssn) throws DataException;
     
    Usuario findById(int ssn) throws DataException;
     
    void updateUsuario(Usuario usuario) throws DataException;

}
