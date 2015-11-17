package main.dao.implement;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.dao.interfaces.IArticuloDao;
import main.dao.model.Articulo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;

@Repository("articuloDao")
public class ArticuloDao extends AbstractDao<Articulo, Serializable> implements IArticuloDao{
 
    public void saveArticulo(Articulo articulo) {
        persist(articulo);
    }
 
    @SuppressWarnings("unchecked")
    public List<Articulo> findAllArticulos() {
        Criteria criteria = getSession().createCriteria(Articulo.class);
        Criteria criteriaConOrder = asignarOrdenacionACriteria(criteria);
		List<Articulo> list = criteriaConOrder.list();
		return list;		        
    }
 
    public void deleteArticuloById(int ssn) {
        Query query = getSession().createSQLQuery("delete from Articulo where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public Articulo findById(int ssn){
        Criteria criteria = getSession().createCriteria(Articulo.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (Articulo) criteria.uniqueResult();
    }
     
    public void updateArticulo(Articulo articulo){
        getSession().update(articulo);
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Articulo> getByQuery(Map<String, Object> m)
			throws DataException {
		Criteria crit = this.getSession().createCriteria(Articulo.class);

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
			if (e.getKey().equals("tipo"))
				crit.add(Restrictions.sqlRestriction("tipo=" + e.getValue()));
			
		}
		
		//reemplazar por la linea de arriba las dos siguientes si se requiere ordenacion
		//Si en vez de Criteria es un SQLQuery echa un vistazo a la clase del proyecto compras-rest ProveedorMercanciaDAOImpl
		Criteria criteriaConOrder = asignarOrdenacionACriteria(crit);
		List<Articulo> list = criteriaConOrder.list();
		return list;
	}
     
}