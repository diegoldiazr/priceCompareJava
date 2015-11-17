package main.dao.implement;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.dao.interfaces.IPrecioDao;
import main.dao.model.Precio;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;

@Repository("precioDao")
public class PrecioDao extends AbstractDao<Precio, Serializable> implements IPrecioDao{
 
    public void savePrecio(Precio precio) {
        persist(precio);
    }
 
    @SuppressWarnings("unchecked")
    public List<Precio> findAllPrecios() {
        Criteria criteria = getSession().createCriteria(Precio.class);
        Criteria criteriaConOrder = asignarOrdenacionACriteria(criteria);
		List<Precio> list = criteriaConOrder.list();
		return list;		        
    }
 
    public void deletePrecioById(int ssn) {
        Query query = getSession().createSQLQuery("delete from Precio where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public Precio findById(int ssn){
        Criteria criteria = getSession().createCriteria(Precio.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (Precio) criteria.uniqueResult();
    }
     
    public void updatePrecio(Precio precio){
        getSession().update(precio);
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Precio> getByQuery(Map<String, Object> m) throws DataException {
		Criteria crit = this.getSession().createCriteria(Precio.class);

		/**
		 * Extraemos el mapa de parametros.
		 *
		 */
		Iterator it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			if (e.getKey().equals("idComercio"))
				crit.add(Restrictions.sqlRestriction("idComercio=" + e.getValue()));
			if (e.getKey().equals("borrado"))
				crit.add(Restrictions.sqlRestriction("borrado=" + e.getValue()));
			if (e.getKey().equals("idArticulo"))
				crit.add(Restrictions.sqlRestriction("idArticulo=" + e.getValue()));
			
		}
		
		//reemplazar por la linea de arriba las dos siguientes si se requiere ordenacion
		//Si en vez de Criteria es un SQLQuery echa un vistazo a la clase del proyecto compras-rest ProveedorMercanciaDAOImpl
		Criteria criteriaConOrder = asignarOrdenacionACriteria(crit);
		List<Precio> list = criteriaConOrder.list();
		return list;
	}
     
}