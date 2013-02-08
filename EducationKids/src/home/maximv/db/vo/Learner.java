package home.maximv.db.vo;

public class Learner {

    public Learner(int pid, String firstName, String middleName, String lastName, String phoneNumber, String login, String bornYear, String email,String sex,int active) {
        super();
        this.pid=pid;
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.login=login;
        this.bornYear=bornYear;
        this.sex=sex;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.active=active==0?false:true;
    }

    public Learner() {
        super();
    }

    private int pid;
    
    private String firstName;

    private String middleName;

    private String lastName;
    
    private String phoneNumber;
    
    private String login;
    
    private String bornYear;
    
    private String email;
    
    private String sex;

    private boolean active;

    public int getPid() {
        return this.pid;
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public String getMiddleName() {
        return this.middleName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getLogin() {
        return this.login;
    }

    public String getBornYear() {
        return this.bornYear;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSex() {
        return this.sex;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setPid(int pid) {
        this.pid=pid;
    }

    public void setFirstName(String firstname) {
        this.firstName=firstname;
    }

    public void setMiddleName(String middleName) {
        this.middleName=middleName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public void setLogin(String login) {
        this.login=login;
    }

    public void setBornYear(String bornYear) {
        this.bornYear=bornYear;
    }

    public void setSex(String sex) {
        this.sex=sex;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber=phone;
    }

    public void setEmail(String email) {
        this.email=email;
    }
    public void setActive(boolean active) {
        this.active=active;
    }

}
