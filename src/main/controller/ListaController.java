/**
 * 
 */
package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.dao.model.Lista;
import main.service.interfaces.IListaService;
import main.utils.OrderingBean;
import main.utils.ReturnAdapter;
import main.utils.StandardResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ListaController {
	
	@Autowired
	private IListaService listaService;

	private Logger log = Logger.getLogger(ListaController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/data/listas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@RequestMapping(method = RequestMethod.GET, 
			value="/data/listas", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ReturnAdapter getListas(
		@RequestParam(value="descripcion", required=false) String descripcion,
		@RequestParam(value="idUsuario", required=false) String idUsuario,
		@RequestParam(value="borrado", required=false) String borrado,
		@RequestParam(value="orderBy", required=false) String orderBy){
		ReturnAdapter result = new ReturnAdapter();
		OrderingBean ordering = new OrderingBean(orderBy);
		listaService.setOrderBy(ordering.getOrderBy());
		
		try{			
			List l = null;
			if (descripcion==null && idUsuario==null && borrado==null)
				l = listaService.getListas();
			else
				l = listaService.getListasByQuery(descripcion, idUsuario, borrado);
			
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
				value="/data/listas",
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
	@RequestMapping(method = RequestMethod.PUT, value = "/data/listas/{id}", 
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
	@RequestMapping(method = RequestMethod.DELETE, value = "/data/listas/{id}", 
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
