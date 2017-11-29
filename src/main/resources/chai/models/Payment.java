package chai.models;

import chai.Services.DateService;

import java.util.Date;

public class Payment {

    private int id;
    private String typeOfPayment;
    private float amount;
    private Date date;
    private Member member;
    private DateService dateService;
    private DateService timeService;

    public Payment(){
        this.dateService = new DateService("yyyy-MM-dd");
        this.timeService = new DateService("hh:mm:ss");
    }

    public Payment(int id, String typeOfPayment, float amount, Date date, Member member) {
        this.id = id;
        this.typeOfPayment = typeOfPayment;
        this.amount = amount;
        this.date = date;
        this.member = member;
        this.dateService = new DateService("yyyy-MM-dd");
        this.timeService = new DateService("hh:mm:ss");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString(){
        return this.dateService.dateToString(this.date);
    }

    public String getTimeString(){
        return this.timeService.dateToString(this.date);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
