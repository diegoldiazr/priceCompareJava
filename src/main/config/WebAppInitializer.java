/**
 * 
 */
package main.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author ddiaz
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
@Override
protected Class<?>[] getRootConfigClasses() {
return new Class[] { AppConfig.class };
}

@Override
protected Class<?>[] getServletConfigClasses() {
return null;
}

@Override
protected String[] getServletMappings() {
return new String[] { "/" };
}

}
/*
public class WebAppInitializer implements WebApplicationInitializer {
	public void onStartup(ServletContext servletContext) throws ServletException {  
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
        ctx.register(AppConfig.class);  
        ctx.setServletContext(servletContext);    
        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
        dynamic.addMapping("/");  
        dynamic.setLoadOnStartup(1);
	}

}
*/
