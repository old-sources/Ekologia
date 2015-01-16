package coop.ekologia.DTO.user;


public class UserOrganizationDTO extends UserDTO {
    private static final long serialVersionUID = 5426881669702048571L;

    private String name;

    private String activity;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
