/**
 * 
 */
package main.service.interfaces;

import java.util.List;

import main.dao.model.SubLibreta;

/**
 * @author ddiaz
 *
 */
public interface ISubLibretaService {

	public SubLibreta getSubLibretaById(Integer id) throws Exception;
	
	public List<SubLibreta> getByQuery(Integer idLibreta) throws Exception;
	
	public List<SubLibreta> getSubLibretas() throws Exception;
}
