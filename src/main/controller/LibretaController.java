/**
 * 
 */
package main.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.dao.model.Libreta;
import main.service.interfaces.ILibretaService;
import main.utils.Return;
import main.utils.StandardResponse;

/**
 * @author ddiaz
 *
 */
@RestController
@RequestMapping("/data")
public class LibretaController {
	
	@Autowired
	private ILibretaService libretaService;

	private Logger log = Logger.getLogger(LibretaController.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/libretas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Return getLibretaById(
			@PathVariable("id") Integer id){
		Return result = new Return();
		try{
			Libreta l = libretaService.getLibretaById(id);			
			if (l!=null){											
				List lista = new ArrayList<>();
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
	@RequestMapping(method = RequestMethod.GET, value = "/libretas", produces = MediaType.APPLICATION_JSON_VALUE)
	public Return getLibretas(){
		Return result = new Return();
		try{
			List l = libretaService.getLibretas();
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
