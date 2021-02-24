public class Customer {
    private Integer id;
    private String name;
    private String juridicalAdress;
    private Integer projects;
    private Integer completedProjects;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getJuridicalAdress() {
        return juridicalAdress;
    }


    public void setJuridicalAdress(String juridicalAdress) {
        this.juridicalAdress = juridicalAdress;
    }


    public Integer getProjects() {
        return projects;
    }


    public void setProjects(Integer projects) {
        this.projects = projects;
    }


    public Integer getCompletedProjects() {
        return completedProjects;
    }


    public void setCompletedProjects(Integer completedProject) {
        this.completedProjects = completedProject;
    }
}
