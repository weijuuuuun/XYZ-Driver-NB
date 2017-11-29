package chai.daoMappers;

import chai.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    /**
     * Method to map result set into a single User object
     * @param resultSet from SQL Query
     * @return User object
     */
    public User mapSingleUser(ResultSet resultSet) throws SQLException {

        if(!resultSet.isBeforeFirst()){
            return null;
        }

        User user = new User();
        resultSet.next();

        String id = resultSet.getString(1);

        user.setId(id);
        user.setPassword(resultSet.getString(2));
        user.setStatus(resultSet.getString(3));

        return user;
    }

}
