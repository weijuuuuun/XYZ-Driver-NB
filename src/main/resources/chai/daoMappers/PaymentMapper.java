package chai.daoMappers;

import chai.Services.DateService;
import chai.models.Member;
import chai.models.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentMapper {

    public List<Payment> mapPayment(ResultSet resultSet) throws SQLException, ParseException {
        if(!resultSet.isBeforeFirst()){
            return null;
        }

        List<Payment> payments = new ArrayList<Payment>();
        DateService dateService = new DateService("yyyy-MM-dd hh:mm:ss");

        while(resultSet.next()){
            Payment payment = new Payment();
            Member member = new Member();
            member.setId(resultSet.getString("mem_id"));



            payment.setId(resultSet.getInt("id"));
            payment.setMember(member);
            payment.setTypeOfPayment(resultSet.getString("type_of_payment"));
            payment.setAmount(resultSet.getFloat("amount"));

            Date dateTime = dateService.stringToDate(resultSet.getString("date") + " " +  resultSet.getString("time"));
            payment.setDate(dateTime);

            payments.add(payment);
        }


        return payments;
    }
}
