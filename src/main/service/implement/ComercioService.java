/**
 * 
 */
package main.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.dao.interfaces.IComercioDao;
import main.dao.model.Comercio;
import main.service.interfaces.IComercioService;
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
	
	public ReturnAdapter saveComercio(Comercio comercio) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//ponemos la fecha de creacion 
		comercio.setCreated(new Date());
		comercioDao.saveComercio(comercio);
		
		result.setCode(StandardResponse.OK);
		result.setMessage(StandardResponse.MESSAGE_OK_CREADO);
		result.setNumResult(1);
		List<Object> lista = new ArrayList<Object>();
		lista.add(comercioDao.findById(comercio.getId()));
		result.setData(lista);
		
		return result;
    }
	
	public ReturnAdapter deleteComercio(Integer id) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Comercio leido = comercioDao.findById(id);
		
		if (leido==null){	
			//si no existe no se puede borrar.
			
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}else{		
			comercioDao.deleteComercioById(id);			
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ELIMINADO);
			result.setNumResult(0);
		}
		
		return result;
	}
	
	public ReturnAdapter updateComercio(Integer id, Comercio comercio) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Comercio leido = comercioDao.findById(id);
		
		if (leido!=null){					
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ACTUALIZADO);
			result.setNumResult(1);			
			
			Comercio elementoGuardar = volcarValores(comercio, leido);
			comercioDao.updateComercio(elementoGuardar);
			
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
	private Comercio volcarValores(Comercio pet, Comercio leido) {
		Comercio res = leido;
		 
		if (pet.getNombre()!=null)
			res.setNombre(pet.getNombre());
		if (pet.getLinkImagen()!=null)
			res.setLinkImagen(pet.getLinkImagen());
		if (pet.getPuntuacion()!=null)
			res.setPuntuacion(pet.getPuntuacion());		
			
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
		this.comercioDao.setOrderBy(orderBy);
	}

	@Override
	public List<Comercio> getComerciosByQuery(String nombre, String borrado) throws Exception {
		Map<String, Object> m = new TreeMap<String, Object>();

		if (nombre!=null){
			m.put("nombre", nombre.trim().toUpperCase());
		}
		if (borrado!=null){			
			m.put("borrado", borrado.trim().toUpperCase().equals("TRUE"));			
		}
		
		return comercioDao.getByQuery(m);

	}
}
