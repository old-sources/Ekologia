package coop.ekologia.DTO.cms;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuConfigurationDTO {
    private String code;
    private List<MenuConfigurationParameterDTO> parameters = new ArrayList<MenuConfigurationParameterDTO>();

    public String getCode() {
        return code;
    }

    public MenuConfigurationDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public MenuConfigurationDTO addParameter(MenuConfigurationParameterDTO menuConfigurationParameterDTO) {
        parameters.add(menuConfigurationParameterDTO);
        return this;
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    private JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        List<String> jsonParameters = new ArrayList<String>();
        for (MenuConfigurationParameterDTO parameter: parameters) {
            jsonParameters.add(parameter.getName());
        }
        json.put("parameters", jsonParameters);
        return json;
    }
}
