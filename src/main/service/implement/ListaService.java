/**
 * 
 */
package main.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.dao.interfaces.IListaDao;
import main.dao.model.Lista;
import main.service.interfaces.IListaService;
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
public class ListaService implements IListaService {

	 @Autowired
	 private IListaDao listaDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.IListaService#getListaById(java.lang.Integer)
	 */	
	public Lista getListaById(Integer id) throws Exception {
		return listaDao.findById(id);		
	}
	
	public List<Lista> getListas() throws Exception{
		return listaDao.findAllListas();
	}
	
	public ReturnAdapter saveLista(Lista _lista) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//ponemos la fecha de creacion 
		_lista.setCreated(new Date());
		listaDao.saveLista(_lista);
		
		result.setCode(StandardResponse.OK);
		result.setMessage(StandardResponse.MESSAGE_OK_CREADO);
		result.setNumResult(1);
		List<Object> lista = new ArrayList<Object>();
		lista.add(listaDao.findById(_lista.getId()));
		result.setData(lista);
		
		return result;
    }
	
	public ReturnAdapter deleteLista(Integer id) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Lista leido = listaDao.findById(id);
		
		if (leido==null){	
			//si no existe no se puede borrar.
			
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}else{		
			listaDao.deleteListaById(id);			
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ELIMINADO);
			result.setNumResult(0);
		}
		
		return result;
	}
	
	public ReturnAdapter updateLista(Integer id, Lista _lista) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Lista leido = listaDao.findById(id);
		
		if (leido!=null){					
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ACTUALIZADO);
			result.setNumResult(1);			
			
			Lista elementoGuardar = volcarValores(_lista, leido);
			listaDao.updateLista(elementoGuardar);
			
			List<Object> lista = new ArrayList<Object>();
			lista.add(elementoGuardar);
			result.setData(lista);
			
		}else{
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}		
		return result;
    }

	/**
	 * 
	 * se retorna un elemento combinado de ambos.
	 * 
	 * @param pet
	 * @param leido
	 * @return
	 */
	private Lista volcarValores(Lista pet, Lista leido) {
		Lista res = leido;
		 
		if (pet.getDescripcion()!=null)
			res.setDescripcion(pet.getDescripcion());
			
			
		res.setId(leido.getId());
		res.setCreated(leido.getCreated());
		res.setBorrado(pet.isBorrado());
		
		res.setUpdated(new Date());
		return res;
	}

}
