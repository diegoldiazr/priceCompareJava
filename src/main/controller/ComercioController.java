/**
 * 
 */
package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.dao.model.Comercio;
import main.service.interfaces.IComercioService;
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
 * Esta entidad gestiona los diversos establecimientos.
 * 
 * @author ddiaz
 *
 */
@RestController
@RequestMapping("/data")
public class ComercioController {
	
	@Autowired
	private IComercioService comercioService;

	private Logger log = Logger.getLogger(ComercioController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/comercios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getComercioById(
			@PathVariable("id") Integer id){
		ReturnAdapter result = new ReturnAdapter();
		try{
			Comercio l = comercioService.getComercioById(id);			
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
	@RequestMapping(method = RequestMethod.GET, value = "/comercios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getComercios(){
		ReturnAdapter result = new ReturnAdapter();
		try{
			List l = comercioService.getComercios();
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
	 * @param comercio
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/comercios", 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter save(@RequestBody Comercio comercio){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = comercioService.saveComercio(comercio);								
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
	 * @param comercio
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/comercios/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter update(
			@PathVariable("id") Integer id,
			@RequestBody Comercio comercio){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = comercioService.updateComercio(id, comercio);						
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
	@RequestMapping(method = RequestMethod.DELETE, value = "/comercios/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter delete(
			@PathVariable("id") Integer id){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = comercioService.deleteComercio(id);						
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
}
