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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * @author ddiaz
 *
 */
@Entity
@Table(name="comercio")
public class Comercio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nombre", unique=true, nullable = false)
	private String nombre;
	private String linkImagen;
	private int puntuacion;
	
	
	private Date created;
	private Date updated;
	private boolean borrado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
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
	
	
	@PrePersist
	protected void onCreate(){
		created = new Date();
	}
	
	
	@PreUpdate
	protected void onUpdate(){
		updated = new Date();
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
	 * @return the puntuacion
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
}