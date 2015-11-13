/**
 * 
 */
package main.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import main.dao.interfaces.IComercioDao;
import main.dao.model.Comercio;
import main.service.interfaces.IComercioService;

/**
 * @author ddiaz
 *
 */
@Component
@Transactional
public class ComercioService implements IComercioService {

	 @Autowired
	 private IComercioDao comercioDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.IComercioService#getComercioById(java.lang.Integer)
	 */	
	public Comercio getComercioById(Integer id) throws Exception {
		return comercioDao.findById(id);		
	}
	
	public List<Comercio> getComercios() throws Exception{
		return comercioDao.findAllComercios();
	}
	
	public void saveComercio(Comercio comercio) {
		comercioDao.saveComercio(comercio);
    }
	
	public void deleteComercioBySsn(Integer id) {
		comercioDao.deleteComercioById(id);
	}
	
	public void updateComercio(Comercio comercio){
		comercioDao.updateComercio(comercio);
    }

}
