import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conn {
    Connection cn;
//    PreparedStatement ps=null;
    Conn() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql:///javaawtpractice", "root", "admin");
//        ps=cn.prepareStatement("");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
