/**
 * 
 */
package main.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.dao.interfaces.ITipoDao;
import main.dao.model.Tipo;
import main.service.interfaces.ITipoService;
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
public class TipoService implements ITipoService {

	 @Autowired
	 private ITipoDao tipoDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.ITipoService#getTipoById(java.lang.Integer)
	 */	
	public Tipo getTipoById(Integer id) throws Exception {
		return tipoDao.findById(id);		
	}
	
	public List<Tipo> getTipos() throws Exception{
		return tipoDao.findAllTipos();
	}
	
	public ReturnAdapter saveTipo(Tipo tipo) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//ponemos la fecha de creacion 
		tipo.setCreated(new Date());
		tipoDao.saveTipo(tipo);
		
		result.setCode(StandardResponse.OK);
		result.setMessage(StandardResponse.MESSAGE_OK_CREADO);
		result.setNumResult(1);
		List<Object> lista = new ArrayList<Object>();
		lista.add(tipoDao.findById(tipo.getId()));
		result.setData(lista);
		
		return result;
    }
	
	public ReturnAdapter deleteTipo(Integer id) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Tipo leido = tipoDao.findById(id);
		
		if (leido==null){	
			//si no existe no se puede borrar.
			
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}else{		
			tipoDao.deleteTipoById(id);			
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ELIMINADO);
			result.setNumResult(0);
		}
		
		return result;
	}
	
	public ReturnAdapter updateTipo(Integer id, Tipo tipo) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Tipo leido = tipoDao.findById(id);
		
		if (leido!=null){					
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ACTUALIZADO);
			result.setNumResult(1);			
			
			Tipo elementoGuardar = volcarValores(tipo, leido);
			tipoDao.updateTipo(elementoGuardar);
			
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
	private Tipo volcarValores(Tipo pet, Tipo leido) {
		Tipo res = leido;
		 
		if (pet.getDescripcion()!=null)
			res.setDescripcion(pet.getDescripcion());
			
			
		res.setId(leido.getId());
		res.setCreated(leido.getCreated());
		res.setBorrado(pet.isBorrado());
		
		res.setUpdated(new Date());
		return res;
	}

	/**
	 *  Este metodo asigna el orderBy. Descomentar si se va a requerir.
	 *
	 */
	public void setOrderBy(String orderBy) {
		this.tipoDao.setOrderBy(orderBy);
	}
}
