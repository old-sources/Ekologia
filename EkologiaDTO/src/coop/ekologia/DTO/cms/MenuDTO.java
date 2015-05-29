package coop.ekologia.DTO.cms;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class MenuDTO {
    private Integer id;
    private String lang;
    private String role;
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

    public String getJsonEncoded() {
        return getJson().replaceAll("'", "\\'");
    }
}
