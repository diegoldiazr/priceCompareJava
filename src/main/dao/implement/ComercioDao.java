package main.dao.implement;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.dao.interfaces.IComercioDao;
import main.dao.model.Comercio;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;

@Repository("comercioDao")
public class ComercioDao extends AbstractDao<Comercio, Serializable> implements IComercioDao{
	
	
 
    public void saveComercio(Comercio comercio) {
        persist(comercio);
    }
 
    @SuppressWarnings("unchecked")
    public List<Comercio> findAllComercios() {
        Criteria criteria = getSession().createCriteria(Comercio.class);
        
        Criteria criteriaConOrder = asignarOrdenacionACriteria(criteria);
		List<Comercio> list = criteriaConOrder.list();
		return list;		        
    }
 
    public void deleteComercioById(int ssn) {
        Query query = getSession().createSQLQuery("delete from Comercio where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public Comercio findById(int ssn){
        Criteria criteria = getSession().createCriteria(Comercio.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (Comercio) criteria.uniqueResult();
    }
     
    public void updateComercio(Comercio comercio){
        getSession().update(comercio);
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Comercio> getByQuery(Map<String, Object> m)
			throws DataException {
		Criteria crit = this.getSession().createCriteria(Comercio.class);


		/**
		 * Extraemos el mapa de parametros.
		 *
		 */
		Iterator it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			if (e.getKey().equals("nombre"))
				crit.add(Restrictions.sqlRestriction("trim(ucase(nombre)) like '%" + e.getValue() + "%'"));
			if (e.getKey().equals("borrado"))
				crit.add(Restrictions.sqlRestriction("borrado=" + e.getValue()));
			
		}
		
		//reemplazar por la linea de arriba las dos siguientes si se requiere ordenacion
		//Si en vez de Criteria es un SQLQuery echa un vistazo a la clase del proyecto compras-rest ProveedorMercanciaDAOImpl
		Criteria criteriaConOrder = asignarOrdenacionACriteria(crit);
		List<Comercio> list = criteriaConOrder.list();
		return list;
	}
     
}