package chai.models;

import chai.Services.DateService;

import java.util.Date;

public class Member {

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private Date dob;
    private Date dor;
    private String status;
    private float balance;
    private User userAccount;
    private DateService dateService;

    public Member (){
        this.dateService = new DateService("yyyy-MM-dd");
    }

    public Member (String id, String firstName, String lastName,  String address, Date dob, Date dor, String status, float balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dob = dob;
        this.dor = dor;
        this.status = status;
        this.balance = balance;
        this.dateService = new DateService("yyyy-MM-dd");

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public String getDobString() {
        return this.dateService.dateToString(dob);
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDor() {
        return dor;
    }

    public void setDor(Date dor) {
        this.dor = dor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", dor=" + dor +
                ", status='" + status + '\'' +
                ", balance=" + balance +
                ", userAccount=" + userAccount +
                ", dateService=" + dateService +
                '}';
    }
}
