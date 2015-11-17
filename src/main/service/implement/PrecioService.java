/**
 * 
 */
package main.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.dao.interfaces.IPrecioDao;
import main.dao.model.Precio;
import main.service.interfaces.IPrecioService;
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
public class PrecioService implements IPrecioService {

	 @Autowired
	 private IPrecioDao precioDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.IPrecioService#getPrecioById(java.lang.Integer)
	 */	
	public Precio getPrecioById(Integer id) throws Exception {
		return precioDao.findById(id);		
	}
	
	public List<Precio> getPrecios() throws Exception{
		return precioDao.findAllPrecios();
	}
	
	public ReturnAdapter savePrecio(Precio precio) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//ponemos la fecha de creacion 
		precio.setCreated(new Date());
		precioDao.savePrecio(precio);
		
		result.setCode(StandardResponse.OK);
		result.setMessage(StandardResponse.MESSAGE_OK_CREADO);
		result.setNumResult(1);
		List<Object> lista = new ArrayList<Object>();
		lista.add(precioDao.findById(precio.getId()));
		result.setData(lista);
		
		return result;
    }
	
	public ReturnAdapter deletePrecio(Integer id) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Precio leido = precioDao.findById(id);
		
		if (leido==null){	
			//si no existe no se puede borrar.
			
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}else{		
			precioDao.deletePrecioById(id);			
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ELIMINADO);
			result.setNumResult(0);
		}
		
		return result;
	}
	
	public ReturnAdapter updatePrecio(Integer id, Precio precio) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Precio leido = precioDao.findById(id);
		
		if (leido!=null){					
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ACTUALIZADO);
			result.setNumResult(1);			
			
			Precio elementoGuardar = volcarValores(precio, leido);
			precioDao.updatePrecio(elementoGuardar);
			
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
	private Precio volcarValores(Precio pet, Precio leido) {
		Precio res = leido;

		res.setPrecio(pet.getPrecio());
			
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
		this.precioDao.setOrderBy(orderBy);
	}

	@Override
	public List<Precio> getPreciosByQuery(String idComercio, String borrado,
			String idArticulo) throws Exception {
		Map<String, Object> m = new TreeMap<String, Object>();

		if (idComercio!=null){
			m.put("idComercio", new Integer(idComercio).intValue());
		}
		if (borrado!=null){			
			m.put("borrado", borrado.trim().toUpperCase().equals("TRUE"));			
		}
		if (idArticulo!=null){			
			m.put("idArticulo", new Integer(idArticulo).intValue());			
		}
		
		return precioDao.getByQuery(m);
	}
}
