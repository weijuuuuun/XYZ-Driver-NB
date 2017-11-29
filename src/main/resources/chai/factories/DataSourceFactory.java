package chai.factories;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    /**
     *
     * @return DataSource to a MySQL Database
     */
    public static DataSource getMySqlDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/xyz");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        return dataSource;
    }
}
