package main.dao.implement;

import java.io.Serializable;
import java.util.List;

import main.dao.interfaces.ITipoDao;
import main.dao.model.Tipo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("tipoDao")
public class TipoDao extends AbstractDao<Tipo, Serializable> implements ITipoDao{
 
    public void saveTipo(Tipo tipo) {
        persist(tipo);
    }
 
    @SuppressWarnings("unchecked")
    public List<Tipo> findAllTipos() {
        Criteria criteria = getSession().createCriteria(Tipo.class);
        Criteria criteriaConOrder = asignarOrdenacionACriteria(criteria);
		List<Tipo> list = criteriaConOrder.list();
		return list;		        
    }
 
    public void deleteTipoById(int ssn) {
        Query query = getSession().createSQLQuery("delete from Tipo where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public Tipo findById(int ssn){
        Criteria criteria = getSession().createCriteria(Tipo.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (Tipo) criteria.uniqueResult();
    }
     
    public void updateTipo(Tipo tipo){
        getSession().update(tipo);
    }
     
}