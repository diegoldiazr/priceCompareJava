/**
 * 
 */
package main.config;

/**
 * @author ddiaz
 *
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration 
@ComponentScan({"main.*"}) 
@EnableWebMvc 
@Import({ SecurityConfiguration.class })
public class AppConfig {

}
