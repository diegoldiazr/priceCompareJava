package test.java.junit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import main.dao.model.Comercio;
import main.utils.StandardResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(Parameterized.class)
public class ComercioTest
{

	private int resultado;
	private String parametro;
	private String metodo;
	
	private int elementoCreado;


	/**
	 * Constructor de la clase
	 *
	 */
	public ComercioTest(int resultado, String parametro, String metodo) {
		this.resultado = resultado;
		this.parametro = parametro;
		this.metodo = metodo;
	}
	
	/**
	 * Casos de uso ESTA CLASE DEBE ADAPTARSE A LOS REQUERIMIENTOS DE LA CLASE DE TEST
	 *
	 */
	@Parameters
	public static Collection<Object[]> addedNumbers() {
		return Arrays.asList(
			new Object[][] { 

					///*1*/ 	{ StandardResponse.SOLICITUD_INCORRECTA, "", "POST"},
					/*2*/ 	{ StandardResponse.OK, "", "POST"},


					/*3*/ 	{ StandardResponse.OK, "/1", "GET"},
					///*4*/ 	{ StandardResponse.OK, "?emp=25", "GET"},					
					/*11*/ 	{ StandardResponse.OK, "", "GET"},
					
					/*12*/ 	{ StandardResponse.SIN_CONTENIDO, "/98", "GET"},
					///*12+1*/{ StandardResponse.SIN_CONTENIDO, "?emp=98", "GET"},					
					
					/*20*/ 	{ StandardResponse.SOLICITUD_INCORRECTA, "/98aa", "GET"},
					
					///*25*/ 	{ StandardResponse.SOLICITUD_INCORRECTA, "", "PUT"},
					/*26*/ 	{ StandardResponse.OK, "", "PUT"},
					
					/*0*/ 	{ StandardResponse.OK, "", "DELETE"},
			}
		);
	}

  @Test
  public void add() throws IOException {
	  if  (metodo.equals("POST")){
		    RestTemplate rest = new TestRestTemplate();		    
		    Comercio comercio = new Comercio();
						    
		    if (resultado>=400){		       
			    ResponseEntity<String> response = rest.postForEntity(JUnitConstantes.URL_CONNECTION + "/comercios", comercio, String.class);
			    
		    	assertThat(response.getStatusCode().value() , is(resultado));
		    }else{
		        comercio.setNombre("nombre random 3");
			    ResponseEntity<String> response = rest.postForEntity(JUnitConstantes.URL_CONNECTION + "/comercios", comercio, String.class);
		    	
			    assertThat(response.getStatusCode() , is(HttpStatus.OK));
		    
			    ObjectMapper objectMapper = new ObjectMapper();
			    JsonNode responseJson = objectMapper.readTree(response.getBody());
			    
			    System.out.println("TEST POST: Json retornado: ");
			    System.out.println(responseJson.toString());
			    
			    assertEquals(200, responseJson.path("code").asInt());
			    
			    for (JsonNode node : responseJson.path("data")) {
			    	elementoCreado = node.path("id").asInt();
			        System.out.println("Entry: "+node.toString());
			      }			  			   			    			    
		    }
	  }
  }
  
  @Test
  public void update() throws IOException {
	  if  (metodo.equals("PUT")){
		    RestTemplate rest = new TestRestTemplate();		    
		
	    	String requestBody = "{\"id\":\"" + elementoCreado +"\", \"nombre\":\"nombre ramdom 2\"}";
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON); 
	        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers); 
		    ResponseEntity<String> response = rest.exchange(JUnitConstantes.URL_CONNECTION + "/comercios/" + elementoCreado, HttpMethod.PUT, entity, String.class, elementoCreado);
	    	
		    assertThat(response.getStatusCode() , is(HttpStatus.OK));
	    
		    ObjectMapper objectMapper = new ObjectMapper();
		    JsonNode responseJson = objectMapper.readTree(response.getBody());
		    
		    System.out.println("TEST POST: Json retornado: ");
		    System.out.println(responseJson.toString());
		    
		    assertEquals(200, responseJson.path("code").asInt());
		    
		    for (JsonNode node : responseJson.path("data")) {
		    	elementoCreado = node.path("id").asInt();
		        System.out.println("Entry: "+node.toString());
		      }			  			   			    			    

	  }
  }
  
  @Test
  public void delete() throws IOException {
	  if  (metodo.equals("DELETE")){
		    RestTemplate rest = new TestRestTemplate();		    

		    HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON); 
	        HttpEntity<String> entity = new HttpEntity<String>("", headers); 
		    ResponseEntity<String> response = rest.exchange(JUnitConstantes.URL_CONNECTION + "/comercios/" + elementoCreado, HttpMethod.DELETE, entity, String.class, elementoCreado);
		    assertThat(response.getStatusCode() , is(HttpStatus.OK));
		    
	  }
  }
  
  @Test
  public void test() throws IOException {
    
	  if  (metodo.equals("GET")){
		    RestTemplate rest = new TestRestTemplate();
		
		    ResponseEntity<String> response = rest.getForEntity(JUnitConstantes.URL_CONNECTION + "/comercios" + parametro, String.class);
				    
		    if (resultado>=400){
		    	assertThat(response.getStatusCode().value() , is(resultado));
		    }else{
		    	assertThat(response.getStatusCode() , is(HttpStatus.OK));
		    
			    ObjectMapper objectMapper = new ObjectMapper();
			    JsonNode responseJson = objectMapper.readTree(response.getBody());
			    JsonNode messageJson = responseJson.path("code");
			    
			    System.out.println("TEST GET: Json retornado: ");
			    System.out.println(responseJson.toString());
			    
			    assertThat( messageJson.asInt(), is(resultado) );
		    }
	  }
    
  }
}