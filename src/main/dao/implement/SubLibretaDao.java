package main.dao.implement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;

import main.dao.interfaces.ISubLibretaDao;
import main.dao.model.SubLibreta;

@Repository("subLibretaDao")
public class SubLibretaDao extends AbstractDao implements ISubLibretaDao{
 
    public void saveSubLibreta(SubLibreta subLibreta) {
        persist(subLibreta);
    }
 
    @SuppressWarnings("unchecked")
    public List<SubLibreta> findAllSubLibretas() {
        Criteria criteria = getSession().createCriteria(SubLibreta.class);
        return (List<SubLibreta>) criteria.list();
    }
 
    public void deleteSubLibretaById(int ssn) {
        Query query = getSession().createSQLQuery("delete from SubLibreta where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public SubLibreta findById(int ssn){
        Criteria criteria = getSession().createCriteria(SubLibreta.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (SubLibreta) criteria.uniqueResult();
    }
     
    public void updateSubLibreta(SubLibreta subLibreta){
        getSession().update(subLibreta);
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<SubLibreta> getByQuery(Map<String, Object> m) throws DataException {
		Criteria crit = this.getSession().createCriteria(SubLibreta.class);

		/**
		 * Extraemos el mapa de parametros.
		 *
		 */
		Iterator it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			if ("IDLIBRETA".equals(e.getKey()))
				crit.add(Restrictions.sqlRestriction("idLibreta=" + e.getValue()));
		}
		
		return (List<SubLibreta>) crit.list();
		
	}
     
}