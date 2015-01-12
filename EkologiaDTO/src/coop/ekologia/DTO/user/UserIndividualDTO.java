package coop.ekologia.DTO.user;

import java.util.Date;

public class UserIndividualDTO extends UserDTO {
    private static final long serialVersionUID = 4406131773463669828L;

    private String firstname;

    private String lastname;

    private Date birthday;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
