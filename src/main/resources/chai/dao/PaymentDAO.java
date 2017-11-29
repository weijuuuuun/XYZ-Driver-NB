package chai.dao;

import chai.Services.DateService;
import chai.daoMappers.MemberMapper;
import chai.daoMappers.PaymentMapper;
import chai.factories.DataSourceFactory;
import chai.models.Member;
import chai.models.Payment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private DataSource dataSource;
    private PaymentMapper paymentMapper;

    public PaymentDAO(){
        this.dataSource = DataSourceFactory.getMySqlDataSource();
        this.paymentMapper = new PaymentMapper();
    }

    public List<Payment> getPaymentOfMember(String memberId){


        List<Payment> payments = new ArrayList<Payment>();

        Connection connection               = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet                 = null;

        try {
            connection                  = this.dataSource.getConnection();
            String queryString = "SELECT * FROM payments " +
                    "WHERE mem_id = ? ";

            System.out.println(queryString);

            preparedStatement           = connection.prepareStatement(queryString);
            preparedStatement.setString(1, memberId);

            resultSet = preparedStatement.executeQuery();


            payments = this.paymentMapper.mapPayment(resultSet);

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return payments;
    }


    public void pay(String memberId){

        Connection connection               = null;
        PreparedStatement preparedStatement = null;

        try {
            connection                  = this.dataSource.getConnection();
            String queryString = "UPDATE members " +
                    "SET balance = 0 " +
                    "WHERE id = ?; ";



            preparedStatement           = connection.prepareStatement(queryString);
            preparedStatement.setString(1, memberId);


            preparedStatement.execute();

            connection.close();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void addPaymentRecord(String memberId, String paymentType, float amount, String dateString, String timeString){

        Connection connection               = null;
        PreparedStatement preparedStatement = null;

        try {
            connection                  = this.dataSource.getConnection();
            String queryString = "INSERT INTO payments(mem_id, type_of_payment, amount, date, time) " +
                    "VALUES(?, ?, ?, ?, ?)";



            preparedStatement           = connection.prepareStatement(queryString);
            preparedStatement.setString(1, memberId);
            preparedStatement.setString(2, paymentType);
            preparedStatement.setFloat(3, amount);
            preparedStatement.setString(4, dateString);
            preparedStatement.setString(5, timeString);


            preparedStatement.execute();

            connection.close();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
