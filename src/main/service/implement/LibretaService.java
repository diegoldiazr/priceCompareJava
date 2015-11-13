/**
 * 
 */
package main.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import main.dao.interfaces.ILibretaDao;
import main.dao.model.Libreta;
import main.service.interfaces.ILibretaService;

/**
 * @author ddiaz
 *
 */
@Component
@Transactional
public class LibretaService implements ILibretaService {

	 @Autowired
	 private ILibretaDao libretaDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.ILibretaService#getLibretaById(java.lang.Integer)
	 */	
	public Libreta getLibretaById(Integer id) throws Exception {
		return libretaDao.findById(id);		
	}
	
	public List<Libreta> getLibretas() throws Exception{
		return libretaDao.findAllLibretas();
	}
	
	public void saveLibreta(Libreta libreta) {
		libretaDao.saveLibreta(libreta);
    }
	
	public void deleteLibretaBySsn(Integer id) {
		libretaDao.deleteLibretaById(id);
	}
	
	public void updateLibreta(Libreta libreta){
		libretaDao.updateLibreta(libreta);
    }

}
