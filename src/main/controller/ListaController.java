/**
 * 
 */
package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.dao.model.Lista;
import main.service.interfaces.IListaService;
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
 * Esta entidad gestiona los diversos listas de articulos.
 * 
 * @author ddiaz
 *
 */
@RestController
@RequestMapping("/data/listas")
public class ListaController {
	
	@Autowired
	private IListaService listaService;

	private Logger log = Logger.getLogger(ListaController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getListaById(
			@PathVariable("id") Integer id){
		ReturnAdapter result = new ReturnAdapter();
		try{
			Lista l = listaService.getListaById(id);			
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
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getListas(){
		ReturnAdapter result = new ReturnAdapter();
		try{
			List l = listaService.getListas();
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
	 * @param lista
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,  
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter save(@RequestBody Lista lista){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = listaService.saveLista(lista);								
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
	 * @param lista
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter update(
			@PathVariable("id") Integer id,
			@RequestBody Lista lista){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = listaService.updateLista(id, lista);						
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
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter delete(
			@PathVariable("id") Integer id){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = listaService.deleteLista(id);						
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
}