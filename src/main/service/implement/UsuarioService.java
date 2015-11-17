/**
 * 
 */
package main.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.dao.interfaces.IUsuarioDao;
import main.dao.model.Usuario;
import main.service.interfaces.IUsuarioService;
import main.utils.ReturnAdapter;
import main.utils.StandardResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ddiaz
 *
 */
@Component
@Transactional
public class UsuarioService implements IUsuarioService {

	 @Autowired
	 private IUsuarioDao usuarioDao;
	     
	 
	/* (non-Javadoc)
	 * @see service.interfaces.IUsuarioService#getUsuarioById(java.lang.Integer)
	 */	
	public Usuario getUsuarioById(Integer id) throws Exception {
		return usuarioDao.findById(id);		
	}
	
	public List<Usuario> getUsuarios() throws Exception{
		return usuarioDao.findAllUsuarios();
	}
	
	public ReturnAdapter saveUsuario(Usuario usuario) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//ponemos la fecha de creacion 
		usuario.setCreated(new Date());
		usuarioDao.saveUsuario(usuario);
		
		result.setCode(StandardResponse.OK);
		result.setMessage(StandardResponse.MESSAGE_OK_CREADO);
		result.setNumResult(1);
		List<Object> lista = new ArrayList<Object>();
		lista.add(usuarioDao.findById(usuario.getId()));
		result.setData(lista);
		
		return result;
    }
	
	public ReturnAdapter deleteUsuario(Integer id) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Usuario leido = usuarioDao.findById(id);
		
		if (leido==null){	
			//si no existe no se puede borrar.
			
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}else{		
			usuarioDao.deleteUsuarioById(id);			
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ELIMINADO);
			result.setNumResult(0);
		}
		
		return result;
	}
	
	public ReturnAdapter updateUsuario(Integer id, Usuario usuario) throws Exception{
		ReturnAdapter result = new ReturnAdapter();
		//comprobamos primero si el elemento existe.
		Usuario leido = usuarioDao.findById(id);
		
		if (leido!=null){					
			result.setCode(StandardResponse.OK);
			result.setMessage(StandardResponse.MESSAGE_OK_ACTUALIZADO);
			result.setNumResult(1);			
			
			Usuario elementoGuardar = volcarValores(usuario, leido);
			usuarioDao.updateUsuario(elementoGuardar);
			
			List<Object> lista = new ArrayList<Object>();
			lista.add(elementoGuardar);
			result.setData(lista);
			
		}else{
			result.setCode(StandardResponse.SIN_CONTENIDO);
			result.setMessage(StandardResponse.MESSAGE_SIN_CONTENIDO);
			result.setNumResult(0);
		}		
		return result;
    }

	/**
	 * 
	 * se retorna un elemento combinado de ambos.
	 * 
	 * @param pet
	 * @param leido
	 * @return
	 */
	private Usuario volcarValores(Usuario pet, Usuario leido) {
		Usuario res = leido;
		 
		
		if (pet.getPassword()!=null)
			res.setPassword(pet.getPassword());
		if (pet.getNombre()!=null)
			res.setNombre(pet.getNombre());
		if (pet.getApellidos()!=null)
			res.setApellidos(pet.getApellidos());
		if (pet.getEmail()!=null)
			res.setEmail(pet.getEmail());
			
		res.setId(leido.getId());
		res.setCreated(leido.getCreated());
		res.setBorrado(pet.isBorrado());
		
		res.setUpdated(new Date());
		return res;
	}

	/**
	 *  Este metodo asigna el orderBy. Descomentar si se va a requerir.
	 *
	 */
	public void setOrderBy(String orderBy) {
		this.usuarioDao.setOrderBy(orderBy);
	}
}
