package main.dao.implement;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.dao.interfaces.IListaDao;
import main.dao.model.Lista;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;

@Repository("listaDao")
public class ListaDao extends AbstractDao<Lista, Serializable> implements IListaDao{
 
    public void saveLista(Lista lista) {
        persist(lista);
    }
 
    @SuppressWarnings("unchecked")
    public List<Lista> findAllListas() {
        Criteria criteria = getSession().createCriteria(Lista.class);
        Criteria criteriaConOrder = asignarOrdenacionACriteria(criteria);
		List<Lista> list = criteriaConOrder.list();
		return list;		        
    }
 
    public void deleteListaById(int ssn) {
        Query query = getSession().createSQLQuery("delete from Lista where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public Lista findById(int ssn){
        Criteria criteria = getSession().createCriteria(Lista.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (Lista) criteria.uniqueResult();
    }
     
    public void updateLista(Lista lista){
        getSession().update(lista);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Lista> getByQuery(Map<String, Object> m) throws DataException {
		Criteria crit = this.getSession().createCriteria(Lista.class);

		/**
		 * Extraemos el mapa de parametros.
		 *
		 */
		Iterator it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			if (e.getKey().equals("descripcion"))
				crit.add(Restrictions.sqlRestriction("trim(ucase(descripcion)) like '%" + e.getValue() + "%'"));
			if (e.getKey().equals("borrado"))
				crit.add(Restrictions.sqlRestriction("borrado=" + e.getValue()));
			if (e.getKey().equals("idUsuario"))
				crit.add(Restrictions.sqlRestriction("idUsuario=" + e.getValue()));
			
		}
		
		//reemplazar por la linea de arriba las dos siguientes si se requiere ordenacion
		//Si en vez de Criteria es un SQLQuery echa un vistazo a la clase del proyecto compras-rest ProveedorMercanciaDAOImpl
		Criteria criteriaConOrder = asignarOrdenacionACriteria(crit);
		List<Lista> list = criteriaConOrder.list();
		return list;
	}
     
}