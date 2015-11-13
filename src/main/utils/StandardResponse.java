/**
 * 
 */
package main.utils;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;



/**
 * @author ddiaz
 *
 */
public class StandardResponse {
	
	
	public static final int SERVICIO_NO_DISPONIBLE = 503;
	public static final int OK = 200;
	public static final int SIN_CONTENIDO = 204;
	public static final int ACEPTADO = 202;
	public static final int SOLICITUD_INCORRECTA = 400;
	public static final int ACCESO_DENEGADO = 403;
	public static final int METODO_NO_ENCONTRADO = 404;
	public static final int METODO_NO_PERMITIDO = 405;

	private static final HashMap<Class<? extends Exception>, Integer> LIST_EXCEPTIONS 
		= new LinkedHashMap<Class<? extends Exception>, Integer>();
	public static final String MESSAGE_OK = "OK";
	public static final String MESSAGE_SIN_CONTENIDO = "No Content";
	
	static {
		
		LIST_EXCEPTIONS.put(RuntimeException.class, SOLICITUD_INCORRECTA);
		LIST_EXCEPTIONS.put(NumberFormatException.class, SOLICITUD_INCORRECTA);		
		LIST_EXCEPTIONS.put(Exception.class, SERVICIO_NO_DISPONIBLE);
		LIST_EXCEPTIONS.put(AccessDeniedException.class, ACCESO_DENEGADO);
		LIST_EXCEPTIONS.put(IllegalArgumentException.class, SOLICITUD_INCORRECTA);
		LIST_EXCEPTIONS.put(NullPointerException.class, METODO_NO_PERMITIDO);			 

	}
	
	
	/**
	 * este metodo obtiene la respuesta http adecuada al tipo de excepcion
	 * recibido.
	 * 
	 * @param ex
	 *            excepcion
	 * @param log
	 *            Logger para escribir la excepcion en log.
	 * @return Response
	 */
	public static Return getResponseInExceptionInt(Throwable ex, Logger log) {
		Class<? extends Throwable> class1 = ex.getClass();
		Integer integer = LIST_EXCEPTIONS.get(class1);		

		if (integer == null) {
			log.debug("falta mapear la clase " + ex.getClass()
					+ " en la clase StandardResponse");
			integer = SERVICIO_NO_DISPONIBLE;
		}
		log.debug(ex);
		Return result = new Return();
		result.setCode(integer);
		result.setMessage(ex + " \n " + ex.getMessage() + " \n " + ex.getLocalizedMessage());		
		result.setNumResult(0);
		result.setData(null);
		return result;
	}

	

}
