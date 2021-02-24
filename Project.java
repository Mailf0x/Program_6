import java.util.Date;


public class Project {
    private Integer id;
    private String customer;
    private String name;
    private Date start;
    private Date planFinish;
    private Date finish;
    private String manager;
    private Boolean isCompleted;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getCustomer() {
        return customer;
    }


    public void setCustomer(String customer) {
        this.customer = customer;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getStart() {
        return start;
    }


    public void setStart(Date start) {
        this.start = start;
    }


    public Date getPlanFinish() {
        return planFinish;
    }


    public void setPlanFinish(Date planFinish) {
        this.planFinish = planFinish;
    }


    public Date getFinish() {
        return finish;
    }


    public void setFinish(Date finish) {
        this.finish = finish;
    }


    public String getManager() {
        return manager;
    }


    public void setManager(String manager) {
        this.manager = manager;
    }


    public Boolean getIsCompleted() {
        return isCompleted;
    }


    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
