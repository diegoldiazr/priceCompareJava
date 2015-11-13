/**
 * 
 */
package main.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.dao.interfaces.IArticuloDao;
import main.dao.model.Articulo;
import main.service.interfaces.IArticuloService;
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
public class ArticuloService implements IArticuloService {

	 @Autowired
	 private IArticuloDao articuloDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.IArticuloService#getArticuloById(java.lang.Integer)
	 */	
	public Articulo getArticuloById(Integer id) throws Exception {
		return articuloDao.findById(id);		
	}
	
	public List<Articulo> getArticulos() throws Exception{
		return articuloDao.findAllArticulos();
	}
	
	public ReturnAdapter saveArticulo(Articulo articulo) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//ponemos la fecha de creacion 
		articulo.setCreated(new Date());
		articuloDao.saveArticulo(articulo);
		
		result.setCode(StandardResponse.OK);
		result.setMessage(StandardResponse.MESSAGE_OK_CREADO);
		result.setNumResult(1);
		List<Object> lista = new ArrayList<Object>();
		lista.add(articuloDao.findById(articulo.getId()));
		result.setData(lista);
		
		return result;
    }
	
	public ReturnAdapter deleteArticulo(Integer id) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Articulo leido = articuloDao.findById(id);
		
		if (leido==null){	
			//si no existe no se puede borrar.
			
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}else{		
			articuloDao.deleteArticuloById(id);			
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ELIMINADO);
			result.setNumResult(0);
		}
		
		return result;
	}
	
	public ReturnAdapter updateArticulo(Integer id, Articulo articulo) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Articulo leido = articuloDao.findById(id);
		
		if (leido!=null){					
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ACTUALIZADO);
			result.setNumResult(1);			
			
			Articulo elementoGuardar = volcarValores(articulo, leido);
			articuloDao.updateArticulo(elementoGuardar);
			
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
	private Articulo volcarValores(Articulo pet, Articulo leido) {
		Articulo res = leido;
		 
		if (pet.getNombre()!=null)
			res.setNombre(pet.getNombre());
		if (pet.getLinkImagen()!=null)
			res.setLinkImagen(pet.getLinkImagen());
		if (pet.getTipo()!=null)
			res.setTipo(pet.getTipo());
		
		
		res.setId(leido.getId());
		res.setCreated(leido.getCreated());
		res.setBorrado(pet.isBorrado());
		
		res.setUpdated(new Date());
		return res;
	}

}
