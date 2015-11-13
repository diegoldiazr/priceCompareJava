package main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.utils.Return;
import main.utils.Version;

@RestController
@RequestMapping("/")
public class VersionController {
	
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(VersionController.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/version", method = RequestMethod.GET)
	public Return version (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    Return resultado = new Return();
	    
	    Version version = new Version();
	    List data = new ArrayList();
	    data.add(version);
	    resultado.setData(data);
	    
	    return resultado;
	}

}
