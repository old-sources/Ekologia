package coop.ekologia.DTO.cms;

import java.util.ArrayList;
import java.util.List;

public class MenuConfigurationParameterDTO {
    private String name;
    private List<MenuConfigurationParameterConstraint> constraints = new ArrayList<MenuConfigurationParameterConstraint>();
    List<String> errors = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public MenuConfigurationParameterDTO setName(String name) {
        this.name = name;
        return this;
    }

    public MenuConfigurationParameterDTO addConstraint( MenuConfigurationParameterConstraint constraint ) {
        constraints.add(constraint);
        return this;
    }

    public boolean verify(final String value) {
        errors = new ArrayList<String>();
        for (MenuConfigurationParameterConstraint constraint: constraints) {
            if (!constraint.verify(value)) {
                errors.add(constraint.getErrorCode());
            }
        }
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public static interface MenuConfigurationParameterConstraint {
        String getErrorCode();
        boolean verify(String value);
    }
}
