package main.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import main.dao.interfaces.IListaDao;
import main.dao.model.Lista;

@Repository("listaDao")
public class ListaDao extends AbstractDao implements IListaDao{
 
    public void saveLista(Lista lista) {
        persist(lista);
    }
 
    @SuppressWarnings("unchecked")
    public List<Lista> findAllListas() {
        Criteria criteria = getSession().createCriteria(Lista.class);
        return (List<Lista>) criteria.list();
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
     
}