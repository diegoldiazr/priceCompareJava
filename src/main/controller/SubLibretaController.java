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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.dao.model.SubLibreta;
import main.service.interfaces.ISubLibretaService;
import main.utils.Return;
import main.utils.StandardResponse;

/**
 * @author ddiaz
 *
 */
@RestController
@RequestMapping("/data")
public class SubLibretaController {
	
	@Autowired
	private ISubLibretaService subLibretaService;

	private Logger log = Logger.getLogger(SubLibretaController.class);
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, value = "/subLibretas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Return getLibretaById(
			@PathVariable("id") Integer id){
		Return result = new Return();
		try{			
			SubLibreta l = subLibretaService.getSubLibretaById(id);					
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
	@RequestMapping(method = RequestMethod.GET, value = "/subLibretas", produces = MediaType.APPLICATION_JSON_VALUE)
	public Return getLibretas(
			@RequestParam(value="idLibreta", required = false) Integer idLibreta){
		Return result = new Return();
		try{
			List l = null;
			if (idLibreta == null){
				l = subLibretaService.getSubLibretas();
			}else{
				l = subLibretaService.getByQuery(idLibreta);
			}
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
