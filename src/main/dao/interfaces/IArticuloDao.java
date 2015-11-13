/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.Articulo;

/**
 * @author ddiaz
 *
 */
public interface IArticuloDao {
	
	void saveArticulo(Articulo articulo) throws DataException;
    
    List<Articulo> findAllArticulos() throws DataException;
     
    void deleteArticuloById(int ssn) throws DataException;
     
    Articulo findById(int ssn) throws DataException;
     
    void updateArticulo(Articulo articulo) throws DataException;

}
