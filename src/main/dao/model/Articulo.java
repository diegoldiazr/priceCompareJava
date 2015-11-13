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
 * @author ddiaz
 *
 */
@Entity
@Table(name="articulo")
public class Articulo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", unique=true, nullable = false)
	private String nombre;
	private String linkImagen;
	private Integer tipo; //define el tipo de articulo de la tabla TipoArticulo
	
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the linkImagen
	 */
	public String getLinkImagen() {
		return linkImagen;
	}
	/**
	 * @param linkImagen the linkImagen to set
	 */
	public void setLinkImagen(String linkImagen) {
		this.linkImagen = linkImagen;
	}
	/**
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	
}
