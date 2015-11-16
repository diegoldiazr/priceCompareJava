/**
 * 
 */
package main.dao.model;

import java.util.Date;

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
@Table(name="precio")
public class Precio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int idComercio;
	private int idArticulo;
	
	private Double precio;
			
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
	 * @return the idComercio
	 */
	public int getIdComercio() {
		return idComercio;
	}
	/**
	 * @param idComercio the idComercio to set
	 */
	public void setIdComercio(int idComercio) {
		this.idComercio = idComercio;
	}
	/**
	 * @return the idArticulo
	 */
	public int getIdArticulo() {
		return idArticulo;
	}
	/**
	 * @param idArticulo the idArticulo to set
	 */
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
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
		return "Precio [id=" + id + ", idComercio=" + idComercio
				+ ", idArticulo=" + idArticulo + ", precio=" + precio
				+ ", created=" + created + ", updated=" + updated
				+ ", borrado=" + borrado + "]";
	}
	
	


	
}
