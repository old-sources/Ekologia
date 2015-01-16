/**
 * 
 */
package coop.ekologia.DTO.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1822397517797929630L;

	private Integer id;
	private String email;
	private String password;
	private String salt;
	private String addressStreet;
	private String addressZipcode;
	private String addressCity;
	private String phoneNumber;
	private String avatar;
	private String description;
	private String country;
	private List<String> roles;
	
	// form fields
	private String usertype;

	public UserDTO() {
		roles=new ArrayList<String>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
	    return salt;
	}
	
	public void setSalt(String salt) {
	    this.salt = salt;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressZipcode() {
		return addressZipcode;
	}

	public void setAddressZipcode(String addressZipcode) {
		this.addressZipcode = addressZipcode;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role) {
		roles.add(role);
	}
	
	public void removeRole(String role) {
		roles.remove(role);
	}
	
	public boolean hasRole(String role) {
		return roles.contains(role);
	}
	
	public boolean hasRoles(String... roleList) {
		return roles.containsAll(Arrays.asList(roleList));
	}
	
	public boolean hasRoles(Collection<String> roleList) {
		return roles.containsAll(roleList);
	}

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
	
	public Boolean getAdmin(){
		Boolean retour =false;
		for (String role : getRoles()) {
			if (role.compareTo("admin")==0){
				retour=true;
			}
		}
		return retour;
	}
}
