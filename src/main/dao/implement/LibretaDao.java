package main.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import main.dao.interfaces.ILibretaDao;
import main.dao.model.Libreta;

@Repository("libretaDao")
public class LibretaDao extends AbstractDao implements ILibretaDao{
 
    public void saveLibreta(Libreta libreta) {
        persist(libreta);
    }
 
    @SuppressWarnings("unchecked")
    public List<Libreta> findAllLibretas() {
        Criteria criteria = getSession().createCriteria(Libreta.class);
        return (List<Libreta>) criteria.list();
    }
 
    public void deleteLibretaById(int ssn) {
        Query query = getSession().createSQLQuery("delete from Libreta where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public Libreta findById(int ssn){
        Criteria criteria = getSession().createCriteria(Libreta.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (Libreta) criteria.uniqueResult();
    }
     
    public void updateLibreta(Libreta libreta){
        getSession().update(libreta);
    }
     
}