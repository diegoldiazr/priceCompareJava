/**
 * 
 */
package main.service.implement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import main.dao.interfaces.ISubLibretaDao;
import main.dao.model.SubLibreta;
import main.service.interfaces.ISubLibretaService;

/**
 * @author ddiaz
 *
 */
@Component
@Transactional
public class SubLibretaService implements ISubLibretaService {

	 @Autowired
	 private ISubLibretaDao subLibretaDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.ISubLibretaService#getSubLibretaById(java.lang.Integer)
	 */	
	public SubLibreta getSubLibretaById(Integer id) throws Exception {		
		return subLibretaDao.findById(id);		
	}
	
	public List<SubLibreta> getSubLibretas() throws Exception{
		return subLibretaDao.findAllSubLibretas();
	}
	
	public void saveSubLibreta(SubLibreta subLibreta) {
		subLibretaDao.saveSubLibreta(subLibreta);
    }
	
	public void deleteSubLibretaBySsn(Integer id) {
		subLibretaDao.deleteSubLibretaById(id);
	}
	
	public void updateSubLibreta(SubLibreta subLibreta){
		subLibretaDao.updateSubLibreta(subLibreta);
    }

	@Override
	public List<SubLibreta> getByQuery(Integer idLibreta) throws Exception {
		Map<String, Object> m = new TreeMap<String, Object>();		
		m.put("IDLIBRETA", idLibreta);		
		
		return subLibretaDao.getByQuery(m);
		
	}

}
