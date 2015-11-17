/**
 * 
 */
package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.dao.model.Precio;
import main.service.interfaces.IPrecioService;
import main.utils.ReturnAdapter;
import main.utils.StandardResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 
 * Esta entidad gestiona los diversos precios de articulos.
 * 
 * @author ddiaz
 *
 */
@RestController
public class PrecioController {
	
	@Autowired
	private IPrecioService precioService;

	private Logger log = Logger.getLogger(PrecioController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/data/precios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getPrecioById(
			@PathVariable("id") Integer id){
		ReturnAdapter result = new ReturnAdapter();
		try{
			Precio l = precioService.getPrecioById(id);			
			if (l!=null){											
				List lista = new ArrayList();
				lista.add(l);
				result.setData(lista);
			}else{
				result.setCode(StandardResponse.SIN_CONTENIDO);
				result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
				result.setNumResult(0);
			}
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, 
		value="/data/precios",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getPrecios(){
		ReturnAdapter result = new ReturnAdapter();
		try{
			List l = precioService.getPrecios();
			if (l!=null){				
				result.setNumResult(l.size());				
				result.setData(l);
			}else{
				result.setCode(StandardResponse.SIN_CONTENIDO);
				result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
				result.setNumResult(0);
			}
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
	
		
	/**
	 * 
	 * Guardar un elemento.
	 * 
	 * @param precio
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,  
			value="/data/precios",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter save(@RequestBody Precio precio){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = precioService.savePrecio(precio);								
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
	
	
	/**
	 * 
	 * Actualizar un elemento
	 * 
	 * 
	 * @param id
	 * @param precio
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/data/precios/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter update(
			@PathVariable("id") Integer id,
			@RequestBody Precio precio){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = precioService.updatePrecio(id, precio);						
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
	
	/**
	 * 
	 * Borrar un elemento
	 * 
	 * 
	 * @param id 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/data/precios/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter delete(
			@PathVariable("id") Integer id){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = precioService.deletePrecio(id);						
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
}
