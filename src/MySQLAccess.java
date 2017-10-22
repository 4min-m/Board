/**
 * Created by Mohammad on 10/22/2017.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public MySQLAccess() {
        // This will load the MySQL driver, each DB has its own driver
        try {

            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            this.connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/board_bot?useUnicode=true&characterEncoding=UTF-8"
                            + "&user=root&password=");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void excecute(String s)
    {
        try{
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            statement.executeUpdate(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet query(String s)
    {
        try{
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public Connection getConnection()
    {
        return this.connect;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
