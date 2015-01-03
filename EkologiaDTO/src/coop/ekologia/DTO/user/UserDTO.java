/**
 * 
 */
package coop.ekologia.DTO.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author imie
 *
 */
public class UserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1822397517797929630L;
	private Integer id;
	private String nom;
	private String prenom;
	private Date dateNaiss;
	private String eMail;
	private String passw;
	
	/**
	 * 
	 */
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDateNaiss() {
		return dateNaiss;
	}


	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}


	public String geteMail() {
		return eMail;
	}


	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	public String getPassw() {
		return passw;
	}


	public void setPassw(String passw) {
		this.passw = passw;
	}




}
