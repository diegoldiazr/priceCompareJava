/**
 * 
 */
package main.dao.interfaces;

import java.util.List;

import org.hibernate.exception.DataException;

import main.dao.model.Libreta;

/**
 * @author ddiaz
 *
 */
public interface ILibretaDao {
	
	void saveLibreta(Libreta libreta) throws DataException;
    
    List<Libreta> findAllLibretas() throws DataException;
     
    void deleteLibretaById(int ssn) throws DataException;
     
    Libreta findById(int ssn) throws DataException;
     
    void updateLibreta(Libreta libreta) throws DataException;

}
