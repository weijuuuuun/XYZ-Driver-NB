package chai.Services;

import chai.dao.MemberDAO;
import chai.dao.PaymentDAO;
import chai.models.Payment;

import java.util.Date;
import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentService(){
        this.paymentDAO = new PaymentDAO();
    }

    public List<Payment> getPaymentOfMember(String memberId){
       return this.paymentDAO.getPaymentOfMember(memberId);
    }

    public void pay(String memberId, float amount){

        Date date = new Date();
        DateService dateService = new DateService("yyyy-MM-dd");
        DateService timeService = new DateService("hh:mm:ss");

        this.paymentDAO.pay(memberId);
        this.paymentDAO.addPaymentRecord(memberId, "FEE", amount, dateService.dateToString(date), timeService.dateToString(date));
        
    }
}
