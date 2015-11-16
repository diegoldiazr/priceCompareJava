/**
 * 
 */
package main.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.dao.interfaces.IElementoListaDao;
import main.dao.model.ElementoLista;
import main.service.interfaces.IElementoListaService;
import main.utils.ReturnAdapter;
import main.utils.StandardResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ddiaz
 *
 */
@Component
@Transactional
public class ElementoListaService implements IElementoListaService {

	 @Autowired
	 private IElementoListaDao elementoListaDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.IElementoListaService#getElementoListaById(java.lang.Integer)
	 */	
	public ElementoLista getElementoListaById(Integer id) throws Exception {
		return elementoListaDao.findById(id);		
	}
	
	public List<ElementoLista> getElementoListas() throws Exception{
		return elementoListaDao.findAllElementoListas();
	}
	
	public ReturnAdapter saveElementoLista(ElementoLista elementoLista) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//ponemos la fecha de creacion 
		elementoLista.setCreated(new Date());
		elementoListaDao.saveElementoLista(elementoLista);
		
		result.setCode(StandardResponse.OK);
		result.setMessage(StandardResponse.MESSAGE_OK_CREADO);
		result.setNumResult(1);
		List<Object> lista = new ArrayList<Object>();
		lista.add(elementoListaDao.findById(elementoLista.getId()));
		result.setData(lista);
		
		return result;
    }
	
	public ReturnAdapter deleteElementoLista(Integer id) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		ElementoLista leido = elementoListaDao.findById(id);
		
		if (leido==null){	
			//si no existe no se puede borrar.
			
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}else{		
			elementoListaDao.deleteElementoListaById(id);			
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ELIMINADO);
			result.setNumResult(0);
		}
		
		return result;
	}
	
	

}
