package main.dao.implement;

import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.persistence.Column;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T, ID> {
	
	private Logger log = Logger.getLogger(AbstractDao.class);
	
	private String orderBy;
	 
    @Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public void persist(Object entity) {
        getSession().persist(entity);
    }
 
    public void delete(Object entity) {
        getSession().delete(entity);
    }
    
    /**
	 * Este metodo retorna el objeto recibido al que le ha asignado la ordenacion orderBy
	 * 
	 * @param crit
	 * 
	 */
	public Criteria asignarOrdenacionACriteria(Criteria crit){
		Criteria critRetorno = crit;
		log.debug("asignarOrdenacionACriteria miramos si tiene ordenacion");
		//si se ha indicado ordenacion lo incorporamos. para ello tenemos que extraer la lista de
		//los atributos indicados, que seran los nombres del bean por ser un criteria.
		if (orderBy!=null){
			log.debug("asignarOrdenacionACriteria- tiene ordenacion empezamos a extraer la lista de ordenacion");
			StringTokenizer st = new StringTokenizer(orderBy, ",");			
			while (st.hasMoreElements()) {
				String elemSinTokenizar = (String) st.nextElement();
				StringTokenizer st2 = new StringTokenizer(elemSinTokenizar, " ");	
				//primer elemento sera el atributo
				//El atributo necesita un tratamiento especial ya que si no la consulta falla.
				//debemos convertir el atributo a mayusculas y tomar la primera letra y ponerla en minusculas.
				String atributo = ((String) st2.nextElement()).trim();
				
				//el segundo sera la ordenacion asc o desc si no pone nada pondremos asc.
				String ordenacion = "ASC";
				if (st2.hasMoreElements()) {
					ordenacion = ((String) st2.nextElement()).trim();
				}
								
				log.debug("valor de atributo= " + atributo);
				log.debug("valor de ordenacion= " + ordenacion);
				if (ordenacion.equals("ASC")){
					critRetorno.addOrder(Order.asc(atributo));	
				}else{
					critRetorno.addOrder(Order.desc(atributo));	
				}				
			}
		}
		
		return critRetorno;
	}
	
	/**
	 * Este metodo retorna el objeto recibido al que le ha asignado la ordenacion orderBy
	 * @param class1 
	 * 
	 * @param String
	 * 
	 */
	public String asignarOrdenacionASQLString(String sql, Class<T> class1){
		
		log.debug("asignarOrdenacionACriteria miramos si tiene ordenacion");
		//si se ha indicado ordenacion lo incorporamos. para ello tenemos que extraer la lista de
		//los atributos indicados, que seran los nombres del bean por ser un criteria.		
		if (orderBy!=null){
			
			sql += " ORDER BY ";
					
			log.debug("asignarOrdenacionACriteria- tiene ordenacion empezamos a extraer la lista de ordenacion");
			StringTokenizer st = new StringTokenizer(orderBy, ",");			
			while (st.hasMoreElements()) {
				String elemSinTokenizar = (String) st.nextElement();
				StringTokenizer st2 = new StringTokenizer(elemSinTokenizar, " ");	
				//primer elemento sera el atributo
				//El atributo necesita un tratamiento especial ya que si no la consulta falla.
				//debemos convertir el atributo a mayusculas y tomar la primera letra y ponerla en minusculas.
				String atributo = ((String) st2.nextElement()).trim();
				
				//el segundo sera la ordenacion asc o desc si no pone nada pondremos asc.
				String ordenacion = "ASC";
				if (st2.hasMoreElements()) {
					ordenacion = ((String) st2.nextElement()).trim();
				}
				log.debug("valor de atributo= " + atributo);
				log.debug("valor de ordenacion= " + ordenacion);
				
				//obtenemos el nombre de base de datos porque el de java no sirve para este tipo de consultas.				
				try {
					Method metodo = class1.getMethod("get"+atributo, null);
					Column a = metodo.getAnnotation(Column.class);
					atributo = a.name();
				} catch (SecurityException e) {
					log.error(e);				
				} catch (NoSuchMethodException e) {
					log.error(e);
				}								
				sql += atributo + " " + ordenacion + ", ";					
			}
			
			//debemos quitar la ultima coma por lo que quitamos los dos ultimos elementos
			sql = sql.substring(0, sql.length()-2).trim(); 
		}
		
		log.debug("sql formada: " + sql);
						
		return sql;
	}
	
	/**
	 * metodo que asigna la ordenacion a una sql que no tiene un fichero asociado.
	 * 
	 * @param consultaSQL
	 * @return
	 */
	public String asignarOrdenacionASQLStringSinFicheroAsociado(String sql) {
		if (orderBy!=null){		
			sql += " ORDER BY ";
			log.debug("asignarOrdenacionASQLStringSinFicheroAsociado- tiene ordenacion empezamos a extraer la lista de ordenacion");
			StringTokenizer st = new StringTokenizer(orderBy, ",");			
			while (st.hasMoreElements()) {
				String elemSinTokenizar = (String) st.nextElement();
				StringTokenizer st2 = new StringTokenizer(elemSinTokenizar, " ");	
				//primer elemento sera el atributo
				//El atributo necesita un tratamiento especial ya que si no la consulta falla.
				//debemos convertir el atributo a mayusculas y tomar la primera letra y ponerla en minusculas.
				String atributo = ((String) st2.nextElement()).trim();
				
				//el segundo sera la ordenacion asc o desc si no pone nada pondremos asc.
				String ordenacion = "ASC";
				if (st2.hasMoreElements()) {
					ordenacion = ((String) st2.nextElement()).trim();
				}
				log.debug("valor de atributo= " + atributo);
				log.debug("valor de ordenacion= " + ordenacion);
															
				sql += atributo + " " + ordenacion + ", ";					
			}
			
			//debemos quitar la ultima coma por lo que quitamos los dos ultimos elementos
			sql = sql.substring(0, sql.length()-2).trim(); 
		}
		return sql;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}