package chai.dao;

import chai.daoMappers.UserMapper;
import chai.factories.DataSourceFactory;
import chai.models.Member;
import chai.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private DataSource dataSource;
    private UserMapper userMapper;

    public UserDAO(){
        this.dataSource = DataSourceFactory.getMySqlDataSource();
        this.userMapper = new UserMapper();
    }


    /**
     * Get User details from db based on userId
     * @param userid User's id to search for
     * @return User if found. NULL if not found
     */
    public User getUser(String userid){
        Connection connection               = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet                 = null;
        User user                           = new User();

        try {
            connection                  = this.dataSource.getConnection();
            String getPasswordSqlString = "SELECT * from users " +
                    "WHERE id = ? " +
                    "AND status <> ? ";

            preparedStatement           = connection.prepareStatement(getPasswordSqlString);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, "ADMIN");

            System.out.println(getPasswordSqlString);
            resultSet                   = preparedStatement.executeQuery();

            user = this.userMapper.mapSingleUser(resultSet);

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    /**
     * Get User details from db based on userId
     * @param userid User's id to search for
     * @return User if found. NULL if not found
     */
    public User getUserAdmin(String userid){
        Connection connection               = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet                 = null;
        User adminUser                      = new User();

        try {
            connection                  = this.dataSource.getConnection();
            String getPasswordSqlString = "SELECT * from users " +
                    "WHERE id = ? " +
                    "AND status = ? ";

            preparedStatement           = connection.prepareStatement(getPasswordSqlString);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, "ADMIN");

            System.out.println(getPasswordSqlString);
            resultSet                   = preparedStatement.executeQuery();

            adminUser = this.userMapper.mapSingleUser(resultSet);

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminUser;
    }


    /**
     * Inser a new user to user table
     * @param user the User object to add
     * @return the User object stored. Null if failed
     */
    public User addUser(User user){

        Connection connection               = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet                 = null;

        try {
            connection                  = this.dataSource.getConnection();
            String getPasswordSqlString = "INSERT INTO users " +
                    "(id, password, status) " +
                    "VALUES (?, ? ,?)";

            preparedStatement           = connection.prepareStatement(getPasswordSqlString);
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getStatus());

            System.out.println(getPasswordSqlString);
            preparedStatement.execute();


            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }





}
