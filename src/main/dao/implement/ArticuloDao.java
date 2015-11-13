package main.dao.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import main.dao.interfaces.IArticuloDao;
import main.dao.model.Articulo;

@Repository("articuloDao")
public class ArticuloDao extends AbstractDao implements IArticuloDao{
 
    public void saveArticulo(Articulo articulo) {
        persist(articulo);
    }
 
    @SuppressWarnings("unchecked")
    public List<Articulo> findAllArticulos() {
        Criteria criteria = getSession().createCriteria(Articulo.class);
        return (List<Articulo>) criteria.list();
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
     
}