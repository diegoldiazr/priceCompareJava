package main.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import main.dao.interfaces.IElementoListaDao;
import main.dao.model.ElementoLista;

@Repository("elementoListaDao")
public class ElementoListaDao extends AbstractDao implements IElementoListaDao{
 
    public void saveElementoLista(ElementoLista elementoLista) {
        persist(elementoLista);
    }
 
    @SuppressWarnings("unchecked")
    public List<ElementoLista> findAllElementoListas() {
        Criteria criteria = getSession().createCriteria(ElementoLista.class);
        return (List<ElementoLista>) criteria.list();
    }
 
    public void deleteElementoListaById(int ssn) {
        Query query = getSession().createSQLQuery("delete from ElementoLista where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public ElementoLista findById(int ssn){
        Criteria criteria = getSession().createCriteria(ElementoLista.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (ElementoLista) criteria.uniqueResult();
    }
     
    public void updateElementoLista(ElementoLista elementoLista){
        getSession().update(elementoLista);
    }
     
}