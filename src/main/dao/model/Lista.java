/**
 * 
 */
package main.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Tipo de articulos.
 * 
 * @author ddiaz
 *
 */
@Entity
@Table(name="lista")
public class Lista {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "descripcion", unique=true, nullable = false)
	private String descripcion;
	
	@Column(name = "idUsuario", unique=true)
	private int idUsuario; //id del usuario creador de la lista
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	private boolean borrado;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * @return the borrado
	 */
	public boolean isBorrado() {
		return borrado;
	}
	/**
	 * @param borrado the borrado to set
	 */
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lista [id=" + id + ", descripcion=" + descripcion
				+ ", idUsuario=" + idUsuario + ", created=" + created
				+ ", updated=" + updated + ", borrado=" + borrado + "]";
	}
	
	

	
}
