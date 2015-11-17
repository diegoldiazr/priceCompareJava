package main.dao.implement;

import java.io.Serializable;
import java.util.List;

import main.dao.interfaces.IUsuarioDao;
import main.dao.model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("usuarioDao")
public class UsuarioDao extends AbstractDao<Usuario, Serializable> implements IUsuarioDao{
 
    public void saveUsuario(Usuario usuario) {
        persist(usuario);
    }
 
    @SuppressWarnings("unchecked")
    public List<Usuario> findAllUsuarios() {
        Criteria criteria = getSession().createCriteria(Usuario.class);
        Criteria criteriaConOrder = asignarOrdenacionACriteria(criteria);
		List<Usuario> list = criteriaConOrder.list();
		return list;		        
    }
 
    public void deleteUsuarioById(int ssn) {
        Query query = getSession().createSQLQuery("delete from Usuario where id = :ssn");
        query.setInteger("ssn", ssn);
        query.executeUpdate();
    }
 
     
    public Usuario findById(int ssn){
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("id",ssn));
        return (Usuario) criteria.uniqueResult();
    }
     
    public void updateUsuario(Usuario usuario){
        getSession().update(usuario);
    }
     
}