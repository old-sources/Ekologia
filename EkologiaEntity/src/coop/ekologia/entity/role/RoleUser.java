package coop.ekologia.entity.role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity implementation class for Entity: RoleUser
 */
@Entity
@Table(name = "role_user")
@NamedQueries({
        @NamedQuery(
                name = RoleUser.FIND_ALL,
                query = "SELECT r FROM RoleUser r JOIN r.roleUserLangs rl WHERE rl.langue=:lang"
        )})
public class RoleUser implements Serializable {
    public static final String FIND_ALL = "roleuser.findall";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "roleUser")
    private List<RoleUserLang> roleUserLangs;

    @OneToMany(mappedBy = "roleUser")
    private List<UserRoleUser> userRoleUsers;

    public RoleUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RoleUserLang> getRoleUserLangs() {
        return roleUserLangs;
    }

    public void setRoleUserLangs(List<RoleUserLang> roleUserLangs) {
        this.roleUserLangs = roleUserLangs;
    }

    public List<UserRoleUser> getUserRoleUsers() {
        return userRoleUsers;
    }

    public void setUserRoleUsers(List<UserRoleUser> userRoleUsers) {
        this.userRoleUsers = userRoleUsers;
    }


}
