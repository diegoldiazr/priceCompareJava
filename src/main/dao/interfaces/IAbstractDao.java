package main.dao.interfaces;

import org.hibernate.Criteria;

import main.utils.IOrderingDAO;

public interface IAbstractDao<T, Serializable> extends IOrderingDAO{

	
	Criteria asignarOrdenacionACriteria(Criteria crit);
		
	String asignarOrdenacionASQLString(String sql, Class<T> class1);
		
	String asignarOrdenacionASQLStringSinFicheroAsociado(String sql);
}
