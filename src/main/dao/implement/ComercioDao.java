package main.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import main.dao.interfaces.IComercioDao;
import main.dao.model.Comercio;

@Repository("comercioDao")
public class ComercioDao extends AbstractDao implements IComercioDao{
 
    public void saveComercio(Comercio comercio) {
        persist(comercio);
    }
 
    @SuppressWarnings("unchecked")
    public List<Comercio> findAllComercios() {
        Criteria criteria = getSession().createCriteria(Comercio.class);
        return (List<Comercio>) criteria.list();
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
     
}