package main.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import main.dao.interfaces.IPrecioDao;
import main.dao.model.Precio;

@Repository("precioDao")
public class PrecioDao extends AbstractDao implements IPrecioDao{
 
    public void savePrecio(Precio precio) {
        persist(precio);
    }
 
    @SuppressWarnings("unchecked")
    public List<Precio> findAllPrecios() {
        Criteria criteria = getSession().createCriteria(Precio.class);
        return (List<Precio>) criteria.list();
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
     
}