package chai.dao;

import chai.Services.DateService;
import chai.daoMappers.MemberMapper;
import chai.daoMappers.UserMapper;
import chai.factories.DataSourceFactory;
import chai.models.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private DataSource dataSource;
    private MemberMapper memberMapper;

    public MemberDAO(){
        this.dataSource = DataSourceFactory.getMySqlDataSource();
        this.memberMapper = new MemberMapper();
    }

    /**
     * Inser a new member to member table
     * @param member the Member object to add
     * @return the Member object stored. Null if failed
     */
    public Member addMember(Member member){


        Connection connection               = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet                 = null;

        DateService dateService = new DateService("yyyy-MM-dd");

        try {
            connection                  = this.dataSource.getConnection();
            String getPasswordSqlString = "INSERT INTO members " +
                    "(id, name, address, dob, dor, status, balance) " +
                    "VALUES (?, ? ,?, ? ,? ,? ,?)";

            preparedStatement           = connection.prepareStatement(getPasswordSqlString);
            preparedStatement.setString(1, member.getId());
            preparedStatement.setString(2, member.getFirstName() + " " + member.getLastName());
            preparedStatement.setString(3, member.getAddress());
            preparedStatement.setString(4, dateService.dateToString(member.getDob()));
            preparedStatement.setString(5, dateService.dateToString(member.getDor()));
            preparedStatement.setString(6, member.getStatus());
            preparedStatement.setFloat(7, member.getBalance());


            preparedStatement.execute();


            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return member;
    }


    public List<Member> getAll(){

        List<Member> members = new ArrayList<Member>();

        Connection connection               = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet                 = null;



        DateService dateService = new DateService("yyyy-MM-dd");

        try {
            connection                  = this.dataSource.getConnection();
            String getPasswordSqlString = "SELECT * FROM members";

            preparedStatement           = connection.prepareStatement(getPasswordSqlString);
            resultSet = preparedStatement.executeQuery();


            members = this.memberMapper.mapMembers(resultSet);

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return members;
    }


    public Member get(String memberId){

        Member member = new Member();

        Connection connection               = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet                 = null;

        DateService dateService = new DateService("yyyy-MM-dd");

        try {
            connection                  = this.dataSource.getConnection();
            String retrieveString = "SELECT * FROM members " +
                    "WHERE id = ? ";

            preparedStatement           = connection.prepareStatement(retrieveString);
            preparedStatement.setString(1, memberId);
            resultSet = preparedStatement.executeQuery();

            member = this.memberMapper.mapMember(resultSet);

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return member;
    }


    public void updateStatus(String newStatus, String memberId){

        Connection connection               = null;
        PreparedStatement preparedStatement = null;


        try {
            connection                  = this.dataSource.getConnection();
            String updateString = "UPDATE members " +
                    "SET status = ? " +
                    "WHERE id = ? ";

            preparedStatement           = connection.prepareStatement(updateString);
            preparedStatement.setString(1, newStatus);
            preparedStatement.setString(2, memberId);

            preparedStatement.execute();

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
