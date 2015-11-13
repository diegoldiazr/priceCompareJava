/**
 * 
 */
package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.dao.model.Articulo;
import main.service.interfaces.IArticuloService;
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
@RequestMapping("/data/articulos")
public class ArticuloController {
	
	@Autowired
	private IArticuloService articuloService;

	private Logger log = Logger.getLogger(ArticuloController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getArticuloById(
			@PathVariable("id") Integer id){
		ReturnAdapter result = new ReturnAdapter();
		try{
			Articulo l = articuloService.getArticuloById(id);			
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
	public ReturnAdapter getArticulos(){
		ReturnAdapter result = new ReturnAdapter();
		try{
			List l = articuloService.getArticulos();
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
	 * @param articulo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,  
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter save(@RequestBody Articulo articulo){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = articuloService.saveArticulo(articulo);								
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
	 * @param articulo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter update(
			@PathVariable("id") Integer id,
			@RequestBody Articulo articulo){
		ReturnAdapter result = new ReturnAdapter();
		try{
			result = articuloService.updateArticulo(id, articulo);						
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
			result = articuloService.deleteArticulo(id);						
		}catch(Exception e){
			result = StandardResponse.getResponseInExceptionInt(e, log);
		}
		return result;
	}
}
