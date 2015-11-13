/**
 * 
 */
package main.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ddiaz
 *
 */
@Entity
@Table(name="sublibreta")
public class SubLibreta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "idLibreta", nullable = false)
	private int idLibreta;
	@Column(name = "texto", nullable = false)
	private String texto;
	private String fechaCreacion;
	private String fechaModificacion;
	private boolean borrado;
	
	public int getId() {
		return id;
	}
	public int getIdLibreta() {
		return idLibreta;
	}
	public void setIdLibreta(int idLibreta) {
		this.idLibreta = idLibreta;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}	
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
}
