package coop.ekologia.entity.cms;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="menu")
@NamedQueries({
        @NamedQuery(name = Menu.FIND, query = "SELECT m FROM Menu m WHERE m.lang=:lang AND m.role=:role")
})
public class Menu {
    public static final String FIND = "menu.find";

    @GeneratedValue
    @Id
    private Integer id;

    @Basic
    private String lang;

    @Basic
    private String role;

    @Basic
    private String json;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
