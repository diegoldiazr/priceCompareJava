/**
 * 
 */
package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.dao.model.Comercio;
import main.service.interfaces.IComercioService;
import main.utils.Return;
import main.utils.StandardResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
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
	public Return getComercioById(
			@PathVariable("id") Integer id){
		Return result = new Return();
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
	public Return getComercios(){
		Return result = new Return();
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
}
