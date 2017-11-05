/**
 * Created by Mohammad on 10/22/2017.
 */
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

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

    public int getState(long chat_id)
    {
        ResultSet res = query("SELECT state FROM states WHERE chat_id="+chat_id);
        int state = -1;

        try {
            res.next();
            state = res.getInt("state");
        }catch (Exception e){
            e.printStackTrace();
            return state;
        }

        return state;
    }
    public void setState(int newState,long chat_id)
    {
        try {

        excecute("UPDATE states SET state='"+newState+"' WHERE chat_id='"+chat_id+"'");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setDataOfState(JSONObject jsonData,long chat_id)
    {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement("UPDATE states SET data=?" +
                    " WHERE chat_id="+chat_id);
            ps.setString(1,jsonData.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public JSONObject getDataOfState(long chat_id)
    {
        ResultSet r = query("SELECT data FROM states WHERE chat_id="+chat_id);
        try {
            r.next();
            String data = r.getString("data");
            if(data==null)
                return new JSONObject();
            return new JSONObject(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void createUserState(long chat_id,String fullname,String username)
    {
        try {
         excecute("INSERT INTO states(chat_id, state,fullname,username)" +
                 " VALUES('"+chat_id+"','0','"+fullname+"','"+username+"')");
        }catch (Exception e){
            e.printStackTrace();
        }
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
