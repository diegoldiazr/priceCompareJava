/**
 * 
 */
package main.dao.interfaces;

import java.util.List;
import java.util.Map;

import org.hibernate.exception.DataException;

import main.dao.model.SubLibreta;

/**
 * @author ddiaz
 *
 */
public interface ISubLibretaDao {
	
	void saveSubLibreta(SubLibreta subLibreta) throws DataException;
    
    List<SubLibreta> findAllSubLibretas() throws DataException;
     
    void deleteSubLibretaById(int ssn) throws DataException;
     
    SubLibreta findById(int ssn) throws DataException;
            
    void updateSubLibreta(SubLibreta subLibreta) throws DataException;

	List<SubLibreta> getByQuery(Map<String, Object> m) throws DataException;

}
